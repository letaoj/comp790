package ot.letao;

import java.util.Map;

import util.session.MessageFilter;
import util.session.MessageFilterCreator;
import util.session.SentMessage;
import echo.modular.SimpleList;

public class MySentMessageFilterCreator implements MessageFilterCreator<SentMessage> {

  MessageFilter<SentMessage> sentMessageFilter;

  public MySentMessageFilterCreator(Map<SimpleList, OTManager> otManagers) {
    sentMessageFilter = new MyTimeStampingSentMessageFilter(otManagers);
  }

  @Override
  public MessageFilter<SentMessage> getMessageFilter() {
    return sentMessageFilter;
  }

}
