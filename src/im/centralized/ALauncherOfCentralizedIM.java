package im.centralized;

import im.ALauncherOfIM;
import im.AliceIM;
import im.LauncherOfIM;
import util.tags.DistributedTags;
import bus.uigen.pipe.AConsoleModelBasedLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class ALauncherOfCentralizedIM extends ALauncherOfIM implements LauncherOfIM {
	
	public Class[] mainClasses() {
		return new Class[] { 
				sessionManagerClass(), 
				modelClass(),
				aliceClass(), 
				bobClass(),		
				cathyClass() 
			};
	}
	public String[] processNames() {
		return new String[] {
				sessionManagerName(), 
				masterName(),
				aliceName(), 
				bobName(),		
				cathyName() 
		};
	}
	public String masterName() {
		return MasterIMModelLauncher.CLIENT_NAME;
		// return AliceOTIM.class;
	}
	public Class modelClass() {
		return MasterIMModelLauncher.class;
		// return OTSessionManagerServerStarter.class;

	}
	

	public Class aliceClass() {
		return AliceSlaveIM.class;
		// return AliceOTIM.class;
	}
	

	public Class bobClass() {
		return BobSlaveIM.class;
		// return BobOTIM.class;
	}
	

	public Class cathyClass() {
		return CathySlaveIM.class;
		// return CathyOTIM.class;
	}
	
	
//	public  void launch() {	
////		Class[] classes = {
////				SessionManagerServerStarter.class,
////				AliceIM.class,
////				BobIM.class,
////				CathyIM.class				
////		};
//		MainClassLaunchingUtility.createInteractiveLauncher(composeMainClasses());
//	}
	
	public static void main(String args[]) {
		(new ALauncherOfCentralizedIM()).launchWithConsoles();
	}

}
