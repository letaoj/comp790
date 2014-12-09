package im.centralized.awareinteractor;

import im.AnIMClientComposerAndLauncher;
import im.centralized.ASlaveInCoupler;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;
import echo.modular.ASimpleList;
import echo.modular.EchoerInteractor;
import echo.modular.SimpleList;
public class AnAwareSlaveIMComposerAndLauncher extends AnIMClientComposerAndLauncher {
	
	
	protected SimpleList<String> createHistory() {
		return new ASimpleList<String>();
	}
	protected EchoerInteractor createInteractor() {
		return new AnAwareSlaveIMInteractor( history, communicator);
	}		

	protected  void addHistoryInCoupler(Communicator communicator, SimpleList<String> aHistory) {
		historyInCoupler = new ASlaveInCoupler(aHistory, communicator.getClientName());
		communicator.addPeerMessageListener(historyInCoupler);
	}
	public static void main (String[] args) {
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		(new AnAwareSlaveIMComposerAndLauncher()).composeAndLaunch(args);
	}
}
