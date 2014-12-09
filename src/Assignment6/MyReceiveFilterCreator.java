package Assignment6;

import java.util.Map;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;

public class MyReceiveFilterCreator implements MessageFilterCreator<ReceivedMessage> {

  MessageFilter<ReceivedMessage> receivedMessageFilter;

  public MyReceiveFilterCreator(Map<String, OTManager> otManagers) {
    receivedMessageFilter = new MyReceiveFilter(otManagers);
  }

  @Override
  public MessageFilter<ReceivedMessage> getMessageFilter() {
    return receivedMessageFilter;
  }

}
