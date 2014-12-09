package echo.modular;

import trace.echo.ListEditMade;
import trace.echo.modular.EchoTracerSetter;
import trace.echo.modular.ListEditNotified;
import trace.echo.modular.ListEditObserved;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class TracingLaunchEventClass {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		TraceableInfo.setPrintSource(true);
		TraceableInfo.setPrintTime(true);
		TraceableInfo.setPrintThread(true);
		Tracer.setKeywordPrintStatus(ListEditMade.class, true);
		Tracer.setKeywordPrintStatus(ListEditObserved.class, true);
		AnEchoComposerAndLauncher.traceUnawareLaunch(args);
	}

}
