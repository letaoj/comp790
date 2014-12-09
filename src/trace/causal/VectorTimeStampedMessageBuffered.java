package trace.causal;

import java.util.Map;

import trace.locking.SlaveLockGrantRequestMade;
import util.session.CommunicatorSelector;
import util.session.ReceivedMessage;
import util.trace.session.ProcessInfo;

public class VectorTimeStampedMessageBuffered extends OrderedVectorTimeStampedMessageInfo{

//	public VectorTimeStampedMessageBuffered(String aMessage, String aProcess, int aPos, long aLong,
//			Map<String, Integer> aVectorTimeStamp, Object aFinder) {
//		super(aMessage, aProcess, aPos, aLong, aVectorTimeStamp, aFinder);
//		// TODO Auto-generated constructor stub
//	}

//	public VectorTimeStampedMessageBuffered(String aMessage, int aPos,
//			long aLong, Map<String, Integer> aVectorTimeStamp,
//			ProcessInfo aProcessInfo) {
//		super(aMessage, aPos, aLong, aVectorTimeStamp, aProcessInfo);
//		// TODO Auto-generated constructor stub
//	}
//
//	public VectorTimeStampedMessageBuffered(String aMessage,
//			String aProcessName, int aPos, long aLong,
//			Map<String, Integer> aVectorTimeStamp, Object aFinder) {
//		super(aMessage, aProcessName, aPos, aLong, aVectorTimeStamp, aFinder);
//		// TODO Auto-generated constructor stub
//	}
	
	public static VectorTimeStampedMessageBuffered newCase(
			String aProcessName,
			int aPos,
			ReceivedMessage aReceivedMessage,
			Map<String, Integer> aVectorTimeStamp,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName, aVectorTimeStamp,  aPos, aReceivedMessage.getTimeStamp());
		VectorTimeStampedMessageBuffered retVal = new VectorTimeStampedMessageBuffered(aMessage, aProcessName, 
				aVectorTimeStamp, aPos, aReceivedMessage.getTimeStamp(), aFinder);
		retVal.announce();
		return retVal;
	}
	public VectorTimeStampedMessageBuffered(int aPos, long aLong,
			Map<String, Integer> aVectorTimeStamp) {
		super(aPos, aLong, aVectorTimeStamp);
		// TODO Auto-generated constructor stub
	}
	public VectorTimeStampedMessageBuffered(String aMessage,
			Map<String, Integer> aVectorTimeStamp, int aPos, long aLong,
			ProcessInfo aProcessInfo) {
		super(aMessage, aVectorTimeStamp, aPos, aLong, aProcessInfo);
		// TODO Auto-generated constructor stub
	}
	public VectorTimeStampedMessageBuffered(String aMessage,
			String aProcessName, Map<String, Integer> aVectorTimeStamp,
			int aPos, long aLong, Object aFinder) {
		super(aMessage, aProcessName, aVectorTimeStamp, aPos, aLong, aFinder);
		// TODO Auto-generated constructor stub
	}
	public static VectorTimeStampedMessageBuffered newCase(
			int aPos,
			ReceivedMessage aReceivedMessage,
			Map<String, Integer> aVectorTimeStamp,
			  Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(), aPos, aReceivedMessage, aVectorTimeStamp, aFinder);
			
		
	}

}
