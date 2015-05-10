package services;

import interfaces.RemoteObserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import library.ctrl.LibraryController;
import model.LibraryException;

public class ClientServiceManager extends UnicastRemoteObject implements RemoteObserver{

	private LibraryController controller;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientServiceManager(LibraryController controller) throws RemoteException {
		super();
		this.controller = controller;
	}

	@Override
	public void update(Object observable, Object updateMsg)
			throws RemoteException, LibraryException {
		this.controller.update();
		
	}

}
