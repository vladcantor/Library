package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.LibraryException;

public interface RemoteObserver extends Remote {

    void update(Object observable, Object updateMsg) throws RemoteException, LibraryException;

}
