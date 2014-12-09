package Assignment6;

import java.util.Map;

import trace.ot.LocalSiteCountIncremented;
import trace.ot.OTListEditBuffered;
import trace.ot.OTListEditSent;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.SentMessage;

public class MySendFilter implements MessageFilter<SentMessage> {

  MessageProcessor<SentMessage> messageProcessor;
  Map<String, OTManager> otManagers;

  public MySendFilter(Map<String, OTManager> otManagers) {
    this.otManagers = otManagers;
  }

  @Override
  public void setMessageProcessor(MessageProcessor<SentMessage> theMesssageProcessor) {
    messageProcessor = theMesssageProcessor;
  }

  @Override
  public void filterMessage(SentMessage message) {
    if (message.isUserMessage()) {
      if (useOT()) {
        ListEdit listEdit = (ListEdit) message.getUserMessage();
        OTManager listOTManager = otManagers.get(listEdit.getList());
        OTTimeStamp localTimeStamp = listOTManager.getTimeStamp();
        OTTimeStamp.incLocal(localTimeStamp);
        LocalSiteCountIncremented.newCase(message.getSendingUser(), message.getSendingUser(),
            localTimeStamp.L, localTimeStamp.R, this);
        OTMessage msg = new OTMessage(listEdit, OTTimeStamp.deepCopy(localTimeStamp));
        message.setUserMessage(msg);
        messageProcessor.processMessage(message);
        OTListEditSent.newCase(listEdit.getList(), listEdit.getOperationName(),
            listEdit.getIndex(), listEdit.getElement(), localTimeStamp.L, localTimeStamp.R,
            listOTManager.getUserName(), this);
        listOTManager.addToBuffer(msg);
        OTListEditBuffered.newCase(listEdit.getList(), listEdit.getOperationName(),
            listEdit.getIndex(), listEdit.getElement(), localTimeStamp.L, localTimeStamp.R,
            listOTManager.getUserName(), this);
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
