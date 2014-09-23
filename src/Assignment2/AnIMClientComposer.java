package Assignment2;

import util.session.PeerMessageListener;
import util.tags.ApplicationTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

public class AnIMClientComposer extends AGUIClientComposer {
	public static final String DEFAULT_APPLICATION_NAME = ApplicationTags.IM;

	protected SimpleList<String> history;
	protected AnIMInteractor interactor;
	protected PeerMessageListener statusInCoupler;

	public String getApplicationName() {
		return DEFAULT_APPLICATION_NAME;
	}

	public void compose(String args[]) {
		super.compose(args);
		history = new AReplicatedSimpleList<String>(communicator,
				getApplicationName());
		interactor = new AnIMInteractor(history, communicator);
		history.addObserver(interactor);
		listInCoupler = new AListInCoupler(getApplicationName(), history);
		communicator.addPeerMessageListener(listInCoupler);
		communicator.join();
	}

	public SimpleList getList() {
		return history;
	}
	
	public ListObserver getInteractor() {
		return interactor;
	}

}
