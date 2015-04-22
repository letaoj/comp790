package ft;

import im.ListEdit;

import java.util.ArrayList;
import java.util.List;

import trace.echo.modular.OperationName;
import trace.ft.MessageWithSequencerNumberReceived;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;
import ft.letao.AMessageWithSeqNum;
import ft.letao.AMessageWithSeqNumFromSequencer;
import ft.letao.ASentRequest;
import ft.letao.AFTManager;

public class MyReceivedMessageFilter implements MessageFilter<ReceivedMessage> {

  MessageProcessor<ReceivedMessage> messageProcessor;
  AFTManager ftManager;
  List<AMessageWithSeqNum> buffer;

  public MyReceivedMessageFilter(AFTManager ftManager) {
    this.ftManager = ftManager;
    buffer = new ArrayList<AMessageWithSeqNum>();
  }

  @Override
  public void setMessageProcessor(MessageProcessor<ReceivedMessage> theMesssageProcessor) {
    messageProcessor = theMesssageProcessor;
  }

  @Override
  public void filterMessage(ReceivedMessage message) {
    if (message.isUserMessage()) {
      if (message.getUserMessage() instanceof AMessageWithSeqNumFromSequencer) {
        messageProcessor.processMessage(message);
        return;
      }
      if (message.getUserMessage() instanceof AMessageWithSeqNum) {
        AMessageWithSeqNum seqMessage = (AMessageWithSeqNum) message.getUserMessage();
        ListEdit listEdit = (ListEdit) ftManager.unwarp(seqMessage);
        MessageWithSequencerNumberReceived.newCase(message.getClientName(), OperationName.ADD,
            listEdit.getIndex(), listEdit.getElement(), listEdit.getList(),
            message.getClientName(), this);
        if (seqMessage.getSeqNum() - 1 == ftManager.getGlobalSeqNum()) {
          ftManager.incGlobal();
          message.setUserMessage(listEdit);
          System.out.println("heheehhe");
          messageProcessor.processMessage(message);
        } else if (ftManager.getGlobalSeqNum() == seqMessage.getSeqNum()) {
          // Do nothing, duplicate message
        } else {
          buffer.add(seqMessage);
        }
        // System.out.println(seqMessage.getMessage());
        ftManager.addToHistory(listEdit);
        return;
      }
      if (message.getUserMessage() instanceof ListEdit) {
        System.out.println(message.getUserMessage());
        ftManager.addToHistory((ListEdit) message.getUserMessage());
      }
      messageProcessor.processMessage(message);
    } else {
      messageProcessor.processMessage(message);
    }
  }
}
