package trace.bean;
import java.util.Arrays;
import java.util.List;

import util.trace.Traceable;
import util.trace.TraceableInfo;
public class PropertyChangeInfo extends TraceableInfo {
	protected String propertyName;	
	protected Object oldValue;
	protected Object newValue;
	protected String bean;
	

	public static final String PROPERTY_CHANGE = "PropertyChange";
	public static String[] equalPropertiesArray = {"PropertyName",  "NewValue", "Bean"};
	public static List<String> equalPropertiesList = Arrays.asList(equalPropertiesArray);

	public PropertyChangeInfo(String aMessage, String aPropertyName,  Object anOldValue, Object aNewValue, String aBean, Object aFinder) {
		super(aMessage,  aFinder);
		propertyName = aPropertyName;
		oldValue = anOldValue;
		newValue = aNewValue;
		bean = aBean;
		
	}
	protected void setEqualPropertiesList() {
		super.setEqualPropertiesList();

		equalPropertiesList = Arrays.asList(equalPropertiesArray);


	}
	
	
	public PropertyChangeInfo(String aMessage, String aPropertyName,  String anOldValue, String aNewValue, String aBean) {
//		this("", aName, anIndex, anElement, aList, (Object) null); 
		this("", aPropertyName, anOldValue, aNewValue, aBean, (Object) null);
	}
	public PropertyChangeInfo(String aMessage, 
			String aPropertyName,  Object anOldValue, Object aNewValue, String aBean, Traceable aTraceable

			) {
		super(aMessage, aTraceable);
		propertyName = aPropertyName;
		oldValue = anOldValue;
		newValue = aNewValue;
		bean = aBean;
	}
	public PropertyChangeInfo(String aMessage, 
			PropertyChangeInfo aPropertyChangeInfo

			) {
		this(aMessage, 
				aPropertyChangeInfo.getPropertyName(),
				aPropertyChangeInfo.getOldValue(),
				aPropertyChangeInfo.getNewValue(), 
				aPropertyChangeInfo.getBean(),
//				getOperationName(aMessage), 
//				
//				getIndex(
//						getOperationArgs(aMessage, getOperationName(aMessage).toString() )),
//				getElement(
//						getOperationArgs(aMessage, getOperationName(aMessage).toString() )),

				aPropertyChangeInfo);
	}
	
	public static PropertyChangeInfo toTraceable (String aMessage) {
		Traceable aTraceable = TraceableInfo.toTraceable(aMessage);
		
		return new PropertyChangeInfo(aMessage,
				getPropertyName(aMessage), 
				getOldValue(aMessage), 
				getNewValue(aMessage), 
				getBean(aMessage),
				aTraceable);
				
	}
	
	public static String getOldValue(String aMessage) {
		return getArgs(aMessage, PROPERTY_CHANGE).get(1);
	}
	
	public static String getNewValue(String aMessage) {
		return getArgs(aMessage, PROPERTY_CHANGE).get(2);
	}
	
	public static String getBean(String aMessage) {
		return getArgs(aMessage, PROPERTY_CHANGE).get(3);
	}
	
	public static String getPropertyName(String aMessage) {
		return getArgs(aMessage, PROPERTY_CHANGE).get(0);
	}
	
	
	
	public static String toString(String aPropertyName, Object anOldValue, Object aNewValue, String aBean) {
		
		return toString(System.currentTimeMillis()) + " " +
			toLocalInfoToString(aPropertyName, anOldValue, aNewValue, aBean);
//				"(" + aPropertyName 
//				+ "," + aNewValue
//				+ ","  + aBean
//			+ ")";
				
	}
	public static String toLocalInfoToString(String aPropertyName, Object anOldValue, Object aNewValue, String aBean) {
		return 
				"(" + aPropertyName 
				+ "," + aNewValue
				+ ","  + aBean
			+ ")";
				
	}
	public String toLocalInfoToString() {
		return toLocalInfoToString(propertyName, oldValue, newValue, bean);
	}
	public String alternativeToString() {
//		return "ListEdit(" +  toString(operationName, index, element) + ")";
		return 
//				LIST_EDIT + "(" +
//		"ListEdit(" +  
		
		toLocalInfoToString(); 
//		")";
	}
	
	public boolean equalsEdit(PropertyChangeInfo other) {
		return propertyName.equals(other.getPropertyName()) &&
				newValue.equals(other.getNewValue()) &&
				bean.equals(other.getBean());
	}
	public String getPropertyName() {
		return propertyName;
	}
	public Object getOldValue() {
		return oldValue;
	}
	public Object getNewValue() {
		return newValue;
	}
	public String getBean() {
		return bean;
	}
	
}
