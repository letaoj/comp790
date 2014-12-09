package echo.modular;

import trace.echo.modular.ModularEchoTraceChecker;
import bus.uigen.pipe.ConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;

public class LauncherOfModularEchoDemoer {
	public static ConsoleModelBasedDemoerAndTester createDemoer(boolean anInteractive) {
		return new AModularEchoConsoleModelBasedDemoerAndTester(anInteractive);
	}
	public static void main(String args[]) {
		ConsoleModelBasedDemoerAndTester aDemoer = createDemoer(true);
		aDemoer.demo();
		aDemoer.terminate();

	}

}
