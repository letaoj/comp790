package im.centralized;

import im.AListEdit;
import im.ListEdit;
import im.centralized.awareinteractor.AwareMasterIMModelLauncher;
import trace.echo.modular.OperationName;
import trace.im.ListEditSent;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.trace.session.AddressedSentMessageInfo;
import echo.modular.ASimpleList;

public class ASlaveSimpleList<ElementType> extends ASimpleList<ElementType> implements SlaveSimpleList<ElementType> {
	Communicator communicator;
	public ASlaveSimpleList(Communicator theCommunicator) {
		communicator = theCommunicator;
	}
	
	public synchronized void proxyAdd(ElementType anElement) {
		int anIndex = size();
		ListEdit listEdit = new AListEdit<ElementType>(OperationName.ADD, anIndex, anElement, ApplicationTags.IM);
		ListEditSent.newCase(
				communicator.getClientName(),
				listEdit.getOperationName(), 
				listEdit.getIndex(), 
				listEdit.getElement(), 
				listEdit.getList(),
			AddressedSentMessageInfo.CLIENT, this); 
		communicator.toClient(MasterIMModelLauncher.CLIENT_NAME, listEdit);
		
	}
	
}
