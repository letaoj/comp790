package Assignment4;

import util.awt.AnExtendibleAWTEventQueue;
import util.session.Communicator;
import util.session.CommunicatorSelector;

public class AMasterGUIComposerAndLauncher {
	protected UI ui;
	protected AReplicatedLockController aReplicatedLockController;
	AnExtendibleAWTEventQueue anExtendibleAWTEventQueue;
	ListeningInputDistributer listeningInputDistributer;
	MasterInputController inputController;
	Communicator communicator;

	public void composeAndLaunch(String[] args) {
		communicator = createCommunicator(args);
		communicator.join();
		anExtendibleAWTEventQueue = AnExtendibleAWTEventQueue.getEventQueue();
		listeningInputDistributer = new ListeningInputDistributer(
				anExtendibleAWTEventQueue, communicator);
		ui = new UI(listeningInputDistributer);
		aReplicatedLockController = new AReplicatedLockController();
		inputController = new MasterInputController(communicator,
				aReplicatedLockController);
		listeningInputDistributer.compose();
		anExtendibleAWTEventQueue
				.addEventQueueHandler(listeningInputDistributer);
		aReplicatedLockController.addVetoableChangeListener(inputController);
		anExtendibleAWTEventQueue.addVetoableChangeListener(inputController);
		listeningInputDistributer.registerTree(ui);
	}

	public void checkArgs(String[] args) {
		if (args.length < 5) {
			System.out
					.println("Please supply server host name, session name,  user name and application name as main arguments");
			System.exit(-1);
		}
	}

	public Communicator createCommunicator(String args[]) {
		checkArgs(args);
		if (args.length == 5) {
			if (args[4].equalsIgnoreCase(Communicator.DIRECT))
				CommunicatorSelector.selectDirectCommunicator();
			else if (args[4].equalsIgnoreCase(Communicator.RELAYED))
				CommunicatorSelector.selectRelayerCommunicator();
		}
		return CommunicatorSelector.getCommunicator(args[0], args[1], args[2],
				args[3]);
	}
}
