package Assignment5;

import echo.modular.SimpleList;


public interface ReplicatedSimpleList<T> extends SimpleList<T> {
	void replicatedAdd(int index, T newVal);
	void replicatedRemove(int index, T oldVal);
	// void addReplicatingObserver(HistoryObserver<ElementType> anObserver) ;

}
