package Assignment6;

import java.util.Map;

import trace.ot.OTListEditReceived;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;

public class MyReceiveFilter implements MessageFilter<ReceivedMessage> {

  MessageProcessor<ReceivedMessage> messageProcessor;
  Map<String, OTManager> otManagers;

  public MyReceiveFilter(Map<String, OTManager> otManagers) {
    this.otManagers = otManagers;
  }

  @Override
  public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
    messageProcessor = theMesssageProcessor;
  }

  @Override
  public void filterMessage(ReceivedMessage message) {
    if (message.isUserMessage()) {
      if (useOT()) {
        OTMessage msg = (OTMessage) message.getUserMessage();
        ListEdit remoteOp = (ListEdit) msg.getMessage();
        OTTimeStamp remoteTimeStamp = OTTimeStamp.filp(msg.getOTTimeStamp());
        OTListEditReceived.newCase(remoteOp.getList(), remoteOp.getOperationName(),
            remoteOp.getIndex(), remoteOp.getElement(), remoteTimeStamp.L, remoteTimeStamp.R,
            message.getClientName(), this);
        OTManager otManager = otManagers.get(remoteOp.getList());
        remoteOp = otManager.transform(remoteOp, remoteTimeStamp);
        OTTimeStamp.incRemote(otManager.getTimeStamp());
        message.setUserMessage(remoteOp);
        messageProcessor.processMessage(message);
      } else {
        messageProcessor.processMessage(message);
      }
    } else {
      messageProcessor.processMessage(message);
    }
  }

  public boolean useOT() {
    for (OTManager ot : otManagers.values()) {
      return ot.isDynamicOT();
    }
    return false;
  }
}
