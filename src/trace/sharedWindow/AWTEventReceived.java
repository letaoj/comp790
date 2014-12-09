package trace.sharedWindow;

import java.awt.AWTEvent;

import util.session.CommunicatorSelector;
import util.trace.awt.CommunicatedAWTEventInfo;

public class AWTEventReceived extends CommunicatedAWTEventInfo{

	public AWTEventReceived(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public AWTEventReceived(String aMessage, CommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static AWTEventReceived toTraceable (String aMessage) {
		CommunicatedAWTEventInfo anInfo = CommunicatedAWTEventInfo.toTraceable(aMessage);
		return new AWTEventReceived(aMessage, anInfo);
	}
	public static AWTEventReceived newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		AWTEventReceived retVal = new AWTEventReceived(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
public static AWTEventReceived newCase(
			
			AWTEvent anAWTEvent, String aGlobalId,  String aSourceOrDestination, Object aFinder) {
			
		return AWTEventReceived.newCase(CommunicatorSelector.getProcessName(), anAWTEvent, aGlobalId, aSourceOrDestination, aFinder);
	}

}
