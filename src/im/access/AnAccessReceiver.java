package im.access;

import java.util.Collection;

import javax.swing.JOptionPane;

import util.session.Communicator;
import util.session.ReceivedMessageListener;

public class AnAccessReceiver implements ReceivedMessageListener {
	Communicator communicator;
	AccessController accessController;
	public AnAccessReceiver(Communicator theCommunicator,
			AccessController theAccessController) {
		communicator = theCommunicator;
		accessController = theAccessController;
	}
	public void clientJoined(
			String userName, String theApplicationName, 
			String theSessionName, boolean newSession, 
			boolean newApplication, Collection<String> allUsers) {
//		if (newApplication) {
//			accessController.setOwnerName(userName);
//			accessController.addAdministratorLocal(userName);
//			accessController.addInputterLocal(userName);
//		}
	}
	public void objectReceived(Object message, String sourceName) {
		if (message instanceof AnInputAuthorization)
			processInputAuthorization((Message<String>) message);
		else if (message instanceof AnAdministratorAuthorization)
			processAdminAuthorization((Message<String>) message);
		else if (message instanceof ARemoteInputDenied) {
			processRemoteInputDenied((Message<String>) message);
		}
	}
	void processInputAuthorization(Message<String> message) {
		accessController.addInputterLocal(message.getData());
	}
	void processAdminAuthorization(Message<String> message) {
		accessController.addAdministratorLocal(message.getData());
	}
	
	void processRemoteInputDenied(Message<String> message) {
		if (accessController.canAdminister(communicator.getClientName())) {
			
			System.out.println(message.getData() + " cannot input, and you have the right to administrator");
			//default icon, custom title
			int n = JOptionPane.showConfirmDialog(
			    null,
			   communicator.getClientName() +  ", would you like to give the input right to " + message.getData(),
			    "Interactive Grant Dialog",
			    JOptionPane.YES_NO_OPTION);
			if (n == 0)
				accessController.addInputter(message.getData());
		}
//		accessController.addInputterLocal(message.getData());
	}
	public void clientLeft(String userName, String theApplicationName) {}
}
