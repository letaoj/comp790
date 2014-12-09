package trace.causal;

import java.util.Map;

import trace.ot.LocalSiteCountIncremented;
import trace.ot.UserOTTimeStampInfo;
import util.session.CommunicatorSelector;

public class RemoteCountIncrementedInSiteVectorTimeStamp extends VectorTimeStampInfo{

	public RemoteCountIncrementedInSiteVectorTimeStamp(String aMessage, String aProcessName,
			Map<String, Integer> aUserToCount, Object aFinder) {
		super(aMessage, aProcessName, aUserToCount, aFinder);
	}
	public RemoteCountIncrementedInSiteVectorTimeStamp(String aMessage, VectorTimeStampInfo aVectorTimeStampInfo

			) {
				super(aMessage, aVectorTimeStampInfo);

				
			}

	public static RemoteCountIncrementedInSiteVectorTimeStamp toTraceable(String aMessage) {
		VectorTimeStampInfo aTimeStampInfo = VectorTimeStampInfo.toTraceable(aMessage);
		return new RemoteCountIncrementedInSiteVectorTimeStamp(aMessage, 
				aTimeStampInfo);		
	}
	public static RemoteCountIncrementedInSiteVectorTimeStamp newCase(
			String aProcessName,
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserToCount);
		RemoteCountIncrementedInSiteVectorTimeStamp retVal = new RemoteCountIncrementedInSiteVectorTimeStamp(aMessage, aProcessName, 
				aUserToCount, /*anInServer,*/ aFinder);
		retVal.announce();
		return retVal;
	}
	public static RemoteCountIncrementedInSiteVectorTimeStamp newCase(			
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aProcessName = CommunicatorSelector.getProcessName();
		return newCase(aProcessName, aUserToCount, aFinder);
	}

}
