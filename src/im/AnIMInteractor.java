package im;

import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
import echo.modular.AnEchoInteractor;
import echo.modular.SimpleList;
import echo.monolithic.EchoUtilities;
@Tags({ApplicationTags.IM, InteractiveTags.INTERACTOR})

public class AnIMInteractor extends AnEchoInteractor  {
	protected Communicator communicator;
	public AnIMInteractor(SimpleList<String> aHistory, Communicator aCommunicator) {
		super(aHistory);
		communicator = aCommunicator;
	}	
	protected void addToHistory(String newValue) {
//		((ReplicatedHistory) history).replicatedAdd(history.size(), newValue);
		((ReplicatedSimpleList) history).replicatedAdd(newValue);

	}
	protected void processQuit() {
		super.processQuit();
		communicator.leave();
	}
	protected String computeFeedback(String anInput) {
		return IMUtililties.remoteEcho(anInput, communicator.getClientName());
	}
//	protected void displayOutput(String newValue) {
//		System.out.println(EchoUtilities.echo(newValue));
//	}
}
