package trace.locking;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class SlaveMyLockGrantMadeReceived extends CommunicatedControlInfo{

	public SlaveMyLockGrantMadeReceived(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public SlaveMyLockGrantMadeReceived(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static SlaveMyLockGrantMadeReceived toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new SlaveMyLockGrantMadeReceived(aMessage, anInfo);
	}
	public static SlaveMyLockGrantMadeReceived newCase(
			String aProcessName,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName,  null);
		SlaveMyLockGrantMadeReceived retVal = new SlaveMyLockGrantMadeReceived(aMessage, aProcessName, null, aFinder);
		retVal.announce();
		return retVal;
	}
	
	/**
	 * @param aFinder
	 * @return
	 *  Fired if the lock was granted to me
	 */
	public static SlaveMyLockGrantMadeReceived newCase(
			 Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(),  aFinder);
			
		
	}

	

}
