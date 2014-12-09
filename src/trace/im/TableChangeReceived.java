package trace.im;

import trace.table.TableChangeInfo;
import util.trace.Traceable;

public class TableChangeReceived extends TableChangeInfo {

	public TableChangeReceived(String aMessage,
			TableChangeInfo aPropertyChangeInfo) {
		super(aMessage, aPropertyChangeInfo);
		// TODO Auto-generated constructor stub
	}

	public TableChangeReceived(String aMessage, String aKeyName,
			Object anOldValue, Object aNewValue, String aTable, Object aFinder) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable, aFinder);
		// TODO Auto-generated constructor stub
	}

	public TableChangeReceived(String aMessage, String aKeyName,
			String anOldValue, Object aNewValue, String aTable,
			Traceable aTraceable) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable, aTraceable);
		// TODO Auto-generated constructor stub
	}


	

	public TableChangeReceived(String aMessage, String aKeyName,
			Object anOldValue, Object aNewValue, String aTable,
			Traceable aTraceable) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable, aTraceable);
		// TODO Auto-generated constructor stub
	}

	public TableChangeReceived(String aMessage, String aKeyName,
			String anOldValue, String aNewValue, String aTable) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable);
		// TODO Auto-generated constructor stub
	}

	public static TableChangeReceived newCase(
			 String aKeyName,
			Object anOldValue, Object aNewValue, String aTable, Object aFinder) {			
		String aMessage = toString(aKeyName, anOldValue, aNewValue, aTable);
		TableChangeReceived retVal = new TableChangeReceived(aMessage, aKeyName, anOldValue, aNewValue, aTable,  aFinder);
		retVal.announce();
		return retVal;
	}

}
