package im.timestamp;

import im.AnIMClientComposerAndLauncher;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessageFilterSelector;
import util.session.Communicator;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;
import util.session.SentMessage;

public class ATimeStampingIMComposerAndLauncher extends
		AnIMClientComposerAndLauncher {

	public Communicator createCommunicator(String args[], String applicationName) {
		// set factories used to create communicator
		MessageFilterCreator<ReceivedMessage> receivedMessageQueuerCreator = new ADeTimeStampingReceivedMessageFilterCreator();
		MessageFilterCreator<SentMessage> sentMessageQueuerCreator = new ATimeStampingSentMessageFilterCreator();
		ReceivedMessageFilterSelector
				.setMessageFilterCreator(receivedMessageQueuerCreator);
		SentMessageFilterSelector
				.setMessageFilterCreator(sentMessageQueuerCreator);
		// create communicator
		return super.createCommunicator(args);
	}
}
