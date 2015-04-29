package ft;

import im.ListEdit;

import java.util.ArrayList;
import java.util.List;

import trace.echo.modular.OperationName;
import trace.ft.MessageWithSequencerNumberReceived;
import util.session.MessageFilter;
import util.session.MessageProcessor;
import util.session.ReceivedMessage;
import ft.letao.AFTManager;
import ft.letao.AMessageWithSeqNum;
import ft.letao.AMessageWithSeqNumFromSequencer;
import ft.letao.SentRequest;

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
      messageProcessor.processMessage(message);
    } else {
      messageProcessor.processMessage(message);
    }
  }
}
