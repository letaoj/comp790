package im.centralized;

import im.AHistoryInCoupler;
import im.ListEdit;
import echo.modular.SimpleList;

public class AMasterInCoupler extends AHistoryInCoupler {
	
	public AMasterInCoupler(SimpleList<String>  aHistory) {
		super(aHistory);
	}	
//	protected void processReceivedListEdit (ListEdit<String> aRemoteEdit, String aUserName) {
//		history.add(aRemoteEdit.getElement());			
//	}	
	public void objectReceived(Object aMessage, String aUserName) {
		// need for integration with RPC
		if (aMessage instanceof ListEdit) {
			ListEdit<String> aListEdit = (ListEdit) aMessage;
			((MasterSimpleList<String>) history).centralizedAdd(aListEdit.getElement(), aUserName); 
		}
	}
	protected void connectModelInteractor() {
		
	}

}
