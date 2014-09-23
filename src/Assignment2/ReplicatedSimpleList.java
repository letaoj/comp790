package Assignment2;


public interface ReplicatedSimpleList<T> extends SimpleList<T> {
	void replicatedAdd(int index, T newVal);
	void replicatedRemove(int index, T oldVal);
	// void addReplicatingObserver(HistoryObserver<ElementType> anObserver) ;

}
