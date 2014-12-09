package im;

import util.trace.console.ConsoleTraceSetter;
import bus.uigen.pipe.ConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.DemoerAndTester;

public class LauncherOfIMTester extends LauncherOfIMDemoer {
	public static void main(String args[]) {
		// needed for Echo Tracing
		ConsoleTraceSetter.traceConsole();	
		
		ConsoleModelBasedDemoerAndTester aDemoer = createNewDemoer(true);
		aDemoer.getTranscriptBasedTester().setCorrectSubFolder("AnIMDemoerAndTester");
		aDemoer.getTranscriptBasedTester().setTestSubFolder("AnIMDemoerAndTester");
//		Boolean retVal = aDemoer.executeLoadAndTest(true, false);
		Boolean retVal = aDemoer.executeLoadAndTest(false, true);

		System.out.println("Test result:" + retVal);
		aDemoer.terminate();
	}
//	public static void main(String args[]) {
//		// needed for Echo Tracing
//		ConsoleTraceSetter.traceConsole();	
//		
//		DemoerAndTester aDemoer = createOldDemoer(true);
////		aDemoer.getTranscriptBasedTester().setCorrectSubFolder("AnIMDemoerAndTester");
////		aDemoer.getTranscriptBasedTester().setTestSubFolder("AnIMDemoerAndTester");
////		Boolean retVal = aDemoer.executeLoadAndTest(true, false);
//		Boolean retVal = aDemoer.executeLoadAndTest(false, true);
//
//		System.out.println("Test result:" + retVal);
//		aDemoer.terminate();
//	}


}
