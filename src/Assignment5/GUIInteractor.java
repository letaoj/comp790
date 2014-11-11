package Assignment5;

import util.annotations.Tags;
import util.session.Communicator;
import util.tags.InteractiveTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

@Tags({ InteractiveTags.INTERACTOR })
public class GUIInteractor<T> implements ListObserver {
	protected SimpleList<T> list;
	protected Communicator communicator;
	protected UI ui;
	protected boolean callback = true;

	public GUIInteractor(SimpleList<T> list, Communicator communicator) {
		this.list = list;
		this.communicator = communicator;
	}

	protected void addToList(int index, T newValue) {
		if (callback) {
			((ReplicatedSimpleList) list).replicatedAdd(index, newValue);
		} else {
			((ReplicatedSimpleList) list).add(index, newValue);
		}
		callback = true;
	}

	@Override
	public void elementAdded(int anIndex, Object aNewValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void elementRemoved(int anIndex, Object aNewValue) {
		// TODO Auto-generated method stub

	}
}
