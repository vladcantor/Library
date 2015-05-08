package startUp;

import java.io.FileReader;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

import library.ctrl.LibraryServerController;
import model.Librarian;
import model.LibraryException;
import repository.ILibraryRepository;
import repository.LibraryRepositoryJDBC;
import repository.MemoryRepository;

public class StartServer {

	public static void main(String[] args) throws LibraryException,
			NumberFormatException, RemoteException, AlreadyBoundException {
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
		// ILibraryRepository parts=new LibraryRepositoryJDBC();
		ILibraryRepository memo = new MemoryRepository(
				new LibraryRepositoryJDBC());
		Librarian concurs = new Librarian(memo);
		
		String rmiId = System.getProperty("RMI_ID");
		int rmiPort = Integer.parseInt(System.getProperty("RMI_Port"));
		Registry reg = LocateRegistry.createRegistry(rmiPort);
		LibraryServerController controller = (LibraryServerController)UnicastRemoteObject
                .exportObject(new LibraryServerController(concurs), rmiPort);
		reg.bind(rmiId, controller);

	}
}
