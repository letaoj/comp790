package trace.bean;

import util.trace.Traceable;

public class PropertyChangeMade extends PropertyChangeInfo {

	public PropertyChangeMade(String aMessage,
			PropertyChangeInfo aPropertyChangeInfo) {
		super(aMessage, aPropertyChangeInfo);
		// TODO Auto-generated constructor stub
	}

	public PropertyChangeMade(String aMessage, String aPropertyName,
			Object anOldValue, Object aNewValue, String aBean, Object aFinder) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean, aFinder);
		// TODO Auto-generated constructor stub
	}

	public PropertyChangeMade(String aMessage, String aPropertyName,
			String anOldValue, Object aNewValue, String aBean,
			Traceable aTraceable) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean, aTraceable);
		// TODO Auto-generated constructor stub
	}


	

	public PropertyChangeMade(String aMessage, String aPropertyName,
			Object anOldValue, Object aNewValue, String aBean,
			Traceable aTraceable) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean, aTraceable);
		// TODO Auto-generated constructor stub
	}

	public PropertyChangeMade(String aMessage, String aPropertyName,
			String anOldValue, String aNewValue, String aBean) {
		super(aMessage, aPropertyName, anOldValue, aNewValue, aBean);
		// TODO Auto-generated constructor stub
	}

	public static PropertyChangeMade newCase(
			 String aPropertyName,
			Object anOldValue, Object aNewValue, String aBean, Object aFinder) {			
		String aMessage = toString(aPropertyName, anOldValue, aNewValue, aBean);
		PropertyChangeMade retVal = new PropertyChangeMade(aMessage, aPropertyName, anOldValue, aNewValue, aBean,  aFinder);
		retVal.announce();
		return retVal;
	}

}
