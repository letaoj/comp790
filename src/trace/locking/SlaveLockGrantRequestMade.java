package trace.locking;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class SlaveLockGrantRequestMade extends CommunicatedControlInfo{

	public SlaveLockGrantRequestMade(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public SlaveLockGrantRequestMade(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static SlaveLockGrantRequestMade toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new SlaveLockGrantRequestMade(aMessage, anInfo);
	}
	public static SlaveLockGrantRequestMade newCase(
			String aProcessName,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName,  null);
		SlaveLockGrantRequestMade retVal = new SlaveLockGrantRequestMade(aMessage, aProcessName, null, aFinder);
		retVal.announce();
		return retVal;
	}
	public static SlaveLockGrantRequestMade newCase(
			 Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(),  aFinder);
			
		
	}

	

}
