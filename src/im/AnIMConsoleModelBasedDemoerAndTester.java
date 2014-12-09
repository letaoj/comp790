package im;

import bus.uigen.pipe.ConsoleModelBasedLauncher;
import bus.uigen.pipe.Demoer;
import bus.uigen.pipe.TranscriptBasedTester;
import echo.modular.AModularEchoConsoleModelBasedDemoerAndTester;

public class AnIMConsoleModelBasedDemoerAndTester extends AModularEchoConsoleModelBasedDemoerAndTester {
//	IMDemoer imDemoer;
	
	public AnIMConsoleModelBasedDemoerAndTester(boolean anInteractive) {
		super(anInteractive);
//		imDemoer = new AnIMConsoleModelBasedDemoer();
		
	}
	protected Demoer createDemoer() {
		return new AnIMInputGenerator();
	}
	
	protected TranscriptBasedTester createTranscriptBasedTester() {
		return new AnIMTranscriptBasedTester((IMFinalOutputs) getDemoer());
	}
	
	protected ConsoleModelBasedLauncher createConsoleModelBasedLauncher() {
		return new ALauncherOfIM();
	}
	
	
}
