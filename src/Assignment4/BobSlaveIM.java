package Assignment4;

import trace.locking.LockTracerSetter;
import util.session.Communicator;
import util.tags.DistributedTags;
import util.trace.Tracer;
import util.trace.session.SessionTracerSetter;

public class BobSlaveIM implements ExampleGUISession {
	public static final String USER_NAME = DistributedTags.CLIENT_2;

	public static void main(String[] args) {
		
		String[] launcherArgs = { SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,
				APPLICATION_NAME, Communicator.DIRECT };
		Tracer.showInfo(true);
		SessionTracerSetter.setSessionPrintStatus();
		LockTracerSetter.traceLock();

		(new ASlaveGUIComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}

}