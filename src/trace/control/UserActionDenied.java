package trace.control;

import util.session.CommunicatorSelector;

public class UserActionDenied extends CommunicatedControlInfo{

	public UserActionDenied(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public UserActionDenied(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static UserActionDenied toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new UserActionDenied(aMessage, anInfo);
	}
	public static UserActionDenied newCase(
			String aProcessName,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName,  null);
		UserActionDenied retVal = new UserActionDenied(aMessage, aProcessName, null, aFinder);
		retVal.announce();
		return retVal;
	}
	public static UserActionDenied newCase(
			 Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(),  aFinder);
			
		
	}

	

}
