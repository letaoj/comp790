package trace.bean;

import util.trace.Traceable;

public class PropertyChangeObserved extends PropertyChangeInfo {

	public PropertyChangeObserved(String aMessage,
			PropertyChangeInfo aPropertyChangeInfo) {
		super(aMessage, aPropertyChangeInfo);
		// TODO Auto-generated constructor stub
	}

	public PropertyChangeObserved(String aMessage, String aPropertyName,
			Object anOldValue, Object aNewValue, String aBean, Object aFinder) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean, aFinder);
		// TODO Auto-generated constructor stub
	}

	public PropertyChangeObserved(String aMessage, String aPropertyName,
			String anOldValue, Object aNewValue, String aBean,
			Traceable aTraceable) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean, aTraceable);
		// TODO Auto-generated constructor stub
	}


	

	public PropertyChangeObserved(String aMessage, String aPropertyName,
			Object anOldValue, Object aNewValue, String aBean,
			Traceable aTraceable) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean, aTraceable);
		// TODO Auto-generated constructor stub
	}

	public PropertyChangeObserved(String aMessage, String aPropertyName,
			String anOldValue, String aNewValue, String aBean) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean);
		// TODO Auto-generated constructor stub
	}

	public static PropertyChangeObserved newCase(
			 String aPropertyName,
			Object anOldValue, Object aNewValue, String aBean, Object aFinder) {			
		String aMessage = toString(aPropertyName, anOldValue, aNewValue, aBean);
		PropertyChangeObserved retVal = new PropertyChangeObserved(aMessage, aPropertyName, anOldValue, aNewValue, aBean,  aFinder);
		retVal.announce();
		return retVal;
	}

}
