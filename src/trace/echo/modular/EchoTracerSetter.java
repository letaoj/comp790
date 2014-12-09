package trace.echo.modular;

import trace.echo.ListEditInput;
import trace.echo.ListEditMade;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.console.ConsoleError;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import util.trace.console.ConsoleTraceSetter;
import util.trace.session.SessionTracerSetter;

public class EchoTracerSetter {
	
	public static void traceEchoer() {

		Tracer.showInfo(true);


		EchoTracerSetter.setTraceParameters();
		setEchoerPrintStatus();		
	}
	
	public static void setTraceParameters() {
		TraceableInfo.setPrintSource(true);
		Traceable.setPrintTime(false);
		Traceable.setPrintThread(true);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
	}
	
	public static void setEchoerPrintStatus() {
;
//		ConsoleTraceSetter.traceConsole();	// needed for Echo Tracer
//		ConsoleTraceSetter.setConsolePrintStatus();	// needed for Echo Tracer

		Tracer.setKeywordPrintStatus(ListEditInput.class, true);
		Tracer.setKeywordPrintStatus(ListEditNotified.class, true);
		Tracer.setKeywordPrintStatus(ListEditObserved.class, true);
		Tracer.setKeywordPrintStatus(ListEditMade.class, true);





	}

}
