package Assignment2;

import echo.modular.ListObserver;

public class AGUIClientComposerAndLauncher {
	protected AnIMClientComposer anIMClientComposer;
	protected AnEditorClientComposer anEditorClientComposer;
	protected ListObserver historyInteractor;
	protected ListObserver topicInteractor;
	protected UI ui;

	public void composeAndLaunch(String[] args) {
		String[] args1 = { args[0], args[1], args[2], args[3], args[5] };
		String[] args2 = { args[0], args[1], args[2], args[4], args[5] };
		anIMClientComposer = new AnIMClientComposer();
		anIMClientComposer.compose(args1);
		anEditorClientComposer = new AnEditorClientComposer();
		anEditorClientComposer.compose(args2);
		historyInteractor = anIMClientComposer.getInteractor();
		topicInteractor = anEditorClientComposer.getInteractor();
		launch();
	}

	public void launch() {
		launchUI();
	}

	private void launchUI() {
		ui = new UI(historyInteractor, topicInteractor);
		((AnIMInteractor) historyInteractor).setUI(ui);
		((AnEditorInteractor) topicInteractor).setUI(ui);
	}
}
