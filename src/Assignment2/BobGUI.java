package Assignment2;

import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.Tracer;

@Tags({ DistributedTags.CLIENT_2, ApplicationTags.IM, ApplicationTags.EDITOR })
public class BobGUI implements ExampleGUISession {
	public static final String USER_NAME = DistributedTags.CLIENT_2;

	public static void main(String[] args) {
		String[] launcherArgs = { SESSION_SERVER_HOST, SESSION_NAME, USER_NAME,
				APPLICATION_NAME_1, APPLICATION_NAME_2, Communicator.RELAYED };
		Tracer.showInfo(true);
		IMTracerSetter.traceIM();

		(new AGUIClientComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
}