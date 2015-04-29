package ft;

import util.session.Communicator;
import util.session.PeerMessageListener;
import util.tags.ApplicationTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;
import ft.letao.AFTManager;
import ft.letao.FTManager;

public class AnIMClientComposer extends AGUIClientComposer {
  public static final String DEFAULT_APPLICATION_NAME = ApplicationTags.IM;

  protected SimpleList<String> history;
  protected AnIMInteractor interactor;
  protected PeerMessageListener statusInCoupler;
  protected AFTManager ftManager;

  public AnIMClientComposer(Communicator communicator, AFTManager ftManager) {
    this.communicator = communicator;
    this.ftManager = ftManager;
  }

  public String getApplicationName() {
    return DEFAULT_APPLICATION_NAME;
  }

  public void compose() {
    history = new AReplicatedSimpleList<String>(communicator, getApplicationName());
    interactor = new AnIMInteractor(history, communicator);
    history.addObserver(interactor);
    listInCoupler = new AListInCoupler(getApplicationName(), history, ftManager);
    communicator.addPeerMessageListener(listInCoupler);
  }

  public SimpleList getList() {
    return history;
  }

  public ListObserver getInteractor() {
    return interactor;
  }

}
