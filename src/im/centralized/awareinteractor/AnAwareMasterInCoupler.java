package im.centralized.awareinteractor;

import im.AHistoryInCoupler;
import im.centralized.MasterSimpleList;
import echo.modular.SimpleList;

public class AnAwareMasterInCoupler extends AHistoryInCoupler {
	
	public AnAwareMasterInCoupler(SimpleList<String>  aHistory) {
		super(aHistory);
	}	
//	protected void processReceivedListEdit (ListEdit<String> aRemoteEdit, String aUserName) {
//		history.add(aRemoteEdit.getElement());			
//	}	
	public void objectReceived(Object message, String userName) {
		// need for integration with RPC
		if (message instanceof String)
			((MasterSimpleList<String>) history).centralizedAdd((String) message, userName); 
	}
	protected void connectModelInteractor() {
		
	}

}
