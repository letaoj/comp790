package ft;

import util.session.Communicator;
import echo.modular.ListObserver;
import ft.letao.AFTManager;

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
    ftManager = new AFTManager();
    createCommunicator(args, ftManager);

    communicator.addSessionMessageListener(ftManager);
    communicator.addPeerMessageListener(ftManager);

    anIMClientComposer = new AnIMClientComposer(communicator);
    anEditorClientComposer = new AnEditorClientComposer(communicator);
    anIMClientComposer.compose(ftManager);
    anEditorClientComposer.compose(ftManager);
    historyInteractor = anIMClientComposer.getInteractor();
    topicInteractor = anEditorClientComposer.getInteractor();
    ftGUI = new FTGUI(communicator, ftManager);
    ftManager.setUI(ftGUI);
    ftManager.setCommunicator(communicator);
    communicator.join();
    launch();
  }

  public void launch() {
    launchUI();
  }

  private void createCommunicator(String[] args, AFTManager causalityManager) {
    aGUIClientComposer = new AGUIClientComposer();
    aGUIClientComposer.compose(args, causalityManager);
    communicator = aGUIClientComposer.getCommunicator();
  }

  private void launchUI() {
    ui = new UI(historyInteractor, topicInteractor);

    ((AnIMInteractor) historyInteractor).setUI(ui);
    ((AnEditorInteractor) topicInteractor).setUI(ui);

  }

}
