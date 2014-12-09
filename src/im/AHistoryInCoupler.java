package im;

import trace.im.ListEditReceived;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;
import util.tags.ApplicationTags;
import echo.modular.SimpleList;

public class AHistoryInCoupler implements PeerMessageListener {
	protected SimpleList<String> history;
	public AHistoryInCoupler(SimpleList<String>  theEchoer) {
		history = theEchoer;
	}
	public void objectReceived(Object message, String userName) {
		// need for integration with RPC
		if (message instanceof ListEdit)
			processReceivedListEdit((ListEdit<String>) message, userName);
	}
	protected void processReceivedListEdit (ListEdit<String> aRemoteEdit, String aUserName) {
		if (!aRemoteEdit.getList().equals(ApplicationTags.IM))
			return;
		ListEditReceived.newCase(
				CommunicatorSelector.getProcessName(),
				aRemoteEdit.getOperationName(), 
				aRemoteEdit.getIndex(), 
				aRemoteEdit.getElement(),
				aRemoteEdit.getList(),
				aUserName, 
				this);
		String anInput = aRemoteEdit.getElement();
		// not observable add so we do not get echo message
		// add before we print the message in case some  something reacts to the print
		// before we add
//		history.add(aRemoteEdit.getIndex(), anInput);
		// actually no bounce back should occur should occur
		history.observableAdd(normalizedIndex(history, aRemoteEdit.getIndex()), anInput);

//		System.out.println(IMUtililties.remoteEcho(anInput, aUserName));
	
	}
	protected static int normalizedIndex(SimpleList aHistory, int index) {
		int size = aHistory.size();
		return index > size?size: index;
	}

}
