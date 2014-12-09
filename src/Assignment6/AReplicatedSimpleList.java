package Assignment6;

import java.util.ArrayList;
import java.util.List;

import trace.echo.modular.OperationName;
import trace.im.ListEditSent;
import util.session.Communicator;
import util.trace.session.AddressedSentMessageInfo;
import echo.modular.ListObserver;

public class AReplicatedSimpleList<T> extends AModifiedSimpleList<T> implements
    ReplicatedSimpleList<T> {

  Communicator communicator;
  List<ListObserver<T>> replicatingObservers = new ArrayList();

  public AReplicatedSimpleList(String tag) {
    super(tag);
  }

  public synchronized void replicatedAdd(int index, T element) {
    if (index == -1) {
      index = size();
    }
    super.observableAdd(index, element);
    if (communicator == null)
      return;
    ListEdit listEdit = new AListEdit<T>(OperationName.ADD, index, element, tag);
    ListEditSent.newCase(communicator.getClientName(), listEdit.getOperationName(),
        listEdit.getIndex(), listEdit.getElement(), listEdit.getList(),
        AddressedSentMessageInfo.OTHERS, this);
    communicator.toOthers(listEdit);
    notifyAddReplicatingObservers(index, element);

  }

  public synchronized void replicatedRemove(int index, T element) {
    super.observableRemove(index);
    if (communicator == null)
      return;
    ListEdit listEdit = new AListEdit<T>(OperationName.DELETE, index, element, tag);
    ListEditSent.newCase(communicator.getClientName(), listEdit.getOperationName(),
        listEdit.getIndex(), listEdit.getElement(), listEdit.getList(),
        AddressedSentMessageInfo.OTHERS, this);
    communicator.toOthers(listEdit);
    notifyRemoveReplicatingObservers(index, element);

  }

  public void setCommunicator(Communicator communicator) {
    this.communicator = communicator;
  }

  public void notifyRemoveReplicatingObservers(int index, T oldValue) {
    notifyRemove(replicatingObservers, index, oldValue);
  }

  public void notifyAddReplicatingObservers(int index, T newValue) {
    notifyAdd(replicatingObservers, index, newValue);
  }

}
