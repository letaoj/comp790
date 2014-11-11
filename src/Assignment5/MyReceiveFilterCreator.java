package Assignment5;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;

public class MyReceiveFilterCreator implements
		MessageFilterCreator<ReceivedMessage> {

	MessageFilter<ReceivedMessage> receivedMessageFilter;
	CausalityManager causalityManager;
	
	public MyReceiveFilterCreator(CausalityManager causalityManager) {
		receivedMessageFilter = new MyTimeStampingReceivedMessageFilter(causalityManager);
	}

	@Override
	public MessageFilter<ReceivedMessage> getMessageFilter() {
		return receivedMessageFilter;
	}

}
