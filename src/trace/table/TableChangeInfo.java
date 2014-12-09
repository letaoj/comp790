package trace.table;
import java.util.Arrays;
import java.util.List;

import util.trace.Traceable;
import util.trace.TraceableInfo;
public class TableChangeInfo extends TraceableInfo {
	protected String keyName;	
	protected Object oldValue;
	protected Object newValue;
	protected String table;
	

	public static final String TABLE_CHANGE = "TableChange";
	public static String[] equalPropertiesArray = {"KeyName",  "NewValue", "Table"};
	public static List<String> equalPropertiesList = Arrays.asList(equalPropertiesArray);

	public TableChangeInfo(String aMessage, String aKeyName,  Object anOldValue, Object aNewValue, String aTable, Object aFinder) {
		super(aMessage,  aFinder);
		keyName = aKeyName;
		oldValue = anOldValue;
		newValue = aNewValue;
		table = aTable;
		
	}
	protected void setEqualPropertiesList() {
		super.setEqualPropertiesList();

		equalPropertiesList = Arrays.asList(equalPropertiesArray);


	}
	
	
	public TableChangeInfo(String aMessage, String aPropertyName,  String anOldValue, String aNewValue, String aBean) {
//		this("", aName, anIndex, anElement, aList, (Object) null); 
		this("", aPropertyName, anOldValue, aNewValue, aBean, (Object) null);
	}
	public TableChangeInfo(String aMessage, 
			String aPropertyName,  Object anOldValue, Object aNewValue, String aBean, Traceable aTraceable

			) {
		super(aMessage, aTraceable);
		keyName = aPropertyName;
		oldValue = anOldValue;
		newValue = aNewValue;
		table = aBean;
	}
	public TableChangeInfo(String aMessage, 
			TableChangeInfo aPropertyChangeInfo

			) {
		this(aMessage, 
				aPropertyChangeInfo.getKeyName(),
				aPropertyChangeInfo.getOldValue(),
				aPropertyChangeInfo.getNewValue(), 
				aPropertyChangeInfo.getTable(),
//				getOperationName(aMessage), 
//				
//				getIndex(
//						getOperationArgs(aMessage, getOperationName(aMessage).toString() )),
//				getElement(
//						getOperationArgs(aMessage, getOperationName(aMessage).toString() )),

				aPropertyChangeInfo);
	}
	
	public static TableChangeInfo toTraceable (String aMessage) {
		Traceable aTraceable = TraceableInfo.toTraceable(aMessage);
		
		return new TableChangeInfo(aMessage,
				getKeyName(aMessage), 
				getOldValue(aMessage), 
				getNewValue(aMessage), 
				getTable(aMessage),
				aTraceable);
				
	}
	
	public static String getOldValue(String aMessage) {
		return getArgs(aMessage, TABLE_CHANGE).get(1);
	}
	
	public static String getNewValue(String aMessage) {
		return getArgs(aMessage, TABLE_CHANGE).get(2);
	}
	
	public static String getTable(String aMessage) {
		return getArgs(aMessage, TABLE_CHANGE).get(3);
	}
	
	public static String getKeyName(String aMessage) {
		return getArgs(aMessage, TABLE_CHANGE).get(0);
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
		return toLocalInfoToString(keyName, oldValue, newValue, table);
	}
	public String alternativeToString() {
//		return "ListEdit(" +  toString(operationName, index, element) + ")";
		return 
//				LIST_EDIT + "(" +
//		"ListEdit(" +  
		
		toLocalInfoToString(); 
//		")";
	}
	
	public boolean equalsEdit(TableChangeInfo other) {
		return keyName.equals(other.getKeyName()) &&
				newValue.equals(other.getNewValue()) &&
				table.equals(other.getTable());
	}
	public String getKeyName() {
		return keyName;
	}
	public Object getOldValue() {
		return oldValue;
	}
	public Object getNewValue() {
		return newValue;
	}
	public String getTable() {
		return table;
	}
	
}
