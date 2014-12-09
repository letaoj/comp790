package im.centralized.awareinteractor;

import im.AliceIM;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;


public class AliceAwareSlaveIM extends AliceIM{
//	public static final String USER_NAME = "Alice";
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME,  Communicator.DIRECT};
		Tracer.showInfo(true);
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();

//		SessionTracerSetter.traceSession();
//		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		(new AnAwareSlaveIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
