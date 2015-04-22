package trace.ft;

import trace.echo.modular.OperationName;
import trace.im.CommunicatedListEditInfo;

public class SentRequestSent extends CommunicatedListEditInfo {

  public SentRequestSent(String aMessage, String aProcessName, OperationName anOperationName,
      int anIndex, Object anElement, String aList, String aDestinationOrSource, Object aFinder) {
    super(aMessage, aProcessName, anOperationName, anIndex, anElement, aList, aDestinationOrSource,
        aFinder);
  }

  public SentRequestSent(String aMessage, CommunicatedListEditInfo aListEditInfo) {
    super(aMessage, aListEditInfo);
  }

  public static SentRequestSent toTraceable(String aMessage) {
    CommunicatedListEditInfo aListEditInfo = CommunicatedListEditInfo.toTraceable(aMessage);
    return new SentRequestSent(aMessage, aListEditInfo);
  }

  public static SentRequestSent newCase(String aProcessName, OperationName anOperationName,
      int anIndex, Object anElement, String aList, String aSourceOrDestination, Object aFinder) {

    String aMessage =
        toString(aProcessName, anOperationName, anIndex, anElement, aList, aSourceOrDestination);
    SentRequestSent retVal =
        new SentRequestSent(aMessage, aProcessName, anOperationName, anIndex, anElement, aList,
            aSourceOrDestination, aFinder);
    retVal.announce();
    return retVal;
  }

}
