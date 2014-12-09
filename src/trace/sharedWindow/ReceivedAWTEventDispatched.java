package trace.sharedWindow;

import java.awt.AWTEvent;

import trace.echo.modular.OperationName;
import util.session.CommunicatorSelector;
import util.trace.awt.CommunicatedAWTEventInfo;

public class ReceivedAWTEventDispatched extends CommunicatedAWTEventInfo{

	public ReceivedAWTEventDispatched(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public ReceivedAWTEventDispatched(String aMessage, CommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static ReceivedAWTEventDispatched toTraceable (String aMessage) {
		CommunicatedAWTEventInfo anInfo = CommunicatedAWTEventInfo.toTraceable(aMessage);
		return new ReceivedAWTEventDispatched(aMessage, anInfo);
	}
	public static ReceivedAWTEventDispatched newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		ReceivedAWTEventDispatched retVal = new ReceivedAWTEventDispatched(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
public static ReceivedAWTEventDispatched newCase(
			
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		return ReceivedAWTEventDispatched.newCase(CommunicatorSelector.getProcessName(), anAWTEvent, aGlobalId, aSourceOrDestination, aFinder);
	}



}
