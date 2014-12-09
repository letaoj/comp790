package im.access;

import im.ALauncherOfIM;
import im.LauncherOfIM;
import util.tags.DistributedTags;
import bus.uigen.pipe.AConsoleModelBasedLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class ALauncherOfAccessControlledIM extends ALauncherOfIM implements LauncherOfIM {
	
	
	public Class aliceClass() {
		return AliceAccessControlledIM.class;
	}
	
	public Class bobClass() {
		return BobAccessControlledIM.class;
	}
	

	public Class cathyClass() {
		return CathyAccessControlledIM.class;
	}
	
	
	
	public static void main(String args[]) {
		(new ALauncherOfAccessControlledIM()).launchWithConsoles();
	}

}
