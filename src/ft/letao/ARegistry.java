package ft.letao;

import im.ListEdit;
import util.models.Hashcodetable;
import util.session.Communicator;

public class ARegistry {

  protected static Hashcodetable<Communicator, FTManager> hst =
      new Hashcodetable<Communicator, FTManager>();

  public static FTManager getFTManager(Communicator communicator) {
    if (hst.get(communicator) == null) {
      hst.put(communicator, new AFTManager(communicator));
    }
    return hst.get(communicator);
  }

  public static void sendRequest(Communicator communicator, ListEdit listEdit, String variant) {
    AFTManager ftManager = (AFTManager) getFTManager(communicator);
    ASentRequest aSentRequest = (ASentRequest) ftManager.warpSentRequest(listEdit);
    AFTManager.requestSending(communicator, aSentRequest, variant);
  }
}
