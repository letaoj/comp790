package trace.control;
import util.trace.session.CommunicatedInfo;
public class CommunicatedControlInfo extends CommunicatedInfo {


	public CommunicatedControlInfo(String aMessage,
			String aProcessName,
			String aDestinationOrSource,
			Object aFinder) {
		super(aMessage, aProcessName, aDestinationOrSource, aFinder);
		
		
	}
	
	
	
	
	public CommunicatedControlInfo() {
		this("",   null); 
		
	}
	public CommunicatedControlInfo(String aMessage, 
			
			CommunicatedInfo aTraceable
//			String aProcessName,			
//			Long aTimeStamp,
//			String aThreadName,
//			Object aFinder
			) {
		super(aMessage, aTraceable);
		
		
	}
	
	
	public static CommunicatedControlInfo toTraceable (String aMessage) {
		CommunicatedInfo aTraceable = CommunicatedInfo.toTraceable(aMessage);
		
		
		return new CommunicatedControlInfo(aMessage, aTraceable);
				
	}
	
//	public static String getListEdit(String aMessage) {
//		return getArgOfCompositeDescriptor(aMessage, LIST_EDIT);
//	}
//	
//	public static OperationName getOperationName(String aMessage) {
//		String aListEdit = getListEdit(aMessage);
//		aListEdit = aListEdit.trim();
//		int aNameEndIndex = aListEdit.indexOf("(");
//		String aName = aListEdit.substring(0, aNameEndIndex);
//		return OperationName.fromString(aName);
//	}
//	
	
	
		
	
	
	
	
//	public String toLocalInfoToString() {
//		return toLocalInfoToString(operationName, index, element, list); 
//	}
	
}
