package im.delay.p2p;

import echo.modular.LauncherOfModularEchoTester;
import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.pipe.ConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;

public class LauncherOfNonCausalIMDemoer {
	
	public static ConsoleModelBasedDemoerAndTester createNewDemoer(boolean anInteractive) {
		return new ANonCausalIMConsoleModelBasedDemoerAndTester(anInteractive);
	}
//	public static DemoerAndTester createDemoer() {
//		return new AnIMDemoerAndTester();
//	}
	public static void main(String args[]) {
//		oldLaunch();
		newLaunch();
	}
	
	
	public static void newLaunch() {
		ConsoleModelBasedDemoerAndTester demoer = createNewDemoer(true);
		MainClassListLauncher aLauncher = demoer.createAndDisplayLauncher();
		demoer.executeAll();
	}

}
