package im;

import echo.modular.LauncherOfModularEchoTester;
import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.pipe.ConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;

public class LauncherOfIMDemoer {
	public static DemoerAndTester createOldDemoer(boolean anInteractive) {
		return new AnIMDemoerAndTester(anInteractive);
	}
	public static ConsoleModelBasedDemoerAndTester createNewDemoer(boolean anInteractive) {
		return new AnIMConsoleModelBasedDemoerAndTester(anInteractive);
	}
//	public static DemoerAndTester createDemoer() {
//		return new AnIMDemoerAndTester();
//	}
	public static void main(String args[]) {
//		oldLaunch();
		newLaunch();
	}
	
	public static void oldLaunch() {
		DemoerAndTester demoer = createOldDemoer(true);
		MainClassListLauncher aLauncher = demoer.createAndDisplayLauncher();
		demoer.executeAll();
	}
	public static void newLaunch() {
		ConsoleModelBasedDemoerAndTester demoer = createNewDemoer(true);
		MainClassListLauncher aLauncher = demoer.createAndDisplayLauncher();
		demoer.executeAll();
	}

}
