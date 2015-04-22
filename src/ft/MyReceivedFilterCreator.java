package ft;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.ReceivedMessage;
import ft.letao.AFTManager;

public class MyReceivedFilterCreator implements MessageFilterCreator<ReceivedMessage> {

  MessageFilter<ReceivedMessage> receivedMessageFilter;

  public MyReceivedFilterCreator(AFTManager ftManager) {
    receivedMessageFilter = new MyReceivedMessageFilter(ftManager);
  }

  @Override
  public MessageFilter<ReceivedMessage> getMessageFilter() {
    return receivedMessageFilter;
  }

}
