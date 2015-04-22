package trace.ft;

import trace.echo.modular.OperationName;
import trace.im.CommunicatedListEditInfo;

public class SentRequestReceived extends CommunicatedListEditInfo {

  public SentRequestReceived(String aMessage, String aProcessName, OperationName anOperationName,
      int anIndex, Object anElement, String aList, String aDestinationOrSource, Object aFinder) {
    super(aMessage, aProcessName, anOperationName, anIndex, anElement, aList, aDestinationOrSource,
        aFinder);
  }

  public SentRequestReceived(String aMessage, CommunicatedListEditInfo aListEditInfo) {
    super(aMessage, aListEditInfo);
  }

  public static SentRequestReceived toTraceable(String aMessage) {
    CommunicatedListEditInfo aListEditInfo = CommunicatedListEditInfo.toTraceable(aMessage);
    return new SentRequestReceived(aMessage, aListEditInfo);
  }

  public static SentRequestReceived newCase(String aProcessName, OperationName anOperationName,
      int anIndex, Object anElement, String aList, String aSourceOrDestination, Object aFinder) {

    String aMessage =
        toString(aProcessName, anOperationName, anIndex, anElement, aList, aSourceOrDestination);
    SentRequestReceived retVal =
        new SentRequestReceived(aMessage, aProcessName, anOperationName, anIndex, anElement, aList,
            aSourceOrDestination, aFinder);
    retVal.announce();
    return retVal;
  }

}
