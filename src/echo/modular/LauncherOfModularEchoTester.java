package echo.modular;

import trace.echo.EchoTraceChecker;
import trace.echo.modular.ModularEchoTraceChecker;
import trace.echo.modular.EchoTracerSetter;
import util.misc.ThreadSupport;
import util.trace.console.ConsoleTraceSetter;
import bus.uigen.pipe.ConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;

public class LauncherOfModularEchoTester {
	public static ConsoleModelBasedDemoerAndTester createDemoer(boolean anInteractive) {
		return new AModularEchoConsoleModelBasedDemoerAndTester(anInteractive);
	}
	public static void main(String args[]) {
		// make sure that AnEchoerComposerAndLauncher has echoing of tracer
		ConsoleTraceSetter.traceConsole();	// needed for Echo Tracer
		ConsoleModelBasedDemoerAndTester aDemoer = createDemoer(false);
		aDemoer.getTranscriptBasedTester().setCorrectSubFolder("AModularEchoDemoerAndTester");
		aDemoer.getTranscriptBasedTester().setTestSubFolder("AModularEchoDemoerAndTester");
		// correct
//		Boolean retVal = aDemoer.executeLoadAndTest(true, false);
		// test
		Boolean retVal = aDemoer.executeLoadAndTest(false, true);
		System.out.println("Test result:" + retVal);
		aDemoer.terminate();
	}

}
