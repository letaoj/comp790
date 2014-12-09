package im.delay.p2p;
import im.AliceIM;
import im.AnIMClientComposerAndLauncher;
import im.IMClientComposerAndLauncher;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
public class AliceP2PDelayingIM extends AliceIM {	
	public static final int DELAY_TO_CATHY = 20000;
	public static final String ALICE_DELAY_CONTROLS = "Alice Delay Controls";

	public static void main (String[] args) {
		IMClientComposerAndLauncher composerAndLauncher = (new AnIMClientComposerAndLauncher());
		IMTracerSetter.traceIM();
//		SessionTracerSetter.setSessionPrintStatus();
		delayToCathyAndLaunch(composerAndLauncher);
	}
	public static void delayToCathyAndLaunch(IMClientComposerAndLauncher composerAndLauncher) {
		
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, IMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME, Communicator.DIRECT};

		composerAndLauncher.compose(launcherArgs);
		composerAndLauncher.getCommunicator().setMinimumDelayToPeer(CathyP2PIM.USER_NAME,  DELAY_TO_CATHY);
		P2PDelayAndJitterParameters delayAndJitterParameters = new AP2PDelayAndJitterParameters(composerAndLauncher.getCommunicator(), CathyP2PIM.USER_NAME);
		delayAndJitterParameters.setDelayVariation(DELAY_TO_CATHY);
		delayAndJitterParameters.setMinimumDelayToPeer(DELAY_TO_CATHY);

		OEFrame aFrame = ObjectEditor.edit(delayAndJitterParameters); 
		aFrame.getFrame().setName(ALICE_DELAY_CONTROLS);
		composerAndLauncher.launch();
		
	}

	
}
