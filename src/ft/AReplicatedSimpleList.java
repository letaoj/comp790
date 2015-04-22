package ft;

import im.AListEdit;
import im.ListEdit;

import java.util.ArrayList;
import java.util.List;

import trace.echo.modular.OperationName;
import trace.ft.MessageWithSequencerNumberReceived;
import trace.ft.MessageWithSequencerNumberSent;
import trace.ft.SentRequestSent;
import trace.im.ListEditSent;
import util.session.Communicator;
import util.trace.session.AddressedSentMessageInfo;
import echo.modular.ListObserver;
import ft.letao.AMessageWithSeqNum;
import ft.letao.AMessageWithSeqNumFromSequencer;
import ft.letao.ASentRequest;
import ft.letao.AFTManager;
import ft.letao.FTType;
import ft.letao.MessageWithSeqNum;

public class AReplicatedSimpleList<T> extends AModifiedSimpleList<T> implements
    ReplicatedSimpleList<T> {

  Communicator communicator;
  List<ListObserver<T>> replicatingObservers = new ArrayList<ListObserver<T>>();
  AFTManager ftManager;

  public AReplicatedSimpleList(Communicator communicator, String tag, AFTManager ftManager) {
    super(tag);
    this.communicator = communicator;
    this.ftManager = ftManager;
  }

  public synchronized void replicatedAdd(int index, T element) {
    if (index == -1) {
      index = size();
    }
    System.out.println(index + "" + element);
    if (communicator == null)
      return;
    ListEdit<T> listEdit = new AListEdit<T>(OperationName.ADD, index, element, tag);
    if (ftManager.isFT()) {
      ftManager.requestSending(listEdit, ftManager.getFTType());
    } else {
      ListEditSent.newCase(communicator.getClientName(), listEdit.getOperationName(),
          listEdit.getIndex(), listEdit.getElement(), listEdit.getList(),
          AddressedSentMessageInfo.OTHERS, this);
      super.observableAdd(index, element);
      ftManager.addToHistory(listEdit);
      notifyAddReplicatingObservers(index, element);
      communicator.toOthers(listEdit);
    }
  }

  public synchronized void replicatedRemove(int index, T element) {
    super.observableRemove(index);
    if (communicator == null)
      return;
    ListEdit<T> listEdit = new AListEdit<T>(OperationName.DELETE, index, element, tag);
    ListEditSent.newCase(communicator.getClientName(), listEdit.getOperationName(),
        listEdit.getIndex(), listEdit.getElement(), listEdit.getList(),
        AddressedSentMessageInfo.OTHERS, this);
    communicator.toOthers(listEdit);
    notifyRemoveReplicatingObservers(index, element);
  }

  public void notifyRemoveReplicatingObservers(int index, T oldValue) {
    notifyRemove(replicatingObservers, index, oldValue);
  }

  public void notifyAddReplicatingObservers(int index, T newValue) {
    notifyAdd(replicatingObservers, index, newValue);
  }

  public synchronized void UBtotalOrderAdd(ASentRequest<T> aSentRequest) {
    if (communicator.getClientName().equals(ftManager.getSequencer())) {
      if (aSentRequest.getFTType().equals(FTType.UUB)) {
        ListEdit<T> listEdit = ftManager.upwarpSentRequest(aSentRequest);
        AMessageWithSeqNumFromSequencer message =
            (AMessageWithSeqNumFromSequencer) ftManager.wrapMessage(listEdit);
        MessageWithSequencerNumberSent.newCase(communicator.getClientName(), OperationName.ADD,
            listEdit.getIndex(), listEdit.getElement(), tag, aSentRequest.getClientName(), this);
        communicator.toClient(aSentRequest.getClientName(), message);
      } else {
        ListEdit<T> listEdit = ftManager.upwarpSentRequest(aSentRequest);
        MessageWithSeqNum message = ftManager.wrapMessage(listEdit);
        MessageWithSequencerNumberSent.newCase(communicator.getClientName(), OperationName.ADD,
            listEdit.getIndex(), listEdit.getElement(), tag, AddressedSentMessageInfo.ALL, this);
        communicator.toAll(message);
      }
    }
  }

  public void UUBtotalOrderAdd(AMessageWithSeqNum msg) {
    MessageWithSequencerNumberSent.newCase(communicator.getClientName(), OperationName.ADD,
        msg.getSeqNum(), msg.getMessage(), tag, AddressedSentMessageInfo.ALL, this);
    communicator.toAll(msg);
  }

}
