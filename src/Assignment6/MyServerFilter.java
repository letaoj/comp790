package Assignment6;

import java.util.HashMap;
import java.util.Map;

import util.session.ASentMessage;
import util.session.MessageProcessor;
import util.session.SentMessage;
import util.session.ServerMessageFilter;

public class MyServerFilter implements ServerMessageFilter {

  MessageProcessor<SentMessage> messageProcessor;
  Map<String, OTManager> otManagers;

  @Override
  public void setMessageProcessor(MessageProcessor<SentMessage> theMessageProcessor) {
    messageProcessor = theMessageProcessor;
    otManagers = new HashMap<String, OTManager>();
  }

  @Override
  public void filterMessage(SentMessage message) {
    OTMessage msg = (OTMessage) message.getUserMessage();
    OTTimeStamp remoteTimeStamp = msg.getOTTimeStamp();
    ListEdit remoteOp = (ListEdit) msg.getMessage();
    OTTimeStamp.filp(remoteTimeStamp);
    OTManager otManager = otManagers.get(message.getSendingUser());
    remoteOp = otManager.transform(remoteOp, remoteTimeStamp);
    System.out.println("remote" + remoteOp.getIndex());
    for (String user : otManagers.keySet()) {
      if (!user.equals(message.getSendingUser())) {
        SentMessage unicast = ASentMessage.toSpecificUser(message, user);
        OTManager otm = otManagers.get(user);
        OTTimeStamp tm = otm.getTimeStamp();
        OTTimeStamp.incLocal(tm);
        OTMessage m = new OTMessage(remoteOp, OTTimeStamp.deepCopy(tm));
        unicast.setUserMessage(m);
        messageProcessor.processMessage(unicast);
        otm.addToBuffer(m);
      }
    }
  }

  @Override
  public void userJoined(String aSessionName, String anApplicationName, String userName) {
    otManagers.put(userName, new OTManager(userName, true));
  }

  @Override
  public void userLeft(String aSessionName, String anApplicationName, String userName) {
    otManagers.remove(userName);
  }
}
