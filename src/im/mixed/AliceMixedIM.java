package im.mixed;

import im.AliceIM;
import trace.im.IMTracerSetter;
import util.trace.TraceableInfo;
import util.trace.session.SessionTracerSetter;
/*
 * This does not relaly work, cannot do both direct and p2p
 */
public class AliceMixedIM extends AliceIM{	
	public static void main (String[] args) {
//		Tracer.showInfo(true);
		TraceableInfo.setPrintSource(true);
//		AwareIMTracerSetter.traceAwareIM();
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();
		SessionTracerSetter.setSessionPrintStatus();
//		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, AnIMComposerAndLauncher.DIRECT};
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME,  USER_NAME, APPLICATION_NAME};

		(new AP2PAndRelayedIMComposerAndLauncher()).composeAndLaunch(launcherArgs);

	}	
}
