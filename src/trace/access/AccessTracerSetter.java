package trace.access;

import trace.control.UserActionDenied;
import trace.echo.modular.EchoTracerSetter;
import util.trace.Tracer;

public class AccessTracerSetter extends EchoTracerSetter{
	
	public static void traceAccess() {
		EchoTracerSetter.setTraceParameters();

		setAccessPrintStatus();		
	}
	
	public static void setAccessPrintStatus() {
		
		Tracer.setKeywordPrintStatus(UserActionDenied.class, true);
		Tracer.setKeywordPrintStatus(AdministerRightDeclined.class, true);
		Tracer.setKeywordPrintStatus(AdministerRightRequested.class, true);
		Tracer.setKeywordPrintStatus(AdministerRightGiven.class, true);
		Tracer.setKeywordPrintStatus(InputRightDeclined.class, true);
		Tracer.setKeywordPrintStatus(InputRightRequested.class, true);
		Tracer.setKeywordPrintStatus(InputRightGiven.class, true);
		Tracer.setKeywordPrintStatus(OwnerSet.class, true);




	}

}
