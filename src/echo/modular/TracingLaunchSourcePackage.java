package echo.modular;

import trace.echo.ListEditMade;
import trace.echo.modular.EchoTracerSetter;
import trace.echo.modular.ListEditNotified;
import trace.echo.modular.ListEditObserved;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;

public class TracingLaunchSourcePackage {
	public static void main (String[] args) {
		Tracer.showInfo(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_PACKAGE_NAME);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		TraceableInfo.setPrintTraceable(true);
		TraceableInfo.setPrintSource(true);
		TraceableInfo.setPrintTime(true);
		TraceableInfo.setPrintThread(true);
		Tracer.setKeywordPrintStatus(ASimpleList.class, true);
		AnEchoComposerAndLauncher.traceUnawareLaunch(args);
	}

}
