package im.delay.p2p;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import util.annotations.Column;
import util.annotations.Row;
import util.session.Communicator;
import bus.uigen.ObjectEditor;

public class AP2PDelayAndJitterParameters extends AJitterParameters implements P2PDelayAndJitterParameters{
	boolean isRelayed;
	
	int minimumDelayToPeer;
//	int delayVariation;
//	protected Communicator communicator;
	protected String peerName = "";
//	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public AP2PDelayAndJitterParameters(Communicator aCommunicator, String aPeerName) {
		super(aCommunicator);
		peerName = aPeerName;
	}
	public AP2PDelayAndJitterParameters(Communicator aCommunicator) {
		super(aCommunicator);
	}
	@Row(1)
	public int getMinimumDelayToPeer() {
		return minimumDelayToPeer;
	}
	
	public void setMinimumDelayToPeer(int newValue) {
		int oldValue = minimumDelayToPeer;
		this.minimumDelayToPeer = newValue;
		communicator.setMinimumDelayToPeer(peerName,  newValue);
		propertyChangeSupport.firePropertyChange("minimumDelayToPeer", oldValue, newValue);

	}
	@Row(2)
	public int getDelayVariation() {
		return super.getDelayVariation();
	}
//	public void setDelayVariation(int newValue) {
//		int oldValue = delayVariation;
//		this.delayVariation = newValue;
//		communicator.setDelayVariation(newValue);
//		propertyChangeSupport.firePropertyChange("delayVariation", oldValue, newValue);
//
//	}
	@Override
	@Row(0)
	public String getPeerName() {
		return peerName;
	}
	@Override
	public void setPeerName(String newValue) {
		String oldValue = peerName;
		this.peerName = newValue;
		communicator.setMinimumDelayToPeer(newValue,  minimumDelayToPeer);
		communicator.setDelayVariation(delayVariation);
		propertyChangeSupport.firePropertyChange("peerName", oldValue, newValue);

	}
	@Row(3)
	@Column(0)
	public void noJitter() {
//		setDelayVariation(0);
		super.noJitter();
	}
	@Row(3)
	@Column(1)
	public void noDelay() {
		setMinimumDelayToPeer(0);
	}
	@Row(4)
	public boolean isRelayed() {
		return communicator.getMessageSenderRunnable().isRelayedMode();
		
	}
	public void setRelayed(boolean newVal) {
		this.isRelayed = isRelayed;
		communicator.getMessageSenderRunnable().setRelayedMode(newVal);
	}
	public static void main (String[] anArgs) {
	}
//	@Override
//	public void addPropertyChangeListener(PropertyChangeListener aListener) {
//		propertyChangeSupport.addPropertyChangeListener(aListener);
//	}
}
