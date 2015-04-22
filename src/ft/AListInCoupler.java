package ft;

import im.ListEdit;
import trace.echo.modular.OperationName;
import trace.ft.SentRequestReceived;
import trace.im.ListEditReceived;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;
import echo.modular.SimpleList;
import ft.letao.AMessageWithSeqNumFromSequencer;
import ft.letao.ASentRequest;

public class AListInCoupler<E> implements PeerMessageListener {
  protected SimpleList<E> list;
  protected String tag;

  public AListInCoupler(String tag, SimpleList<E> theEchoer) {
    this.tag = tag;
    list = theEchoer;
  }

  public void objectReceived(Object message, String userName) {
    // need for integration with RPC
    if (message instanceof ASentRequest) {
      processReceivedSentRequest((ASentRequest) message, userName);
    } else if (message instanceof ListEdit) {
      processReceivedListEdit((ListEdit<E>) message, userName);
    } else if (message instanceof AMessageWithSeqNumFromSequencer) {
      processReceivedSeqNum((AMessageWithSeqNumFromSequencer) message, userName);
    }
  }

  protected void processReceivedSeqNum(AMessageWithSeqNumFromSequencer message, String userName) {
    if (!((ListEdit) message.getMessage()).getList().equals(tag)) {
      return;
    }
    SentRequestReceived.newCase(userName, OperationName.ADD, 0,
        ((ASentRequest) message.getMessage()).getListEdit().getElement(), tag, userName, this);
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
