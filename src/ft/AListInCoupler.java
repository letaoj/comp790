package ft;

import im.ListEdit;
import trace.echo.modular.OperationName;
import trace.ft.MessageWithSequencerNumberReceived;
import trace.ft.SentRequestReceived;
import trace.im.ListEditReceived;
import util.models.Hashcodetable;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;
import echo.modular.SimpleList;
import ft.letao.AFTManager;
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
      AMessageWithSeqNum msg = (AMessageWithSeqNum) message;
      ListEdit listEdit = (ListEdit) ftManager.unwarp(msg);
      MessageWithSequencerNumberReceived.newCase(userName, OperationName.ADD, listEdit.getIndex(),
          listEdit.getElement(), listEdit.getList(), userName, this);
      if (msg.getSeqNum() - 1 == ftManager.getGlobalSeqNum()) {
        ftManager.incGlobal();
      } else if (ftManager.getGlobalSeqNum() == msg.getSeqNum()) {
        // Do nothing, duplicate message
      } else {
        ftManager.bufferMessage(System.identityHashCode(msg), msg);
      }
      // System.out.println(seqMessage.getMessage());
      ftManager.addToHistory(listEdit);
      processReceivedListEdit(listEdit, userName);
    }
  }

  protected void processReceivedSeqNum(AMessageWithSeqNumFromSequencer message, String userName) {
    if (!((ListEdit) message.getMessage()).getList().equals(tag)) {
      return;
    }
    ((AReplicatedSimpleList) list).UUBtotalOrderAdd(message);
  }

  protected void processReceivedSentRequest(ASentRequest<E> aSentRequest, String userName) {
    if (!aSentRequest.getListEdit().getList().equals(tag)) {
      return;
    }
    SentRequestReceived.newCase(userName, OperationName.ADD, 0, aSentRequest.getListEdit()
        .getElement(), tag, userName, this);
    ((AReplicatedSimpleList) list).UBtotalOrderAdd(aSentRequest);
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

    // System.out.println(IMUtililties.remoteEcho(anInput, aUserName));

  }

  protected static int normalizedIndex(SimpleList aTopic, int index) {
    int size = aTopic.size();
    return index > size ? size : index;
  }

}
