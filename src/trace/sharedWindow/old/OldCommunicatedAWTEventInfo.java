package trace.sharedWindow.old;
import java.awt.AWTEvent;

import trace.echo.ListEditInfo;
import trace.echo.modular.OperationName;
import util.trace.session.AddressedMessageInfo;
import util.trace.session.ProcessInfo;
//public class CommunicatedListEditInfo extends ListEditInfo {
public class OldCommunicatedAWTEventInfo extends AWTEventInfo {

	String destinationOrSource;
	String processName;
	
	public OldCommunicatedAWTEventInfo(String aMessage,
			String aProcessName,
			AWTEvent anAWTEvent, 
			String aGlobalId,
			String aDestinationOrSource,
			Object aFinder) {
		super(aMessage, anAWTEvent, aGlobalId, aFinder);
//		this.operationName = aName;
//		this.index = anIndex;
//		this.element = anElement;
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public OldCommunicatedAWTEventInfo(String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource) {
		this("",  aProcessName, anAWTEvent, aGlobalId, aDestinationOrSource, null);		
	}
	public OldCommunicatedAWTEventInfo(String aMessage, String aProcessName, String aDestinationOrSource, AWTEventInfo anAWTEventInfo) {
		super(aMessage, anAWTEventInfo);
		processName = aProcessName;
		destinationOrSource = aDestinationOrSource;
	}
	public OldCommunicatedAWTEventInfo(String aMessage, OldCommunicatedAWTEventInfo aCommunicatedAWTEventInfo) {
		this(aMessage, aCommunicatedAWTEventInfo.getProcessName(), aCommunicatedAWTEventInfo.getDestinationOrSource(), aCommunicatedAWTEventInfo);
	}

	public static OldCommunicatedAWTEventInfo toTraceable (String aMessage) {
		AWTEventInfo anAWTinfo = AWTEventInfo.toTraceable(aMessage);
		String aProcessName = ProcessInfo.getProcessName(aMessage);
		String anAddress = AddressedMessageInfo.getAddress(aMessage);		
		return new OldCommunicatedAWTEventInfo(aMessage, aProcessName, anAddress, anAWTinfo);
	}
	
	
	public String getDestinationOrSource() {
		return destinationOrSource;
	}
	
	public static String toString(String aProcessName, AWTEvent anEvent, String aGlobalId,  String aDestinationOrSource) {
		return ProcessInfo.toString(aProcessName)  +  " " + AWTEventInfo.toLocalInfoToString(anEvent, aGlobalId) + 
//				" Address(" + 
				" " + AddressedMessageInfo.ADDRESS + "(" +
				aDestinationOrSource + ")";
	}
	public String alternativeToString() {
		return toString(processName, awtEvent, globalId, destinationOrSource);
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public void setDestinationOrSource(String destinationOrSource) {
		this.destinationOrSource = destinationOrSource;
	}
	
}
