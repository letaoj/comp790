package trace.sharedWindow.old;

import java.awt.AWTEvent;

import trace.echo.modular.OperationName;

public class AWTEventNotSent extends OldCommunicatedAWTEventInfo{

	public AWTEventNotSent(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public AWTEventNotSent(String aMessage, OldCommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static AWTEventNotSent toTraceable (String aMessage) {
		OldCommunicatedAWTEventInfo anInfo = OldCommunicatedAWTEventInfo.toTraceable(aMessage);
		return new AWTEventNotSent(aMessage, anInfo);
	}
	public static AWTEventNotSent newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		AWTEventNotSent retVal = new AWTEventNotSent(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
