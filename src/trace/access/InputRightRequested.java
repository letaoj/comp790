package trace.access;

import trace.control.CommunicatedControlInfo;
import util.session.CommunicatorSelector;

public class InputRightRequested extends CommunicatedControlInfo{

	public InputRightRequested(String aMessage, String aProcessName,   String aDestinationOrSource, Object aFinder) {
		super(aMessage, aProcessName,  aDestinationOrSource, aFinder);
	}
	public InputRightRequested(String aMessage, CommunicatedControlInfo anInfo) {
		super(aMessage, anInfo);
	}

	public static InputRightRequested toTraceable (String aMessage) {
		CommunicatedControlInfo anInfo = CommunicatedControlInfo.toTraceable(aMessage);
		return new InputRightRequested(aMessage, anInfo);
	}
	public static InputRightRequested newCase(
			String aProcessName,
			 String aSourceOrDestination, Object aFinder) {
			
		String aMessage = toString(aProcessName,  aSourceOrDestination);
		InputRightRequested retVal = new InputRightRequested(aMessage, aProcessName, aSourceOrDestination, aFinder);
		retVal.announce();
		return retVal;
	}
	public static InputRightRequested newCase(
			 String aSourceOrDestination, Object aFinder) {
		
		return newCase(CommunicatorSelector.getProcessName(), aSourceOrDestination, aFinder);
			
		
	}

	

}
