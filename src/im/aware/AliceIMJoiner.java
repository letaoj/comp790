package im.aware;

import im.AliceIM;
import trace.im.aware.AwareIMTracerSetter;
import util.remote.AProcessExecer;
import util.session.Communicator;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.session.ClientJoinNotificationReceived;

public class AliceIMJoiner extends AliceIM{	
	public static void main (String[] args) {
		TraceableInfo.setPrintSource(true);
		AwareIMTracerSetter.traceAwareIM();
		Tracer.setKeywordPrintStatus(AProcessExecer.class, true);
		Tracer.showInfo(false);

		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, null,  Communicator.DIRECT};
//		String[] launcherArgs = {SESSION_SERVER_HOST, "foo", USER_NAME, USER_NAME,  Communicator.DIRECT};

		(new AJoiningIMComposerAndLauncher()).compose(launcherArgs);
	}	
}
