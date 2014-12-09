package trace.causal;

import java.util.Map;

import trace.ot.LocalSiteCountIncremented;
import trace.ot.UserOTTimeStampInfo;
import util.session.CommunicatorSelector;

public class VectorTimeStampCopiedAndNewBufferCreated extends VectorTimeStampInfo{

	public VectorTimeStampCopiedAndNewBufferCreated(String aMessage, String aProcessName,
			Map<String, Integer> aUserToCount, Object aFinder) {
		super(aMessage, aProcessName, aUserToCount, aFinder);
	}
	public VectorTimeStampCopiedAndNewBufferCreated(String aMessage, VectorTimeStampInfo aVectorTimeStampInfo

			) {
				super(aMessage, aVectorTimeStampInfo);

				
			}

	public static VectorTimeStampCopiedAndNewBufferCreated toTraceable(String aMessage) {
		VectorTimeStampInfo aTimeStampInfo = VectorTimeStampInfo.toTraceable(aMessage);
		return new VectorTimeStampCopiedAndNewBufferCreated(aMessage, 
				aTimeStampInfo);		
	}
	public static VectorTimeStampCopiedAndNewBufferCreated newCase(
			String aProcessName,
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aMessage = toString(aProcessName, aUserToCount);
		VectorTimeStampCopiedAndNewBufferCreated retVal = new VectorTimeStampCopiedAndNewBufferCreated(aMessage, aProcessName, 
				aUserToCount, /*anInServer,*/ aFinder);
		retVal.announce();
		return retVal;
	}
	public static VectorTimeStampCopiedAndNewBufferCreated newCase(			
			Map<String, Integer> aUserToCount, 
			Object aFinder) {
		String aProcessName = CommunicatorSelector.getProcessName();
		return newCase(aProcessName, aUserToCount, aFinder);
	}

}
