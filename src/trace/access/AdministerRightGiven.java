package trace.access;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class AdministerRightGiven extends CommunicatedControlInfo{

	public AdministerRightGiven(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public AdministerRightGiven(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static AdministerRightGiven toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new AdministerRightGiven(aMessage, anInfo);
	}
	public static AdministerRightGiven newCase(
			String aProcessName,
			 String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName,  aSourceOrDestination);
		AdministerRightGiven retVal = new AdministerRightGiven(aMessage, aProcessName, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	public static AdministerRightGiven newCase(
			 String aSourceOrDestination, Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(), aSourceOrDestination, aFinder);
			
		
	}

	

}
