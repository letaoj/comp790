package im.centralized;

import im.AnIMInteractor;
import util.session.Communicator;
import echo.modular.SimpleList;

public class ASlaveIMInteractor extends AnIMInteractor  {
	public ASlaveIMInteractor(SlaveSimpleList<String> aHistory, Communicator aCommunicator) {
		super(aHistory, aCommunicator);
	}
	protected SlaveSimpleList slaveSimpleList() {
		return (SlaveSimpleList) history;
	}
	protected void addToHistory(String newValue) {
		slaveSimpleList().proxyAdd(newValue);
	}
	
}
