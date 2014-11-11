package Assignment5;

import trace.causal.LocalCountIncrementedInSiteVectorTimeStamp;
import trace.causal.VectorTimeStampedMessageSent;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;

public class MyTimeStampingSentMessageFilter implements
		MessageFilter<SentMessage> {

	MessageProcessor<SentMessage> messageProcessor;
	CausalityManager causalityManager;

	public MyTimeStampingSentMessageFilter(CausalityManager causalityManager) {
		this.causalityManager = causalityManager;
	}

	@Override
	public void setMessageProcessor(
			MessageProcessor<SentMessage> theMesssageProcessor) {
		messageProcessor = theMesssageProcessor;
	}

	@Override
	public void filterMessage(SentMessage message) {
		if (message.isUserMessage() && causalityManager.isDynamicCaulity()) {
			VectorTimeStamp timeStamp = causalityManager.getTimeStamp();
			timeStamp.inc(message.getSendingUser());
			LocalCountIncrementedInSiteVectorTimeStamp.newCase(
					timeStamp.getTimeStamp(), this);
			VectorTimeStamp tmp = timeStamp.deepCopy(timeStamp);
			message.setUserMessage(new AMessageWithTimeStamp(message
					.getUserMessage(), tmp));
			VectorTimeStampedMessageSent.newCase(message, timeStamp.getTimeStamp(), this);
			messageProcessor.processMessage(message);
		} else {
			messageProcessor.processMessage(message);
		}
	}

}