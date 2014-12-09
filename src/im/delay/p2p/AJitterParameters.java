package im.delay.p2p;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.annotations.Column;
import util.annotations.Row;
import util.session.Communicator;
import bus.uigen.ObjectEditor;

public class AJitterParameters implements JitterParameters{
//	int minimumDelayToPeer;
	protected int delayVariation;
	protected Communicator communicator;
//	String peerName = "";
	protected PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
//	
//	public AJitterParameters(Communicator aCommunicator, String aPeerName) {
//		communicator = aCommunicator;
//		peerName = aPeerName;
//	}
	public AJitterParameters(Communicator aCommunicator) {
		communicator = aCommunicator;
	}
	
	@Row(2)
	public int getDelayVariation() {
		return delayVariation;
	}
	public void setDelayVariation(int newValue) {
		int oldValue = delayVariation;
		this.delayVariation = newValue;
		communicator.setDelayVariation(newValue);
		propertyChangeSupport.firePropertyChange("delayVariation", oldValue, newValue);

	}
	
	public void noJitter() {
		setDelayVariation(0);
	}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);
	}
}
