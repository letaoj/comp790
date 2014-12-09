package Assignment6;

import java.util.HashMap;
import java.util.Map;

import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.ServerMessageFilter;

public class MyMasterServerFilter implements ServerMessageFilter {

  MessageProcessor<SentMessage> messageProcessor;
  Map<String, MyServerFilter> filters;
  MyServerFilter historyFilter;
  MyServerFilter topicFilter;

  public MyMasterServerFilter() {
    filters = new HashMap<String, MyServerFilter>();
    historyFilter = new MyServerFilter();
    topicFilter = new MyServerFilter();
    filters.put("IM", historyFilter);
    filters.put("EDITOR", topicFilter);
  }

  @Override
  public void setMessageProcessor(MessageProcessor<SentMessage> theMessageProcessor) {
    this.messageProcessor = theMessageProcessor;
    historyFilter.setMessageProcessor(theMessageProcessor);
    topicFilter.setMessageProcessor(theMessageProcessor);
  }

  @Override
  public void filterMessage(SentMessage message) {
    if (message.isUserMessage()) {
      if (message.getUserMessage() instanceof OTMessage) {
        OTMessage otMessage = (OTMessage) message.getUserMessage();
        filters.get(((ListEdit) (otMessage.getMessage())).getList()).filterMessage(message);
      } else {
        messageProcessor.processMessage(message);
      }
    } else {
      messageProcessor.processMessage(message);
    }
  }

  @Override
  public void userJoined(String aSessionName, String anApplicationName, String userName) {
    historyFilter.userJoined(aSessionName, anApplicationName, userName);
    topicFilter.userJoined(aSessionName, anApplicationName, userName);
  }

  @Override
  public void userLeft(String aSessionName, String anApplicationName, String userName) {
    historyFilter.userLeft(aSessionName, anApplicationName, userName);
    topicFilter.userLeft(aSessionName, anApplicationName, userName);
  }

}
