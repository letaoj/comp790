package im.access;

import util.models.PropertyVetoerRegistrar;
import util.models.Vetoer;
import util.models.VetoerRegisterer;
import im.ReplicatedSimpleList;

public interface ControlledReplicatedHistory<ElementType> extends ReplicatedSimpleList<ElementType>, PropertyVetoerRegistrar {

	

//	public void addVetoer(Vetoer theVetoer);
//	public void removeVetoer(Vetoer theVetoer);
}
