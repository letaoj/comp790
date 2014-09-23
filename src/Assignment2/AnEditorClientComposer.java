package Assignment2;

import util.tags.ApplicationTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

public class AnEditorClientComposer extends AGUIClientComposer {
	public static final String DEFAULT_APPLICATION_NAME = ApplicationTags.EDITOR;

	protected SimpleList<Character> topic;
	protected AnEditorInteractor interactor;

	public String getApplicationName() {
		return DEFAULT_APPLICATION_NAME;
	}

	public void compose(String args[]) {
		super.compose(args);
		topic = new AReplicatedSimpleList<Character>(communicator,
				getApplicationName());
		interactor = new AnEditorInteractor(topic, communicator);
		topic.addObserver(interactor);
		listInCoupler = new AListInCoupler(getApplicationName(), topic);
		communicator.addPeerMessageListener(listInCoupler);
		communicator.join();
	}

	public SimpleList getList() {
		return topic;
	}

	public ListObserver getInteractor() {
		return interactor;
	}

}
