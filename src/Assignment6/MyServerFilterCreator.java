package Assignment6;

import util.session.ServerMessageFilter;
import util.session.ServerMessageFilterCreator;

public class MyServerFilterCreator implements ServerMessageFilterCreator {

  ServerMessageFilter serverMessageFilter;

  public MyServerFilterCreator() {
    serverMessageFilter = new MyMasterServerFilter();
  }

  @Override
  public ServerMessageFilter getServerMessageFilter() {
    return serverMessageFilter;
  }

}
