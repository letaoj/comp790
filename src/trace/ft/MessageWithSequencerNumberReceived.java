package trace.ft;

import trace.echo.modular.OperationName;
import trace.im.CommunicatedListEditInfo;

public class MessageWithSequencerNumberReceived extends CommunicatedListEditInfo {
  public MessageWithSequencerNumberReceived(String aMessage, String aProcessName,
      OperationName anOperationName, int anIndex, Object anElement, String aList,
      String aDestinationOrSource, Object aFinder) {
    super(aMessage, aProcessName, anOperationName, anIndex, anElement, aList, aDestinationOrSource,
        aFinder);
  }

  public MessageWithSequencerNumberReceived(String aMessage, CommunicatedListEditInfo aListEditInfo) {
    super(aMessage, aListEditInfo);
  }

  public static MessageWithSequencerNumberReceived toTraceable(String aMessage) {
    CommunicatedListEditInfo aListEditInfo = CommunicatedListEditInfo.toTraceable(aMessage);
    return new MessageWithSequencerNumberReceived(aMessage, aListEditInfo);
  }

  public static MessageWithSequencerNumberReceived newCase(String aProcessName,
      OperationName anOperationName, int anIndex, Object anElement, String aList,
      String aSourceOrDestination, Object aFinder) {

    String aMessage =
        toString(aProcessName, anOperationName, anIndex, anElement, aList, aSourceOrDestination);
    MessageWithSequencerNumberReceived retVal =
        new MessageWithSequencerNumberReceived(aMessage, aProcessName, anOperationName, anIndex,
            anElement, aList, aSourceOrDestination, aFinder);
    retVal.announce();
    return retVal;
  }
}
