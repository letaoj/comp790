package trace.causal;

import java.util.Map;

import trace.ot.LocalSiteCountIncremented;
import trace.ot.UserOTTimeStampInfo;
import util.session.CommunicatorSelector;

public class UserAddedToVectorTimeStamp extends VectorTimeStampInfo{

	public UserAddedToVectorTimeStamp(String aMessage, String aProcessName,
			Map<String, Integer> aUserToCount, Object aFinder) {
		super(aMessage, aProcessName, aUserToCount, aFinder);
	}
	public UserAddedToVectorTimeStamp(String aMessage, VectorTimeStampInfo aVectorTimeStampInfo

			) {
				super(aMessage, aVectorTimeStampInfo);

				
			}

	public static UserAddedToVectorTimeStamp toTraceable(String aMessage) {
		VectorTimeStampInfo aTimeStampInfo = VectorTimeStampInfo.toTraceable(aMessage);
		return new UserAddedToVectorTimeStamp(aMessage, 
				aTimeStampInfo);		
	}
	public static UserAddedToVectorTimeStamp newCase(
			String aProcessName,
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserToCount);
		UserAddedToVectorTimeStamp retVal = new UserAddedToVectorTimeStamp(aMessage, aProcessName, 
				aUserToCount, /*anInServer,*/ aFinder);
		retVal.announce();
		return retVal;
	}
	public static UserAddedToVectorTimeStamp newCase(			
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aProcessName = CommunicatorSelector.getProcessName();
		return newCase(aProcessName, aUserToCount, aFinder);
	}

}
