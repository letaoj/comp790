package ot.letao;

import java.util.Map;

import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;
import echo.modular.SimpleList;

public class MyTimeStampingReceivedMessageFilter implements MessageFilter<ReceivedMessage> {

  MessageProcessor<ReceivedMessage> messageProcessor;
  Map<SimpleList, OTManager> otManagers;

  public MyTimeStampingReceivedMessageFilter(Map<SimpleList, OTManager> otManagers) {
    this.otManagers = otManagers;
  }

  @Override
  public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
    messageProcessor = theMesssageProcessor;
  }

  @Override
  public void filterMessage(ReceivedMessage message) {
    if (message.isUserMessage()) {

      messageProcessor.processMessage(message);
    } else {
      messageProcessor.processMessage(message);
    }
  }
}
