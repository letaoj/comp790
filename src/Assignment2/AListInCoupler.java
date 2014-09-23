package Assignment2;

import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;

public class AListInCoupler<E> implements PeerMessageListener {
	protected SimpleList<E> list;
	protected String tag;

	public AListInCoupler(String tag, SimpleList<E> theEchoer) {
		this.tag = tag;
		list = theEchoer;
	}

	public void objectReceived(Object message, String userName) {
		// need for integration with RPC
		if (message instanceof ListEdit)
			processReceivedListEdit((ListEdit<E>) message, userName);
	}

	protected void processReceivedListEdit(ListEdit<E> aRemoteEdit,
			String aUserName) {
		if (!aRemoteEdit.getList().equals(tag))
			return;
		ListEditReceived.newCase(CommunicatorSelector.getProcessName(),
				aRemoteEdit.getOperationName(), aRemoteEdit.getIndex(),
				aRemoteEdit.getElement(), aRemoteEdit.getList(), aUserName,
				this);
		E anInput = aRemoteEdit.getElement();
		if (aRemoteEdit.getOperationName().equals(OperationName.ADD)) {
			list.observableAdd(normalizedIndex(list, aRemoteEdit.getIndex()),
					anInput);
		} else if (aRemoteEdit.getOperationName().equals(OperationName.DELETE)) {
			((AModifiedSimpleList)list).observableRemove(normalizedIndex(list, aRemoteEdit.getIndex()));
		}

		// System.out.println(IMUtililties.remoteEcho(anInput, aUserName));

	}

	protected static int normalizedIndex(SimpleList aTopic, int index) {
		int size = aTopic.size();
		return index > size ? size : index;
	}

}
