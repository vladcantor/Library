package startUp;

import gui.StartApp;
import interfaces.LibraryService;

import java.awt.EventQueue;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import library.ctrl.LibraryController;
import model.LibraryException;
import services.ClientServiceManager;

public class StartClient {

	public StartClient() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws LibraryException,
			NumberFormatException, RemoteException, NotBoundException {
		// PropertyConfigurator.configure(args[0]);
		Properties serverProps = new Properties(System.getProperties());
		try {
			serverProps.load(new FileReader("libraryjdbc.properties"));
			System.setProperties(serverProps);

			System.out.println("Properties set: ");
			System.getProperties().list(System.out);
		} catch (IOException e) {
			System.out.println("Cannot find libraryjdbc.properties " + e);
			return;
		}
		
		String rmiId = System.getProperty("RMI_ID");
		String rmiPort = System.getProperty("RMI_Port");

		//if (System.getSecurityManager() == null)
		//	System.setSecurityManager(new RMISecurityManager());

		try {
			Registry reg = LocateRegistry.getRegistry("localhost",
					Integer.parseInt(rmiPort));
			LibraryService service = (LibraryService) reg.lookup(rmiId);
			final LibraryController ctrl = new LibraryController(service);
			ClientServiceManager client = new ClientServiceManager(ctrl);
			service.addObserver(client);

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						StartApp frame = new StartApp(ctrl);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
