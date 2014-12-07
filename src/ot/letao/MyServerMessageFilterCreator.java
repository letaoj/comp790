package ot.letao;

import util.session.ServerMessageFilter;
import util.session.ServerMessageFilterCreator;

public class MyServerMessageFilterCreator implements ServerMessageFilterCreator {

  ServerMessageFilter serverMessageFilter;

  public MyServerMessageFilterCreator(OTManager otManager) {
    serverMessageFilter = new MyServerMessageFilter(otManager);
  }

  @Override
  public ServerMessageFilter getServerMessageFilter() {
    return serverMessageFilter;
  }

}
