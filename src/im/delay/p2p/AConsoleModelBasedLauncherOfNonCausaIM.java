package im.delay.p2p;

import im.ALauncherOfIM;
import im.SessionManagerServerStarter;
import util.tags.DistributedTags;
import bus.uigen.pipe.AConsoleModelBasedLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class AConsoleModelBasedLauncherOfNonCausaIM extends ALauncherOfIM {
	
	
	public Class sessionManagerClass() {
		return SessionManagerServerStarter.class;
	}
	
	public Class aliceClass() {
		return 	AliceP2PDelayingIM.class;
	}
	
	public Class bobClass() {
		return 	BobP2PIM.class;
	}
	
	public Class cathyClass() {
		return 	CathyP2PIM.class;
	}
	
	public static void main(String args[]) {
		(new AConsoleModelBasedLauncherOfNonCausaIM()).launchWithConsoles();
	}

}
