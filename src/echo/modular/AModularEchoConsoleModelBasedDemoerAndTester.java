package echo.modular;

import java.util.List;

import trace.echo.modular.ModularEchoTraceChecker;
import util.trace.Traceable;
import bus.uigen.pipe.ConsoleModelBasedLauncher;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.TranscriptBasedTester;
import echo.monolithic.AConsoleModelBasedLauncherOfMonolithicEchoer;
import echo.monolithic.AMonolithicEchoConsoleModelBasedDemoerAndTester;
import echo.monolithic.AMonolithicEchoDemoerAndTester;
import echo.monolithic.AMonolithicEchoTranscriptBasedTester;

public class AModularEchoConsoleModelBasedDemoerAndTester 
		extends AMonolithicEchoConsoleModelBasedDemoerAndTester {

	

	
	
	public AModularEchoConsoleModelBasedDemoerAndTester(boolean anInteractive) {
		super(anInteractive);
	}
	
	
	
	protected ConsoleModelBasedLauncher createConsoleModelBasedLauncher() {
		return new AConsoleModelBasedLauncherOfModularEchoer();
	}
	 
	protected TranscriptBasedTester createTranscriptBasedTester() {
		return new AModularEchoTranscriptBasedTester();
	}
	
	
}
