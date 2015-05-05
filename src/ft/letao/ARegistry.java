package ft.letao;

import im.ListEdit;
import util.models.Hashcodetable;
import util.session.Communicator;

public class ARegistry {

  protected static Hashcodetable<Communicator, FTManager> hct =
      new Hashcodetable<Communicator, FTManager>();

  public static FTManager getFTManager(Communicator communicator) {
    if (hct.get(communicator) == null) {
      FTManager ftManager = new AFTManager(communicator);
      hct.put(communicator, ftManager);
      communicator.addSessionMessageListener(ftManager);
      communicator.addPeerMessageListener(ftManager);
    }
    return hct.get(communicator);
  }

  public static void sendRequest(Communicator communicator, ListEdit listEdit, String variant) {
    AFTManager ftManager = (AFTManager) getFTManager(communicator);
    AFTManager.requestSending(communicator, (ASentRequest) ftManager.warpSentRequest(listEdit));
  }
}
