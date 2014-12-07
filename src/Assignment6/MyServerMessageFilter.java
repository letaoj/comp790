package Assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.ServerMessageFilter;

public class MyServerMessageFilter implements ServerMessageFilter {

  MessageProcessor<SentMessage> messageProcessor;
  Map<String, List<OTManager>> userOTManagers;

  public MyServerMessageFilter(OTManager otManager) {
    userOTManagers = new HashMap<String, List<OTManager>>();
  }

  @Override
  public void setMessageProcessor(MessageProcessor<SentMessage> theMessageProcessor) {
    messageProcessor = theMessageProcessor;
  }

  @Override
  public void filterMessage(SentMessage message) {
    if (message.isUserMessage()) {

    } else {
      messageProcessor.processMessage(message);
    }
  }

  @Override
  public void userJoined(String aSessionName, String anApplicationName, String userName) {
    userOTManagers.put(userName, new ArrayList<OTManager>());
  }

  @Override
  public void userLeft(String aSessionName, String anApplicationName, String userName) {
    userOTManagers.remove(userName);
  }

  public void setUserOTManagers(String userName, List<OTManager> otManagers) {
    userOTManagers.put(userName, otManagers);
  }

}
