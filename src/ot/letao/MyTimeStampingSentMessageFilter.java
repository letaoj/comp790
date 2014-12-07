package ot.letao;

import java.util.Map;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;
import echo.modular.SimpleList;

public class MyTimeStampingSentMessageFilter implements MessageFilter<SentMessage> {

  MessageProcessor<SentMessage> messageProcessor;
  Map<SimpleList, OTManager> otManagers;

  public MyTimeStampingSentMessageFilter(Map<SimpleList, OTManager> otManagers) {
    this.otManagers = otManagers;
  }

  @Override
  public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
    messageProcessor = theMesssageProcessor;
  }

  @Override
  public void filterMessage(SentMessage message) {
    if (message.isUserMessage()) {

      messageProcessor.processMessage(message);
    } else {
      messageProcessor.processMessage(message);
    }
  }

}
