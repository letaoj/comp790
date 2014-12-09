package trace.sharedWindow.old;

import java.awt.AWTEvent;

import trace.echo.modular.OperationName;

public class GlobalIdToComponent extends OldCommunicatedAWTEventInfo{

	public GlobalIdToComponent(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public GlobalIdToComponent(String aMessage, OldCommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static GlobalIdToComponent toTraceable (String aMessage) {
		OldCommunicatedAWTEventInfo anInfo = OldCommunicatedAWTEventInfo.toTraceable(aMessage);
		return new GlobalIdToComponent(aMessage, anInfo);
	}
	public static GlobalIdToComponent newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		GlobalIdToComponent retVal = new GlobalIdToComponent(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
