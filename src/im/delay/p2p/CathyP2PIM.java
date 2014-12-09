package im.delay.p2p;
import im.AnIMClientComposerAndLauncher;
import im.CathyIM;
import im.IMClientComposerAndLauncher;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.session.SessionTracerSetter;
public class CathyP2PIM  extends CathyIM {	
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, Communicator.DIRECT};
		IMClientComposerAndLauncher composerAndLauncher = (new AnIMClientComposerAndLauncher());
		IMTracerSetter.traceIM();
//		SessionTracerSetter.setSessionPrintStatus();
		composerAndLauncher.composeAndLaunch(launcherArgs);
	}
	
}
