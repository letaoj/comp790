package trace.access;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class AdministerRightDeclined extends CommunicatedControlInfo{

	public AdministerRightDeclined(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public AdministerRightDeclined(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static AdministerRightDeclined toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new AdministerRightDeclined(aMessage, anInfo);
	}
	public static AdministerRightDeclined newCase(
			String aProcessName,
			 String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName,  aSourceOrDestination);
		AdministerRightDeclined retVal = new AdministerRightDeclined(aMessage, aProcessName, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	public static AdministerRightDeclined newCase(
			 String aSourceOrDestination, Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(), aSourceOrDestination, aFinder);
			
		
	}

	

}
