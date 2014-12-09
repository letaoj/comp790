package trace.locking;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class MasterLockReleased extends CommunicatedControlInfo{

	public MasterLockReleased(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public MasterLockReleased(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static MasterLockReleased toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new MasterLockReleased(aMessage, anInfo);
	}
	public static MasterLockReleased newCase(
			String aProcessName,
			 String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName,  aSourceOrDestination);
		MasterLockReleased retVal = new MasterLockReleased(aMessage, aProcessName, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	public static MasterLockReleased newCase(
			 String aSourceOrDestination, Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(), aSourceOrDestination, aFinder);
			
		
	}

	

}
