package Assignment5;

import util.session.Communicator;
import util.session.PeerMessageListener;
import util.tags.ApplicationTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

public class AnIMClientComposer extends AGUIClientComposer {
	public static final String DEFAULT_APPLICATION_NAME = ApplicationTags.IM;

	protected SimpleList<String> history;
	protected AnIMInteractor interactor;
	protected PeerMessageListener statusInCoupler;

	public AnIMClientComposer(Communicator communicator) {
		this.communicator = communicator;
	}
	
	public String getApplicationName() {
		return DEFAULT_APPLICATION_NAME;
	}

	public void compose() {
		history = new AReplicatedSimpleList<String>(communicator,
				getApplicationName());
		interactor = new AnIMInteractor(history, communicator);
		history.addObserver(interactor);
		listInCoupler = new AListInCoupler(getApplicationName(), history);
		communicator.addPeerMessageListener(listInCoupler);
	}
	
	public SimpleList getList() {
		return history;
	}
	
	public ListObserver getInteractor() {
		return interactor;
	}

}
