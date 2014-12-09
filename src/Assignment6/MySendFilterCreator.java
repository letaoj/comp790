package Assignment6;

import java.util.Map;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;

public class MySendFilterCreator implements MessageFilterCreator<SentMessage> {

  MessageFilter<SentMessage> sentMessageFilter;

  public MySendFilterCreator(Map<String, OTManager> otManagers) {
    sentMessageFilter = new MySendFilter(otManagers);
  }

  @Override
  public MessageFilter<SentMessage> getMessageFilter() {
    return sentMessageFilter;
  }

}
