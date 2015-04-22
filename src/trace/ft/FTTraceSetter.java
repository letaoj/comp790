package trace.ft;

import trace.echo.modular.EchoTracerSetter;
import util.trace.Tracer;

public class FTTraceSetter extends EchoTracerSetter {

  public static void traceFT() {
    EchoTracerSetter.setTraceParameters();

    setFTPrintStatus();
  }

  public static void setFTPrintStatus() {
    Tracer.setKeywordPrintStatus(HistoryRecovered.class, true);
    Tracer.setKeywordPrintStatus(SentRequestSent.class, true);
    Tracer.setKeywordPrintStatus(SentRequestReceived.class, true);
    Tracer.setKeywordPrintStatus(MessageWithSequencerNumberReceived.class, true);
    Tracer.setKeywordPrintStatus(MessageWithSequencerNumberSent.class, true);
    Tracer.setKeywordPrintStatus(SequencerElected.class, true);
    Tracer.setKeywordPrintStatus(SequencerReElected.class, true);
  }

}
