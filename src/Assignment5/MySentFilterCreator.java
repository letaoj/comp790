package Assignment5;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;

public class MySentFilterCreator implements MessageFilterCreator<SentMessage> {

	MessageFilter<SentMessage> sentMessageFilter;

	public MySentFilterCreator(CausalityManager causalityManager) {
		sentMessageFilter = new MyTimeStampingSentMessageFilter(causalityManager);
	}

	@Override
	public MessageFilter<SentMessage> getMessageFilter() {
		return sentMessageFilter;
	}

}
