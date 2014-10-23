package Assignment3;

import util.awt.AnExtendibleAWTEventQueue;

public class AGUIClientComposerAndLauncher {
	protected UI ui;
	AnExtendibleAWTEventQueue anExtendibleAWTEventQueue;
	ListeningInputDistributer listeningInputDistributer;

	public void composeAndLaunch(String[] args) {
		anExtendibleAWTEventQueue = AnExtendibleAWTEventQueue.getEventQueue();
		listeningInputDistributer = new ListeningInputDistributer(
				anExtendibleAWTEventQueue);
		ui = new UI(args[2], listeningInputDistributer);
		listeningInputDistributer.compose(args);
		anExtendibleAWTEventQueue
				.addEventQueueHandler(listeningInputDistributer);
		listeningInputDistributer.registerTree(ui);
	}
}
