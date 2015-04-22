package ft;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;
import ft.letao.ASentRequest;
import ft.letao.AFTManager;
import ft.letao.FTType;

public class MySentMessageFilter implements MessageFilter<SentMessage> {

  MessageProcessor<SentMessage> messageProcessor;
  AFTManager ftManager;

  public MySentMessageFilter(AFTManager ftManager) {
    this.ftManager = ftManager;
  }

  @Override
  public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
    messageProcessor = theMesssageProcessor;
  }

  @Override
  public void filterMessage(SentMessage message) {
    messageProcessor.processMessage(message);
  }

}
