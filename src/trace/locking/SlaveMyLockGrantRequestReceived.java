package trace.locking;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class SlaveMyLockGrantRequestReceived extends CommunicatedControlInfo{

	public SlaveMyLockGrantRequestReceived(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public SlaveMyLockGrantRequestReceived(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static SlaveMyLockGrantRequestReceived toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new SlaveMyLockGrantRequestReceived(aMessage, anInfo);
	}
	public static SlaveMyLockGrantRequestReceived newCase(
			String aProcessName,
			  Object aFinder) {
			
		String aMessage = toString(aProcessName,  null);
		SlaveMyLockGrantRequestReceived retVal = new SlaveMyLockGrantRequestReceived(aMessage, aProcessName, null, aFinder);
		retVal.announce();
		return retVal;
	}
	/**
	 * @param aFinder
	 * @return
	 *  Fired if the  request is for a lock I hold
	 */
	public static SlaveMyLockGrantRequestReceived newCase(
			 Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(),  aFinder);
			
		
	}

	

}
