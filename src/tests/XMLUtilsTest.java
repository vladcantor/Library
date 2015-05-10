package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Subscriber;

import org.junit.Test;

import repository.XMLUtils;

public class XMLUtilsTest {

	@Test
	public final void test() {
		ArrayList<Subscriber> source = new ArrayList<Subscriber>();
		source.add(new Subscriber("subs1"));
		source.add(new Subscriber("subs2"));
		source.add(new Subscriber("subs3"));
		source.add(new Subscriber("subs4"));
		
		try {
			ArrayList<HashMap<String, String>> dest = XMLUtils.prepareDataToSave(source, Subscriber.class);
			XMLUtils.saveDataToXMLFile(dest, Subscriber.class, "TestSubscriber.txt");
			ArrayList<Subscriber> result =  XMLUtils.readDataFromXMLFile("TestSubscriber.txt", Subscriber.class);
			assertEquals(result.size(), source.size());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
