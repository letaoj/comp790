package im.centralized.awareinteractor;

import im.ALauncherOfIM;
import im.LauncherOfIM;
import util.tags.DistributedTags;
import bus.uigen.pipe.AConsoleModelBasedLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class AnAwareLauncherOfCentralizedIM extends ALauncherOfIM implements LauncherOfIM {
	
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
				aliceName(), 
				bobName(),		
				cathyName() 
		};
	}
	public Class modelClass() {
		return AwareMasterIMModelLauncher.class;
		// return OTSessionManagerServerStarter.class;

	}
	

	public Class aliceClass() {
		return AliceAwareSlaveIM.class;
		// return AliceOTIM.class;
	}
	

	public Class bobClass() {
		return BobAwareSlaveIM.class;
		// return BobOTIM.class;
	}
	

	public Class cathyClass() {
		return CathyAwareSlaveIM.class;
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
		(new AnAwareLauncherOfCentralizedIM()).launchWithConsoles();
	}

}
