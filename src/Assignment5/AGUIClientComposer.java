package Assignment5;

import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.session.MessageFilterCreator;
import util.session.PeerMessageListener;
import util.session.ReceivedMessage;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessage;
import util.session.SentMessageFilterSelector;

public class AGUIClientComposer {

	protected Communicator communicator;
	protected PeerMessageListener listInCoupler;

	public Communicator compose(String[] args, CausalityManager causalityManager) {
		return communicator = createCommunicator(args, causalityManager);
	}

	public void checkArgs(String[] args) {
		if (args.length < 5) {
			System.out
					.println("Please supply server host name, session name,  user name and application name as main arguments");
			System.exit(-1);
		}
	}

	// parameters to factory
	public Communicator createCommunicator(String args[],
			CausalityManager causalityManager) {
		// set factories used to create communicator
		MessageFilterCreator<ReceivedMessage> receivedMessageCreator = new MyReceiveFilterCreator(
				causalityManager);
		MessageFilterCreator<SentMessage> sentMessageCreator = new MySentFilterCreator(
				causalityManager);
		ReceivedMessageFilterSelector
				.setMessageFilterCreator(receivedMessageCreator);
		SentMessageFilterSelector.setMessageFilterCreator(sentMessageCreator);

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

	public PeerMessageListener getRemoteInputEchoer() {
		return listInCoupler;
	}

	public Communicator getCommunicator() {
		return communicator;
	}

}
