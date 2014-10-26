package Assignment4;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import util.session.Communicator;

public class InputController implements VetoableChangeListener {
	String owner;
	Communicator communicator;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
			this);

	public InputController(Communicator communicator) {
		owner = "";
		this.communicator = communicator;
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		if (owner.equals("")) {
			
		} else {
			
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
}
