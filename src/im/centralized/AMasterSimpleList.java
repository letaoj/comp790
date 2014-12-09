package im.centralized;

import trace.echo.modular.OperationName;
import trace.im.ListEditSent;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.trace.session.AddressedSentMessageInfo;
import echo.modular.ASimpleList;

public class AMasterSimpleList<ElementType> extends ASimpleList<ElementType> implements MasterSimpleList<ElementType> {
	Communicator communicator;
	public AMasterSimpleList(Communicator theCommunicator) {
		communicator = theCommunicator;
	}
	
	public synchronized void centralizedAdd(ElementType anInput, String aClientName) {
		int anIndex = size();
		super.add(anIndex, anInput);
		UserEdit<ElementType> userEdit = new AUserEdit<ElementType>(OperationName.ADD, anIndex, anInput, ApplicationTags.IM, aClientName);
		ListEditSent.newCase(
				communicator.getClientName(),
				userEdit.getOperationName(), 
				userEdit.getIndex(), 
				userEdit.getElement(), 
				userEdit.getList(),
				AddressedSentMessageInfo.OTHERS, this);
		communicator.toOthers(userEdit);
	}
	
}
