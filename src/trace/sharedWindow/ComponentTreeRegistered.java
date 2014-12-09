package trace.sharedWindow;

import java.awt.AWTEvent;

import trace.echo.modular.OperationName;
import util.session.CommunicatorSelector;
import util.trace.awt.CommunicatedAWTEventInfo;

public class ComponentTreeRegistered extends CommunicatedAWTEventInfo{

	public ComponentTreeRegistered(String aMessage, String aProcessName, AWTEvent anAWTEvent, String aGlobalId, String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName, anAWTEvent, aGlobalId,  aDestinationOrSource, aFinder);
	}
	public ComponentTreeRegistered(String aMessage, CommunicatedAWTEventInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static ComponentTreeRegistered toTraceable (String aMessage) {
		CommunicatedAWTEventInfo anInfo = CommunicatedAWTEventInfo.toTraceable(aMessage);
		return new ComponentTreeRegistered(aMessage, anInfo);
	}
	public static ComponentTreeRegistered newCase(
			String aProcessName,
			AWTEvent anAWTEvent, String aGlobalId, String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName, anAWTEvent, aGlobalId, aSourceOrDestination);
		ComponentTreeRegistered retVal = new ComponentTreeRegistered(aMessage, aProcessName, anAWTEvent, aGlobalId,  aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	/*
	 * the event is the one that triggered the regus
	 */
	public static ComponentTreeRegistered newCase(
			AWTEvent aTriggeringEvent, String aRootId, Object aFinder) {		
		return ComponentTreeRegistered.newCase(CommunicatorSelector.getProcessName(), aTriggeringEvent,  aRootId, "", aFinder);
	}

}
