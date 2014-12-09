package im.access;

import im.AReplicatedSimpleList;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.List;

import util.models.Vetoer;
import util.session.Communicator;

public class AControlledReplicatedHistory<ElementType> 
		extends AReplicatedSimpleList<ElementType> 
		implements ControlledReplicatedHistory<ElementType>  {
	VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);
//	List<Vetoer> vetoers = new ArrayList();
	public AControlledReplicatedHistory(Communicator theCommunicator) {
		super(theCommunicator);		
	}
	public synchronized void replicatedAdd(ElementType aNewValue) {
		try {
			vetoableChangeSupport.fireVetoableChange("IMHistory", null, aNewValue);			
		} catch (PropertyVetoException e) {
//			System.out.println(e.getMessage());
			return;
		}
		super.replicatedAdd(aNewValue);
		
	}		
//	public void addVetoer(Vetoer theVetoer) {
//		vetoers.add(theVetoer);		
//	}
//	public void removeVetoer(Vetoer theVetoer) {
//		vetoers.remove(theVetoer);		
//	}
//	boolean vetoed(Object theValue) {
//		for (Vetoer vetoer:vetoers) {
//			if (vetoer.veto(theValue)) return true;
//		}
//		return false;
//	}
	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoableChangeSupport.addVetoableChangeListener(listener);
		
	}
	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoableChangeSupport.removeVetoableChangeListener(listener);
		
	}
}
