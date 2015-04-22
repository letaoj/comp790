package ft;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;
import ft.letao.AFTManager;

public class MySentFilterCreator implements MessageFilterCreator<SentMessage> {

  MessageFilter<SentMessage> sentMessageFilter;

  public MySentFilterCreator(AFTManager ftManager) {
    sentMessageFilter = new MySentMessageFilter(ftManager);
  }

  @Override
  public MessageFilter<SentMessage> getMessageFilter() {
    return sentMessageFilter;
  }

}
