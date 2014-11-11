package Assignment5;

import util.session.Communicator;
import echo.modular.ListObserver;

public class AGUIClientComposerAndLauncher {
	protected AGUIClientComposer aGUIClientComposer;
	protected AnIMClientComposer anIMClientComposer;
	protected AnEditorClientComposer anEditorClientComposer;
	protected Communicator communicator;
	protected ListObserver historyInteractor;
	protected ListObserver topicInteractor;
	protected UI ui;
	protected ACausalDelayAndJitterParameterGUI aCausalDelayAndJitterParameterGUI;
	protected CausalityManager causalityManager;
	protected VectorTimeStamp vectorTimeStamp;

	public void composeAndLaunch(String[] args) {
		causalityManager = new CausalityManager();
		createCommunicator(args, causalityManager);

		communicator.addSessionMessageListener(causalityManager);

		anIMClientComposer = new AnIMClientComposer(communicator);
		anEditorClientComposer = new AnEditorClientComposer(communicator);
		anIMClientComposer.compose();
		anEditorClientComposer.compose();
		historyInteractor = anIMClientComposer.getInteractor();
		topicInteractor = anEditorClientComposer.getInteractor();
		communicator.join();
		launch();
	}

	public void launch() {
		launchUI();
	}

	private void createCommunicator(String[] args,
			CausalityManager causalityManager) {
		aGUIClientComposer = new AGUIClientComposer();
		aGUIClientComposer.compose(args, causalityManager);
		communicator = aGUIClientComposer.getCommunicator();
	}

	private void launchUI() {
		ui = new UI(historyInteractor, topicInteractor);
		aCausalDelayAndJitterParameterGUI = new ACausalDelayAndJitterParameterGUI(
				communicator, causalityManager);
		((AnIMInteractor) historyInteractor).setUI(ui);
		((AnEditorInteractor) topicInteractor).setUI(ui);
	}

}
