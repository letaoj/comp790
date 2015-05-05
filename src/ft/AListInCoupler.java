package ft;

import im.ListEdit;
import trace.echo.modular.OperationName;
import trace.ft.MessageWithSequencerNumberReceived;
import trace.ft.SentRequestReceived;
import trace.im.ListEditReceived;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;
import echo.modular.SimpleList;
import ft.letao.AFTManager;
import ft.letao.AMessageWithHashCode;
import ft.letao.AMessageWithSeqNum;
import ft.letao.AMessageWithSeqNumFromSequencer;
import ft.letao.ASentRequest;

public class AListInCoupler<E> implements PeerMessageListener {
  protected SimpleList<E> list;
  protected String tag;
  protected AFTManager ftManager;

  public AListInCoupler(String tag, SimpleList<E> theEchoer, AFTManager ftManager) {
    this.tag = tag;
    list = theEchoer;
    this.ftManager = ftManager;
  }

  public void objectReceived(Object message, String userName) {
    // need for integration with RPC
    if (message instanceof ASentRequest) {
      processReceivedSentRequest((ASentRequest) message, userName);
    } else if (message instanceof ListEdit) {
      processReceivedListEdit((ListEdit<E>) message, userName);
    } else if (message instanceof AMessageWithSeqNumFromSequencer) {
      processReceivedSeqNum((AMessageWithSeqNumFromSequencer) message, userName);
    } else if (message instanceof AMessageWithSeqNum) {
      processReceivedMsgWithSeqNum((AMessageWithSeqNum) message, userName);
    } else if (message instanceof AMessageWithHashCode) {
      processReceivedMsgWithHashCode((AMessageWithHashCode) message, userName);
    }
  }

  protected void processReceivedMsgWithHashCode(AMessageWithHashCode message, String userName) {
    ftManager.setAlgorithmStatus("MessageWithHashCode Received!");
    ftManager.retriveMessage((ListEdit)message.getMessage());
    processReceivedListEdit((ListEdit)message.getMessage(), userName);
  }

  protected void processReceivedSeqNum(AMessageWithSeqNumFromSequencer message, String userName) {
    if (!((ListEdit) message.getMessage()).getList().equals(tag)) {
      return;
    }
    ftManager.setAlgorithmStatus("MessageFromSequencer Received!");
    ((AReplicatedSimpleList) list).UUBtotalOrderAdd(message);
  }

  protected void processReceivedMsgWithSeqNum(AMessageWithSeqNum msg, String userName) {
    ListEdit listEdit = (ListEdit) msg.getMessage();
    ftManager.setAlgorithmStatus("MessageWithSeqNum Received!");
    MessageWithSequencerNumberReceived.newCase(userName, OperationName.ADD, listEdit.getIndex(),
        listEdit.getElement(), listEdit.getList(), userName, this);
    if (msg.getSeqNum() - 1 == ftManager.getGlobalSeqNum()) {
      ftManager.incGlobal();
    } else if (ftManager.getGlobalSeqNum() == msg.getSeqNum()) {
      // Do nothing, duplicate message
    } else {
      ftManager.bufferMessage(listEdit);
    }
    ftManager.addToHistory(listEdit);
    processReceivedListEdit(listEdit, userName);
  }

  protected void processReceivedSentRequest(ASentRequest<E> aSentRequest, String userName) {
    if (!aSentRequest.getListEdit().getList().equals(tag)) {
      return;
    }
    SentRequestReceived.newCase(userName, OperationName.ADD, 0, aSentRequest.getListEdit()
        .getElement(), tag, userName, this);
    ((AReplicatedSimpleList) list).processSentRequest(aSentRequest);
  }

  protected void processReceivedListEdit(ListEdit<E> aRemoteEdit, String aUserName) {
    if (!aRemoteEdit.getList().equals(tag))
      return;
    ListEditReceived.newCase(CommunicatorSelector.getProcessName(), aRemoteEdit.getOperationName(),
        aRemoteEdit.getIndex(), aRemoteEdit.getElement(), aRemoteEdit.getList(), aUserName, this);
    E anInput = aRemoteEdit.getElement();
    if (aRemoteEdit.getOperationName().equals(OperationName.ADD)) {
      list.observableAdd(normalizedIndex(list, aRemoteEdit.getIndex()), anInput);
    } else if (aRemoteEdit.getOperationName().equals(OperationName.DELETE)) {
      ((AModifiedSimpleList) list).observableRemove(normalizedIndex(list, aRemoteEdit.getIndex()));
    }
  }

  protected static int normalizedIndex(SimpleList aTopic, int index) {
    int size = aTopic.size();
    return index > size ? size : index;
  }

}
