package repository;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.XMLDeserialization;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

public class XMLUtils {

	public XMLUtils() {
		// TODO Auto-generated constructor stub
	}

	public static <T> ArrayList<HashMap<String, String>> prepareDataToSave(

	ArrayList<T> source, Class<T> classType) throws NoSuchMethodException,
			SecurityException, IllegalArgumentException, IllegalAccessException {

		ArrayList<HashMap<String, String>> destination = new ArrayList<HashMap<String, String>>();
		ArrayList<Field> savedFieds = new ArrayList<Field>();
		Field[] fields;
		Object value = null;
		HashMap<String, String> aux;
		Field auxField;

		fields = classType.getDeclaredFields();

		for (int j = 0; j < fields.length; j++) {

			Class<?> type = fields[j].getType();

			if (!(Modifier.isStatic(fields[j].getModifiers())
					|| Modifier.isFinal(fields[j].getModifiers())
					|| type.isArray() || Collection.class
						.isAssignableFrom(type))) {

				savedFieds.add(fields[j]);

			}
		}

		if (classType == null || fields == null) {
			return null;
		}

		for (int i = 0; i < source.size(); i++) {
			aux = new HashMap<String, String>();

			for (int j = 0; j < savedFieds.size(); j++) {

				auxField = savedFieds.get(j);
				auxField.setAccessible(true);
				value = auxField.get(source.get(i));
				aux.put(auxField.getName(), value.toString());

			}

			destination.add(aux);
		}

		return destination;

	}

	public static <T> void saveDataToXMLFile(
			ArrayList<HashMap<String, String>> data, Class<T> dataType,
			String fileName) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);

			for (HashMap<String, String> elem : data) {

				Element objectNode = doc.createElement(dataType.getName());
				rootElement.appendChild(objectNode);
				Element fieldNode;

				for (Entry<String, String> entry : elem.entrySet()) {
					fieldNode = doc.createElement(entry.getKey());
					objectNode.appendChild(fieldNode);
					fieldNode.appendChild(doc.createTextNode(entry.getValue()));
				}
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String filePath = System.getProperty("user.dir");
			filePath = filePath + "\\" + fileName;
			StreamResult result = new StreamResult(new File(filePath));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException
				| TransformerConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> ArrayList<T> readDataFromXMLFile(String fileName,
			final Class<T> objectType) {
		final ArrayList<T> objects = new ArrayList<T>();

		if (!XMLDeserialization.class.isAssignableFrom(objectType)) {
			return objects;
		}

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {

				HashMap<String, String> properties;
				String currentElementKey = "";

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					if (qName.equalsIgnoreCase("root")) {
						System.out.println("Start Element :" + qName);
						return;
					}

					if (qName.equalsIgnoreCase(objectType.getName())) {
						properties = new HashMap<String, String>();
					}
					currentElementKey = qName;
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {
					if (qName.equalsIgnoreCase(objectType.getName())) {
						try {
							objects.add(objectType.getDeclaredConstructor(
									HashMap.class).newInstance(properties));
							currentElementKey = "";
						} catch (InstantiationException
								| IllegalAccessException
								| IllegalArgumentException
								| InvocationTargetException
								| NoSuchMethodException | SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (qName.equalsIgnoreCase("root")) {
						System.out.println("End Element :" + qName);
					}

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {

					if (!currentElementKey.isEmpty()) {
						properties.put(currentElementKey, new String(ch, start, length));
					}
				}

			};
			String filePath = System.getProperty("user.dir");
			filePath = filePath + "\\" + fileName;
			saxParser.parse(filePath, handler);

		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objects;
	}
}
