package im.delay.p2p;

import static im.IMUtililties.remoteEchoOf;
import static util.pipe.ConsoleModelUtility.containsText;
import static util.pipe.ConsoleModelUtility.getText;
import static util.pipe.ConsoleModelUtility.isConsole;
import static util.pipe.ConsoleModelUtility.isInfo;
import static util.pipe.ConsoleModelUtility.isOutput;
import static util.pipe.ConsoleModelUtility.isOutputLine;
import static util.trace.session.SessionTraceUtility.clientJoined;
import im.ALauncherOfIM;
import im.AliceIM;
import im.AnIMInputGenerator;
import im.AnIMConsoleModelBasedDemoerAndTester;
import im.AnIMDemoerAndTester;
import im.AnIMTranscriptBasedTester;
import im.BobIM;
import im.CathyIM;
import im.IMFinalOutputs;

import java.beans.PropertyChangeEvent;
import java.util.List;

import echo.modular.AModularEchoDemoerAndTester;
import echo.modular.AnEchoInteractor;
import trace.im.IMTraceChecker;
import trace.ot.OTTraceChecker;
import util.misc.ThreadSupport;
import util.pipe.ConsoleModel;
import util.tags.DistributedTags;
import util.trace.Traceable;
import bus.uigen.pipe.ADemoerAndTester;
import bus.uigen.pipe.ConsoleModelBasedLauncher;
import bus.uigen.pipe.Demoer;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;
import bus.uigen.pipe.TranscriptBasedTester;
import bus.uigen.trace.TraceUtility;

public class ANonCausalIMConsoleModelBasedDemoerAndTester extends AnIMConsoleModelBasedDemoerAndTester {
	
	public ANonCausalIMConsoleModelBasedDemoerAndTester(boolean anInteractive) {
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
		return new AConsoleModelBasedLauncherOfNonCausaIM();
	}
	

}
