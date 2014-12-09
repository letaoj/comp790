package trace.sharedWindow.old;

import java.awt.AWTEvent;

import trace.echo.modular.OperationName;

public class ComponentToGlobalId extends OldCommunicatedAWTEventInfo{

	public ComponentToGlobalId(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public ComponentToGlobalId(String aMessage, OldCommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static ComponentToGlobalId toTraceable (String aMessage) {
		OldCommunicatedAWTEventInfo anInfo = OldCommunicatedAWTEventInfo.toTraceable(aMessage);
		return new ComponentToGlobalId(aMessage, anInfo);
	}
	public static ComponentToGlobalId newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		ComponentToGlobalId retVal = new ComponentToGlobalId(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}

}
