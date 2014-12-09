package im.access;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import trace.access.AdministerRightDeclined;
import trace.access.AdministerRightGiven;
import trace.access.AdministerRightRequested;
import trace.access.InputRightDeclined;
import trace.access.InputRightGiven;
import trace.access.InputRightRequested;
import trace.access.OwnerSet;
import trace.control.UserActionDenied;
import util.annotations.Label;
import util.annotations.Position;
import util.annotations.Visible;
import util.models.AListenableVector;
import util.session.Communicator;

public class AnAccessController implements AccessController {
	Communicator communicator;
//	List<String> inputers = new ArrayList();
	List<String> inputters = new AListenableVector();

//	List<String> administrators = new ArrayList();
	List<String> administrators = new AListenableVector();
	String owner;
	boolean interactiveGrant;
	PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public AnAccessController(Communicator theCommunicator) {
		communicator = theCommunicator;
	}
	@Visible(false)
//	@Position(1)
	public boolean hasOwnership(String theUser) {
		return theUser.equals(owner);
	}
	@Override
	@Position(1)
	@Visible(true)
	public String getOwner() {
		return owner;
	}
	@Visible(false)
//	@Position(1)
	public void setOwner(String theOwner) {
		if (theOwner.equals(owner))
			return;
		OwnerSet.newCase(theOwner, this);
		String aMessage = theOwner + " is the owner";
		System.out.println(aMessage);
//		JOptionPane.showMessageDialog(null, aMessage);
		owner = theOwner;
//		if (isOwner())
		propertyChangeSupport.firePropertyChange("owner", null, owner);
	}
	@Visible(false)
	public boolean canInput(String theUser) {
		return  inputters.contains(theUser);
	}
	@Visible(false)
	public void addInputterLocal(String newInputer) {
		
		if (inputters.contains(newInputer))
			return;
		InputRightGiven.newCase(newInputer, this);
		inputters.add(newInputer);
		String aMessage = newInputer + " can input";
//		JOptionPane.showMessageDialog(null, newInputer + " can input");
		System.out.println(aMessage);
	}
	public void addInputter(String aNewInputer) {
		if (inputters.contains(aNewInputer))
			return;
		InputRightRequested.newCase(aNewInputer, this);
		String aUserName = communicator.getClientName();
		if (!canAdminister(aUserName)) {
				String aMessage = aUserName +
						",  you do not have authorization to administer";
//				JOptionPane.showMessageDialog(null, aUserName +
//						" does not have authorization to administer");
				System.err.println(aMessage);
				InputRightDeclined.newCase(aNewInputer, this);
//				communicator.toOthers(new ARemoteInputDenied(aUserName));
			return;
		}
		communicator.toAll(new AnInputAuthorization(aNewInputer));
	}
	@Visible(false)
	public boolean canAdminister(String theUser) {
		return administrators.contains(theUser);
	}
	@Override
//	@Position(1)
	@Visible(false)
	public boolean isAdministrator() {
		return canAdminister(communicator.getClientName());
	}
	@Override
//	@Position(0)
	@Visible(false)
	public boolean isInputter() {
		return canInput(communicator.getClientName());
	}
	@Override
	@Visible(false)
	@Position(1)
	public boolean hasOwnership() {
		return hasOwnership(communicator.getClientName());
	}
	@Position(0)
	public boolean isInteractiveGrant() {
		return interactiveGrant;
	}
	@Visible(false)
	public void addAdministratorLocal(String newAdminsitrator) {
		if (administrators.contains(newAdminsitrator))
			return;
		AdministerRightGiven.newCase(newAdminsitrator, this);
		administrators.add(newAdminsitrator);
		String aMessage = newAdminsitrator + " can administer";
		System.out.println(aMessage);
//		JOptionPane.showMessageDialog(null, newAdminsitrator + " can administer");
	}
	public void addAdministrator(String newAdminsitrator) {
		if (administrators.contains(newAdminsitrator))
			return;
		AdministerRightRequested.newCase(newAdminsitrator, this);
		if (!hasOwnership(communicator.getClientName())) {
			String aMessage = communicator.getClientName() +
					", you are not the owner";
//			JOptionPane.showMessageDialog(null, communicator.getClientName() +
//					" is not owner");
			AdministerRightDeclined.newCase(newAdminsitrator, this);
			System.err.println(aMessage);
	        return;
	    }
		communicator.toAll(new AnAdministratorAuthorization(newAdminsitrator));
	}
//	@Visible(false)
//	public boolean veto(Object theInput) {
//		boolean retVal = !canInput (communicator.getClientName());
//		if (retVal) {
//			JOptionPane.showMessageDialog(null, communicator.getClientName() +
//					" does not have authorization to input");
//		}
//		return retVal;
//	}
	
	public void setInteractiveGrant(boolean interactiveGrant) {
		this.interactiveGrant = interactiveGrant;
	}
	@Override
	@Position(2)
	public List<String> getInputters() {
		return inputters;
	}
	@Override
	@Position(3)
	public List<String> getAdministrators() {
		return administrators;
	}
	@Override
	@Visible(false)
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		if (!canInput (communicator.getClientName())) {
			String aMessage = communicator.getClientName() +
					", you do not have authorization to input";
			System.err.println(aMessage);
			
			UserActionDenied.newCase(this);
			if (isInteractiveGrant())
				communicator.toAll(new ARemoteInputDenied(communicator.getClientName()));
			throw new PropertyVetoException(aMessage, evt);
					
		}		
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener aListener) {
		propertyChangeSupport.addPropertyChangeListener(aListener);		
	}
}
