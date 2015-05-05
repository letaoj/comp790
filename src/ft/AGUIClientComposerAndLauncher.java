package ft;

import util.session.Communicator;
import echo.modular.ListObserver;
import ft.letao.AFTManager;
import ft.letao.ARegistry;

public class AGUIClientComposerAndLauncher {
  protected AGUIClientComposer aGUIClientComposer;
  protected AnIMClientComposer anIMClientComposer;
  protected AnEditorClientComposer anEditorClientComposer;
  protected Communicator communicator;
  protected ListObserver historyInteractor;
  protected ListObserver topicInteractor;
  protected UI ui;
  protected FTGUI ftGUI;
  protected AFTManager ftManager;

  public void composeAndLaunch(String[] args) {
    createCommunicator(args);
    ftManager = (AFTManager) ARegistry.getFTManager(communicator);

    anIMClientComposer = new AnIMClientComposer(communicator, ftManager);
    anEditorClientComposer = new AnEditorClientComposer(communicator, ftManager);
    anIMClientComposer.compose();
    anEditorClientComposer.compose();
    historyInteractor = anIMClientComposer.getInteractor();
    topicInteractor = anEditorClientComposer.getInteractor();
    ftGUI = new FTGUI(communicator);
    ftManager.setUI(ftGUI);
    communicator.join();
    launch();
  }

  public void launch() {
    launchUI();
  }

  private void createCommunicator(String[] args) {
    aGUIClientComposer = new AGUIClientComposer();
    aGUIClientComposer.compose(args);
    communicator = aGUIClientComposer.getCommunicator();
  }

  private void launchUI() {
    ui = new UI(historyInteractor, topicInteractor);

    ((AnIMInteractor) historyInteractor).setUI(ui);
    ((AnEditorInteractor) topicInteractor).setUI(ui);

  }

}
