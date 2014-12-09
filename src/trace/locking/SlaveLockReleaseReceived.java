package trace.locking;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class SlaveLockReleaseReceived extends CommunicatedControlInfo{

	public SlaveLockReleaseReceived(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public SlaveLockReleaseReceived(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static SlaveLockReleaseReceived toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new SlaveLockReleaseReceived(aMessage, anInfo);
	}
	public static SlaveLockReleaseReceived newCase(
			String aProcessName,
			 String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName,  aSourceOrDestination);
		SlaveLockReleaseReceived retVal = new SlaveLockReleaseReceived(aMessage, aProcessName, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	public static SlaveLockReleaseReceived newCase(
			Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(), "", aFinder);
			
		
	}

	

}
