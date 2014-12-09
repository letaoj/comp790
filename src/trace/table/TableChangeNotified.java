package trace.table;

import util.trace.Traceable;

public class TableChangeNotified extends TableChangeInfo {

	public TableChangeNotified(String aMessage,
			TableChangeInfo aPropertyChangeInfo) {
		super(aMessage, aPropertyChangeInfo);
		// TODO Auto-generated constructor stub
	}

	public TableChangeNotified(String aMessage, String aKeyName,
			Object anOldValue, Object aNewValue, String aTable, Object aFinder) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable, aFinder);
		// TODO Auto-generated constructor stub
	}

	public TableChangeNotified(String aMessage, String aKeyName,
			String anOldValue, Object aNewValue, String aTable,
			Traceable aTraceable) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable, aTraceable);
		// TODO Auto-generated constructor stub
	}


	

	public TableChangeNotified(String aMessage, String aKeyName,
			Object anOldValue, Object aNewValue, String aTable,
			Traceable aTraceable) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable, aTraceable);
		// TODO Auto-generated constructor stub
	}

	public TableChangeNotified(String aMessage, String aKeyName,
			String anOldValue, String aNewValue, String aTable) {
		super(aMessage, aKeyName, anOldValue, aNewValue, aTable);
		// TODO Auto-generated constructor stub
	}

	public static TableChangeNotified newCase(
			 String aKeyName,
			Object anOldValue, Object aNewValue, String aTable, Object aFinder) {			
		String aMessage = toString(aKeyName, anOldValue, aNewValue, aTable);
		TableChangeNotified retVal = new TableChangeNotified(aMessage, aKeyName, anOldValue, aNewValue, aTable,  aFinder);
		retVal.announce();
		return retVal;
	}

}
