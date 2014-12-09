package trace.causal;

import java.util.Map;

import trace.locking.SlaveLockGrantRequestMade;
import util.session.CommunicatorSelector;
import util.session.ReceivedMessage;
import util.trace.session.ProcessInfo;

public class VectorTimeStampedMessageRemovedFromBuffer extends VectorTimeStampedMessageInfo{

//	public VectorTimeStampedMessageBuffered(String aMessage, String aProcess, int aPos, long aLong,
//			Map<String, Integer> aVectorTimeStamp, Object aFinder) {
//		super(aMessage, aProcess, aPos, aLong, aVectorTimeStamp, aFinder);
//		// TODO Auto-generated constructor stub
//	}

	public VectorTimeStampedMessageRemovedFromBuffer(String aMessage, 
			long aLong, Map<String, Integer> aVectorTimeStamp,
			ProcessInfo aProcessInfo) {
		super(aMessage,   aVectorTimeStamp, aLong, aProcessInfo);
		// TODO Auto-generated constructor stub
	}

	public VectorTimeStampedMessageRemovedFromBuffer(String aMessage,
			String aProcessName,  
			Map<String, Integer> aVectorTimeStamp, long aLong,Object aFinder) {
		super(aMessage, aProcessName,   aVectorTimeStamp, aLong, aFinder);
		// TODO Auto-generated constructor stub
	}
	public static VectorTimeStampedMessageRemovedFromBuffer newCase(
			String aProcessName,
			ReceivedMessage aReceivedMessage,
			Map<String, Integer> aVectorTimeStamp,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName,  aReceivedMessage.getTimeStamp(), aVectorTimeStamp);
		VectorTimeStampedMessageRemovedFromBuffer retVal = new VectorTimeStampedMessageRemovedFromBuffer(aMessage, aProcessName, 
				aVectorTimeStamp, aReceivedMessage.getTimeStamp(),  aFinder);
		retVal.announce();
		return retVal;
	}
	public static VectorTimeStampedMessageRemovedFromBuffer newCase(
			
			ReceivedMessage aReceivedMessage,
			Map<String, Integer> aVectorTimeStamp,
			  Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(),  aReceivedMessage, aVectorTimeStamp, aFinder);
			
		
	}

}
