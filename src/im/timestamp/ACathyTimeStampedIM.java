package im.timestamp;

import util.session.Communicator;


public class ACathyTimeStampedIM {
	public static final String SESSION_NAME = "FrostySession";
	public static final String USER_NAME = "Cathy";
	public static final String SESSION_SERVER_HOST = "localhost";
	public static void main (String[] args) {
		String[] launcherArgs = {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, Communicator.DIRECT};
		(new ATimeStampingIMComposerAndLauncher()).composeAndLaunch(launcherArgs);
	}
	
}
