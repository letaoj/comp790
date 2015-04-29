package ft;

import util.session.Communicator;
import util.tags.ApplicationTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;
import ft.letao.AFTManager;
import ft.letao.FTManager;

public class AnEditorClientComposer extends AGUIClientComposer {
  public static final String DEFAULT_APPLICATION_NAME = ApplicationTags.EDITOR;

  protected SimpleList<Character> topic;
  protected AnEditorInteractor interactor;
  protected AFTManager ftManager;

  public AnEditorClientComposer(Communicator communicator, AFTManager ftManager) {
    this.communicator = communicator;
    this.ftManager = ftManager;
  }

  public String getApplicationName() {
    return DEFAULT_APPLICATION_NAME;
  }

  public void compose() {
    topic =
        new AReplicatedSimpleList<Character>(communicator, getApplicationName());
    interactor = new AnEditorInteractor(topic, communicator);
    topic.addObserver(interactor);
    listInCoupler = new AListInCoupler(getApplicationName(), topic, ftManager);
    communicator.addPeerMessageListener(listInCoupler);
  }

  public SimpleList getList() {
    return topic;
  }

  public ListObserver getInteractor() {
    return interactor;
  }
}
