package im.centralized.awareinteractor;

import im.ExampleIMSession;
import trace.im.IMTracerSetter;
import util.session.Communicator;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;


public class AwareMasterIMModelLauncher implements ExampleIMSession{
	public static final String CLIENT_NAME = "Model";
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, CLIENT_NAME,  APPLICATION_NAME,  Communicator.DIRECT};
		Tracer.showInfo(true);
		IMTracerSetter.traceIM();
		SessionTracerSetter.setSessionPrintStatus();

//		SessionTracerSetter.traceSession();
//		Tracer.setKeywordPrintStatus(ClientJoined.class, true);
		(new AnAwareMasterIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
