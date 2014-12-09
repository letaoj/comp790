package im.centralized.awareinteractor;

import im.AnIMInteractor;
import util.session.Communicator;
import echo.modular.SimpleList;

public class AnAwareSlaveIMInteractor extends AnIMInteractor  {
	public AnAwareSlaveIMInteractor(SimpleList<String> aHistory, Communicator aCommunicator) {
		super(aHistory, aCommunicator);
	}
	protected void addToHistory(String newValue) {
		communicator.toClient(AwareMasterIMModelLauncher.CLIENT_NAME, newValue);
	}
	
}
