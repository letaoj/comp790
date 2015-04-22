package ft;

import util.session.Communicator;
import util.tags.ApplicationTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;
import ft.letao.AFTManager;

public class AnEditorClientComposer extends AGUIClientComposer {
  public static final String DEFAULT_APPLICATION_NAME = ApplicationTags.EDITOR;

  protected SimpleList<Character> topic;
  protected AnEditorInteractor interactor;

  public AnEditorClientComposer(Communicator communicator) {
    this.communicator = communicator;
  }

  public String getApplicationName() {
    return DEFAULT_APPLICATION_NAME;
  }

  public void compose(AFTManager faultToleranceManager) {
    topic =
        new AReplicatedSimpleList<Character>(communicator, getApplicationName(),
            faultToleranceManager);
    interactor = new AnEditorInteractor(topic, communicator);
    topic.addObserver(interactor);
    listInCoupler = new AListInCoupler(getApplicationName(), topic);
    communicator.addPeerMessageListener(listInCoupler);
  }

  public SimpleList getList() {
    return topic;
  }

  public ListObserver getInteractor() {
    return interactor;
  }
}
