package echo.modular;

import echo.monolithic.AConsoleModelBasedLauncherOfMonolithicEchoer;
import bus.uigen.pipe.AConsoleModelBasedLauncher;
import bus.uigen.pipe.ConsoleModelBasedLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;

public class AConsoleModelBasedLauncherOfModularEchoer extends AConsoleModelBasedLauncherOfMonolithicEchoer implements ConsoleModelBasedLauncher{
	

	public Class echoClass() {
//		return AnEchoComposerAndLauncher.class;
		return TestableTracingLaunch.class;
//		return NonTracingLaunch.class;

	}

	

	
	public static void main(String args[]) {
		(new AConsoleModelBasedLauncherOfModularEchoer()).launchWithConsoles();
	}

}
