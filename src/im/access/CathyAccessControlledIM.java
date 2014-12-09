package im.access;

import im.AliceIM;
import im.AnIMClientComposerAndLauncher;
import im.BobIM;
import im.CathyIM;
import trace.access.AccessTracerSetter;
import trace.im.aware.AwareIMTracerSetter;
import util.session.Communicator;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class CathyAccessControlledIM extends CathyIM{	
	public static void main (String[] args) {
		Tracer.showInfo(true);
//		TraceableInfo.setPrintSource(true);
		
//		AwareIMTracerSetter.traceAwareIM();
		AccessTracerSetter.traceAccess();
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,  APPLICATION_NAME, Communicator.DIRECT};
		(new AnAccessControlledIMClientComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}	
}
