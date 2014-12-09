package im;

import util.session.Communicator;
import echo.modular.SimpleList;
import echo.modular.ListObserver;

public interface ReplicatedSimpleList<ElementType> extends SimpleList<ElementType> {
	void replicatedAdd(ElementType newVal);
//	void addReplicatingObserver(HistoryObserver<ElementType> anObserver) ;

	Communicator getCommunicator();

	void setCommunicator(Communicator communicator);

	

}
