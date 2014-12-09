package trace.causal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trace.echo.ListEditInfo;
import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class VectorTimeStampedMessageInfo extends VectorTimeStampInfo {
//	VectorTimeStampInfo vectorTimeStamp;
	long messageTimeStamp;
	public static final String REAL_TS = "RealTS";
//	String processName;
	public VectorTimeStampedMessageInfo(String aMessage, String aProcessName,
			Map<String, Integer>  aVectorTimeStamp,
			long along, 
			Object aFinder) {
		super(aMessage, aProcessName, aVectorTimeStamp,  aFinder);
//		processName = aProcessName;
//		vectorTimeStamp = aVectorTimeStamp;
		messageTimeStamp = along;
	}
	
	public VectorTimeStampedMessageInfo(long along, 
			Map<String, Integer>  aVectorTimeStamp) {
		this("", "", aVectorTimeStamp, along, null);
	}

	public VectorTimeStampedMessageInfo(String aMessage, 
			Map<String, Integer>  aVectorTimeStamp, long along, ProcessInfo aProcessInfo) {
//		super(aMessage, "", aProcessInfo);
		super(aMessage, aVectorTimeStamp, aProcessInfo);

//		vectorTimeStamp = aVectorTimeStamp;
		messageTimeStamp = along;
	}

	public VectorTimeStampedMessageInfo(String aMessage, VectorTimeStampedMessageInfo aVectorTimeStampedMessage) {
		this (aMessage, aVectorTimeStampedMessage.getUserToCount(), 
				aVectorTimeStampedMessage.getMessageTimeStamp(),
				aVectorTimeStampedMessage);
		
	}
	public static String toLocalInfoToString(String aProcessName, long along,
			Map<String, Integer>  aVectorTimeStamp) {
		return REAL_TS + "(" + along+  ")" + " "
				+ toString(aProcessName, aVectorTimeStamp);
	}
	public static String toString(String aProcessName, long along,
			Map<String, Integer> aVectorTimeStamp) {
		return toString(aProcessName) + " " + toLocalInfoToString(aProcessName, along, aVectorTimeStamp);
//				"OTEdit(" + 
//				along.toLocalInfoToString() + " "
//				+ aVectorTimeStamp.alternativeToString() ;
				
//				+ ")";

	}
	
	public static VectorTimeStampedMessageInfo toTraceable(String aMessage) {
//		ProcessInfo aProcessInfo;
//		try {
//		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
//		} catch (Exception e) {
//			System.out.println(e);
//			aProcessInfo = null;
//		}
//		long along = long.toTraceable(aMessage);
		long aLong =   Long.parseLong(getArgs(aMessage, REAL_TS).get(0));
		Map<String, Integer> retVal = new HashMap();
		VectorTimeStampInfo aTimeStampInfo = VectorTimeStampInfo.toTraceable(aMessage);
		
		return new VectorTimeStampedMessageInfo(aMessage, aTimeStampInfo.getUserToCount(), aLong, aTimeStampInfo);
		
	}
	

	public String alternativeToString() {
		return toString(processName, messageTimeStamp, userToCount);
	}
	public String toLocalInfoToString() {
		return toLocalInfoToString(processName, messageTimeStamp, userToCount);
	}


//	public Map<String, Integer>  getVectorTimeStamp() {
//		return vectorTimeStamp;
//	}

	public long getMessageTimeStamp() {
		return messageTimeStamp;
	}
	
//	public String getProcessName() {
//		return processName;
//	}

}
