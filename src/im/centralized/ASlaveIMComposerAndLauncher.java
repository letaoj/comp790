package im.centralized;

import im.AnIMClientComposerAndLauncher;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
import echo.modular.ASimpleList;
import echo.modular.EchoerInteractor;
import echo.modular.SimpleList;
public class ASlaveIMComposerAndLauncher extends AnIMClientComposerAndLauncher {
	
	
	protected SlaveSimpleList<String> createHistory() {
		return new ASlaveSimpleList<String>(communicator);
	}
	protected EchoerInteractor createInteractor() {
		return new ASlaveIMInteractor( (SlaveSimpleList) history, communicator);
	}		

	protected  void addHistoryInCoupler(Communicator communicator, SimpleList<String> aHistory) {
		historyInCoupler = new ASlaveInCoupler(aHistory, communicator.getClientName());
		communicator.addPeerMessageListener(historyInCoupler);
	}
	public static void main (String[] args) {
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		(new ASlaveIMComposerAndLauncher()).composeAndLaunch(args);
	}
}
