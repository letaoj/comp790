package trace.causal;

import java.util.Map;

import trace.locking.SlaveLockGrantRequestMade;
import util.session.CommunicatorSelector;
import util.session.SentMessage;
import util.trace.session.ProcessInfo;

public class VectorTimeStampedMessageSent extends VectorTimeStampedMessageInfo{

//	public VectorTimeStampedMessageBuffered(String aMessage, String aProcess, int aPos, long aLong,
//			Map<String, Integer> aVectorTimeStamp, Object aFinder) {
//		super(aMessage, aProcess, aPos, aLong, aVectorTimeStamp, aFinder);
//		// TODO Auto-generated constructor stub
//	}

	public VectorTimeStampedMessageSent(String aMessage, 
			long aLong, Map<String, Integer> aVectorTimeStamp,
			ProcessInfo aProcessInfo) {
		super(aMessage,   aVectorTimeStamp, aLong, aProcessInfo);
		// TODO Auto-generated constructor stub
	}

	public VectorTimeStampedMessageSent(String aMessage,
			String aProcessName,  
			Map<String, Integer> aVectorTimeStamp, long aLong,Object aFinder) {
		super(aMessage, aProcessName,   aVectorTimeStamp, aLong, aFinder);
		// TODO Auto-generated constructor stub
	}
	public static VectorTimeStampedMessageSent newCase(
			String aProcessName,
			SentMessage aSentMessage,
			Map<String, Integer> aVectorTimeStamp,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName,  aSentMessage.getTimeStamp(), aVectorTimeStamp);
		VectorTimeStampedMessageSent retVal = new VectorTimeStampedMessageSent(aMessage, aProcessName, 
				aVectorTimeStamp, aSentMessage.getTimeStamp(),  aFinder);
		retVal.announce();
		return retVal;
	}
	public static VectorTimeStampedMessageSent newCase(
			
			SentMessage aSentMessage,
			Map<String, Integer> aVectorTimeStamp,
			  Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(),  aSentMessage, aVectorTimeStamp, aFinder);
			
		
	}

}
