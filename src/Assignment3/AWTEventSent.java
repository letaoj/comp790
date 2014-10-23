package Assignment3;

import java.awt.AWTEvent;

import util.session.CommunicatorSelector;
import util.trace.awt.CommunicatedAWTEventInfo;

public class AWTEventSent extends CommunicatedAWTEventInfo{

	public AWTEventSent(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public AWTEventSent(String aMessage, CommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static AWTEventSent toTraceable (String aMessage) {
		CommunicatedAWTEventInfo anInfo = CommunicatedAWTEventInfo.toTraceable(aMessage);
		return new AWTEventSent(aMessage, anInfo);
	}
	public static AWTEventSent newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		AWTEventSent retVal = new AWTEventSent(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	
	public static AWTEventSent newCase(
			
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		return AWTEventSent.newCase(CommunicatorSelector.getProcessName(), anAWTEvent, aGlobalId, aSourceOrDestination, aFinder);
	}

}
