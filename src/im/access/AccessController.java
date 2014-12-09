package im.access;

import java.beans.VetoableChangeListener;
import java.util.List;

import util.models.PropertyListenerRegistrar;
import util.models.Vetoer;

public interface AccessController extends VetoableChangeListener, PropertyListenerRegistrar {
	boolean canInput(String theUser);
	boolean canAdminister(String theUser);
	void addAdministrator(String newAdminsitrator);
	void addInputter(String newInputter);
	void addAdministratorLocal(String newAdminsitrator);
	void addInputterLocal(String newInputter);
	void setOwner(String theOwner);
	
	public boolean isAdministrator() ;
	public boolean isInputter() ;
	public boolean hasOwnership() ;
	public boolean isInteractiveGrant();
	public void setInteractiveGrant(boolean interactiveGrant) ;
	public List<String> getInputters() ;
	public List<String> getAdministrators() ;
	public String getOwner();

}
