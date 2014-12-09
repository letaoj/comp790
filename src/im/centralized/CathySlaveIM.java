package im.centralized;

import im.BobIM;
import im.CathyIM;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.session.SessionTracerSetter;


public class CathySlaveIM extends CathyIM{
//	public static final String USER_NAME = "Bob";
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME,  Communicator.DIRECT};
//		Tracer.showInfo(true);
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();

//		SessionTracerSetter.traceSession();
//		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		(new ASlaveIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
