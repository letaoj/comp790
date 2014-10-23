package Assignment3;

import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Traceable;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import Assignment2.ListEditInput;
import Assignment2.ListEditMade;
import Assignment2.ListEditNotified;
import Assignment2.ListEditObserved;

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
