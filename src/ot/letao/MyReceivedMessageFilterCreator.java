package ot.letao;

import java.util.Map;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;
import echo.modular.SimpleList;

public class MyReceivedMessageFilterCreator implements MessageFilterCreator<ReceivedMessage> {

  MessageFilter<ReceivedMessage> receivedMessageFilter;

  public MyReceivedMessageFilterCreator(Map<SimpleList, OTManager> otManagers) {
    receivedMessageFilter = new MyTimeStampingReceivedMessageFilter(otManagers);
  }

  @Override
  public MessageFilter<ReceivedMessage> getMessageFilter() {
    return receivedMessageFilter;
  }

}
