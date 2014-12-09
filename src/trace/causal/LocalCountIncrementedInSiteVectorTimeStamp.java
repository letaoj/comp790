package trace.causal;

import java.util.Map;

import trace.ot.LocalSiteCountIncremented;
import trace.ot.UserOTTimeStampInfo;
import util.session.CommunicatorSelector;

public class LocalCountIncrementedInSiteVectorTimeStamp extends VectorTimeStampInfo{

	public LocalCountIncrementedInSiteVectorTimeStamp(String aMessage, String aProcessName,
			Map<String, Integer> aUserToCount, Object aFinder) {
		super(aMessage, aProcessName, aUserToCount, aFinder);
	}
	public LocalCountIncrementedInSiteVectorTimeStamp(String aMessage, VectorTimeStampInfo aVectorTimeStampInfo

			) {
				super(aMessage, aVectorTimeStampInfo);

				
			}

	public static LocalCountIncrementedInSiteVectorTimeStamp toTraceable(String aMessage) {
		VectorTimeStampInfo aTimeStampInfo = VectorTimeStampInfo.toTraceable(aMessage);
		return new LocalCountIncrementedInSiteVectorTimeStamp(aMessage, 
				aTimeStampInfo);		
	}
	public static LocalCountIncrementedInSiteVectorTimeStamp newCase(
			String aProcessName,
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserToCount);
		LocalCountIncrementedInSiteVectorTimeStamp retVal = new LocalCountIncrementedInSiteVectorTimeStamp(aMessage, aProcessName, 
				aUserToCount, /*anInServer,*/ aFinder);
		retVal.announce();
		return retVal;
	}
	public static LocalCountIncrementedInSiteVectorTimeStamp newCase(			
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aProcessName = CommunicatorSelector.getProcessName();
		return newCase(aProcessName, aUserToCount, aFinder);
	}

}
