package Assignment5;

import java.util.Queue;

import trace.causal.ConcurrentVectorTimeStampedMessageDetected;
import trace.causal.RemoteCountIncrementedInSiteVectorTimeStamp;
import trace.causal.VectorTimeStampedMessageBuffered;
import trace.causal.VectorTimeStampedMessageDelivered;
import trace.causal.VectorTimeStampedMessageRemovedFromBuffer;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;

public class MyTimeStampingReceivedMessageFilter implements
		MessageFilter<ReceivedMessage> {

	MessageProcessor<ReceivedMessage> messageProcessor;
	CausalityManager causalityManager;

	public MyTimeStampingReceivedMessageFilter(CausalityManager causalityManager) {
		this.causalityManager = causalityManager;
	}

	@Override
	public void setMessageProcessor(
			MessageProcessor<ReceivedMessage> theMesssageProcessor) {
		messageProcessor = theMesssageProcessor;
	}

	@Override
	public void filterMessage(ReceivedMessage message) {
		if (message.isUserMessage() && causalityManager.isDynamicCaulity()) {
			VectorTimeStamp timeStamp = causalityManager.getTimeStamp();
			AMessageWithTimeStamp msg = (AMessageWithTimeStamp) message
					.getUserMessage();
			causalityManager.addToBuffer(msg);
			VectorTimeStampedMessageBuffered.newCase(0, message,
					timeStamp.getTimeStamp(), this);
			Queue<AMessageWithTimeStamp> buffer = causalityManager.getBuffer();
			try {
				while (!buffer.isEmpty()
						&& ((AMessageWithTimeStamp) buffer.peek())
								.getVectorTimeStamp().successor(timeStamp)) {
					AMessageWithTimeStamp msgWithTimeStamp = buffer.remove();
					VectorTimeStampedMessageRemovedFromBuffer.newCase(message,
							msgWithTimeStamp.getVectorTimeStamp()
									.getTimeStamp(), this);
					message.setUserMessage(msgWithTimeStamp.getMessage());
					messageProcessor.processMessage(message);
					causalityManager.setTimeStamp(msgWithTimeStamp
							.getVectorTimeStamp());
					timeStamp = causalityManager.getTimeStamp();
					VectorTimeStampedMessageDelivered.newCase(message, timeStamp.getTimeStamp(), this);
					RemoteCountIncrementedInSiteVectorTimeStamp.newCase(timeStamp.getTimeStamp(), this);
				}
			} catch (Exception e) {
				ConcurrentVectorTimeStampedMessageDetected.newCase(message,
						timeStamp.getTimeStamp(), this);
				System.out.println("Concurrent message detected!!!");
				return;
			}
		} else {
			messageProcessor.processMessage(message);
		}
	}
}
