package echo.monolithic;

import static im.IMUtililties.remoteEchoOf;
import static util.pipe.ConsoleModelUtility.containsText;
import static util.pipe.ConsoleModelUtility.getText;
import static util.pipe.ConsoleModelUtility.isConsole;
import static util.pipe.ConsoleModelUtility.isInfo;
import static util.pipe.ConsoleModelUtility.isOutput;
import static util.pipe.ConsoleModelUtility.isOutputLine;
import static util.trace.session.SessionTraceUtility.clientJoined;
import im.AHistoryInCoupler;
import im.BobIM;

import java.beans.PropertyChangeEvent;
import java.util.List;

import trace.echo.EchoTraceChecker;
import trace.echo.modular.ModularEchoTraceChecker;
import trace.im.IMTraceChecker;
import util.annotations.Tags;
import util.misc.ThreadSupport;
import util.pipe.ConsoleModel;
import util.pipe.InputGenerator;
import util.tags.ApplicationTags;
import util.trace.Traceable;
import bus.uigen.pipe.AConsoleModelBasedDemoerAndTester;
import bus.uigen.pipe.AConsoleModelBasedLauncher;
import bus.uigen.pipe.ADemoer;
import bus.uigen.pipe.ADemoerAndTester;
import bus.uigen.pipe.ATranscriptBasedTester;
import bus.uigen.pipe.ConsoleModelBasedLauncher;
import bus.uigen.pipe.Demoer;
import bus.uigen.pipe.DemoerAndTester;
import bus.uigen.pipe.MainClassListLauncher;
import bus.uigen.pipe.TranscriptBasedTester;
import bus.uigen.trace.TraceUtility;
@Tags(ApplicationTags.ECHOER)
public class AMonolithicEchoConsoleModelBasedDemoerAndTester extends AConsoleModelBasedDemoerAndTester {
	public AMonolithicEchoConsoleModelBasedDemoerAndTester(boolean anInteractive) {
		super(anInteractive);
		

	}
	protected InputGenerator createDemoer() {
		return new  AMonolithicEchoInputGenerator();
	}
	
	protected TranscriptBasedTester createTranscriptBasedTester() {
		return new AMonolithicEchoTranscriptBasedTester();
	}
	
	protected ConsoleModelBasedLauncher createConsoleModelBasedLauncher() {
		return new AConsoleModelBasedLauncherOfMonolithicEchoer();
	}
}
