package im.access;

import echo.modular.SimpleList;
import im.AReplicatedSimpleList;
import im.AnIMClientComposerAndLauncher;
import im.ReplicatedSimpleList;
import im.aware.ASessionAwarenessProvider;
import im.aware.AnAwareIMComposerAndLauncher;
import util.session.Communicator;
import util.session.ReceivedMessageListener;
import util.session.SessionMessageListener;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AnAccessControlledIMClientComposerAndLauncher extends AnIMClientComposerAndLauncher {	
	static String APPLICATION_NAME = "Access Controlled IM";	
	protected void addCollaborationFunctions() {
		super.addCollaborationFunctions();
		addAccessControl();	
	}
	protected SimpleList<String> createHistory() {
		return new AControlledReplicatedHistory<String>(communicator);
	}	
	public  void addAccessControl() {	
		AccessController accessController = new AnAccessController(communicator);	
		accessController.setOwner(AliceAccessControlledIM.USER_NAME);
//		accessController.addAdministratorLocal(AliceAccessControlledIM.USER_NAME);
//		accessController.addInputterLocal(AliceAccessControlledIM.USER_NAME);
		ReceivedMessageListener accessReceiver = new AnAccessReceiver(communicator, accessController);
		communicator.addReceivedMessageListener(accessReceiver);
//		controlledReplicatedHistory().addVetoer(accessController);
		controlledReplicatedHistory().addVetoableChangeListener(accessController);

		OEFrame aFrame = ObjectEditor.edit(accessController);
		aFrame.setTitle(communicator.getClientName() + " Access Controller");

	}	
	protected ControlledReplicatedHistory<String> controlledReplicatedHistory() {
		return (ControlledReplicatedHistory<String>) history;
	}
	
	
}
