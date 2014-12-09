package trace.causal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trace.echo.ListEditInfo;
import util.trace.TraceableInfo;
import util.trace.session.ProcessInfo;

public class OrderedVectorTimeStampedMessageInfo extends VectorTimeStampedMessageInfo {
//	VectorTimeStampedMessageInfo vectorTimeStampedMessage;
	int pos;
	public static final String POS = "Pos";
//	String processName;
	public OrderedVectorTimeStampedMessageInfo(String aMessage, String aProcessName,
			Map<String, Integer> aVectorTimeStamp,
			int aPos, long aLong, 
			Object aFinder) {
		super(aMessage, aProcessName, aVectorTimeStamp,  aLong,  aFinder);
//		processName = aProcessName;
		pos = aPos;
	}
	
	public OrderedVectorTimeStampedMessageInfo(int aPos, long aLong,
			Map<String, Integer> aVectorTimeStamp) {
		this("", "", aVectorTimeStamp,aPos, aLong,   null);
	}

	public OrderedVectorTimeStampedMessageInfo(String aMessage, Map<String, Integer> aVectorTimeStamp, int aPos, long aLong,
			 ProcessInfo aProcessInfo) {
//		super(aMessage, "", aProcessInfo);
		super(aMessage,  aVectorTimeStamp, aLong, aProcessInfo);

		pos = aPos;
	}

//	public OrderedVectorTimeStampedMessageInfo(String aMessage, OrderedVectorTimeStampedMessageInfo anOrderedInfo) {
//		this (aMessage, anOrderedInfo.getListEdit(), anOrderedInfo.getUserOTTimeStamp(), anOrderedInfo);
//		
//	}
	public static String toLocalInfoToString(String aProcessName, int aPos, long aTS,
			Map<String, Integer> aVectorTimeStampedMessage) {
		return POS + "(" + aPos+  ")" + " " + toLocalInfoToString (aProcessName, aTS, aVectorTimeStampedMessage);
//				+ aVectorTimeStampedMessage.alternativeToString() ;
		
	}
	public static String toString(String aProcessName, Map<String, Integer> aVectorTimeStamp,
			int aPos, long aLong) {
		return toString(aProcessName) + " " + toLocalInfoToString(aProcessName, aPos, aLong, aVectorTimeStamp);
//				"OTEdit(" + 
//				aPos.toLocalInfoToString() + " "
//				+ aVectorTimeStamp.alternativeToString() ;
				
//				+ ")";

	}
	
	public static OrderedVectorTimeStampedMessageInfo toTraceable(String aMessage) {
//		ProcessInfo aProcessInfo;
//		try {
		ProcessInfo aProcessInfo = ProcessInfo.toTraceable(aMessage);
//		} catch (Exception e) {
//			System.out.println(e);
//			aProcessInfo = null;
//		}
//		long aPos = long.toTraceable(aMessage);
		int aPos =   Integer.parseInt(getArgs(aMessage, POS).get(0));
		VectorTimeStampedMessageInfo aMessageInfo = VectorTimeStampedMessageInfo.toTraceable(aMessage);
		return new OrderedVectorTimeStampedMessageInfo(aMessage, aMessageInfo.getUserToCount(), aPos, aMessageInfo.getMessageTimeStamp(), 
				aProcessInfo);
		
	}
	

	public String alternativeToString() {
		return toString(processName, userToCount, pos, messageTimeStamp);
	}
	public String toLocalInfoToString() {
		return toLocalInfoToString(processName, pos, messageTimeStamp, userToCount);
	}


	

	public long getPosition() {
		return pos;
	}
	


}
