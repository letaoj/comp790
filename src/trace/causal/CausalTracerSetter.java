package trace.causal;

import trace.access.InputRightRequested;
import trace.control.UserActionDenied;
import trace.echo.modular.EchoTracerSetter;
import util.trace.Tracer;

public class CausalTracerSetter extends EchoTracerSetter{
	
	public static void traceCausal() {
		EchoTracerSetter.setTraceParameters();

		setCausalPrintStatus();		
	}
	
	public static void setCausalPrintStatus() {
		Tracer.setKeywordPrintStatus(ConcurrentVectorTimeStampedMessageDetected.class, true);		
		Tracer.setKeywordPrintStatus(LocalCountIncrementedInSiteVectorTimeStamp.class, true);
		Tracer.setKeywordPrintStatus(RemoteCountIncrementedInSiteVectorTimeStamp.class, true);
		Tracer.setKeywordPrintStatus(UserAddedToVectorTimeStamp.class, true);
		Tracer.setKeywordPrintStatus(VectorTimeStampCopiedAndNewBufferCreated.class, true);
		Tracer.setKeywordPrintStatus(VectorTimeStampCreated.class, true);
		Tracer.setKeywordPrintStatus(VectorTimeStampedMessageBuffered.class, true);
		Tracer.setKeywordPrintStatus(VectorTimeStampedMessageDelivered.class, true);
		Tracer.setKeywordPrintStatus(VectorTimeStampedMessageReceived.class, true);	
		Tracer.setKeywordPrintStatus(VectorTimeStampedMessageRemovedFromBuffer.class, true);	
		Tracer.setKeywordPrintStatus(VectorTimeStampedMessageSent.class, true);		
	}

}
