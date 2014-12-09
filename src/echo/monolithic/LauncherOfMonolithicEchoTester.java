package echo.monolithic;

import trace.echo.EchoTraceChecker;
import trace.echo.modular.ModularEchoTraceChecker;
import trace.echo.modular.EchoTracerSetter;
import util.misc.ThreadSupport;
import util.trace.console.ConsoleTraceSetter;
import bus.uigen.pipe.ConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;

public class LauncherOfMonolithicEchoTester extends LauncherOfMonolithicEchoDemoer{
	public static DemoerAndTester createOldDemoer() {
		return new AMonolithicEchoDemoerAndTester(false);
	}
	public static void oldLaunch() {
		ConsoleTraceSetter.traceConsole(); // needed for comparing outputs

		DemoerAndTester aDemoer = createOldDemoer();


		Boolean retVal = aDemoer.executeLoadAndTest(false, true);

		System.out.println("Test result:" + retVal);


		aDemoer.terminate();
	}
	public static ConsoleModelBasedDemoerAndTester createNewDemoer() {
		return new AMonolithicEchoConsoleModelBasedDemoerAndTester(false);
//		return new AMonolithicEchoConsoleModelBasedDemoerAndTester(true);

	}
	public static void newLaunch() {
		ConsoleTraceSetter.traceConsole(); // needed for comparing outputs

		ConsoleModelBasedDemoerAndTester aDemoer = createNewDemoer();

		aDemoer.getTranscriptBasedTester().setCorrectSubFolder("AMonolithicEchoDemoerAndTester");
		aDemoer.getTranscriptBasedTester().setTestSubFolder("AMonolithicEchoDemoerAndTester");
		Boolean retVal = aDemoer.executeLoadAndTest(false, true);

		System.out.println("Test result:" + retVal);


		aDemoer.terminate();
	}
	public static void main(String args[]) {

		newLaunch();
	}

}
