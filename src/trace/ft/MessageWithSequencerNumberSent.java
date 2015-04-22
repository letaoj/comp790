package trace.ft;

import trace.echo.modular.OperationName;
import trace.im.CommunicatedListEditInfo;

public class MessageWithSequencerNumberSent extends CommunicatedListEditInfo {
  public MessageWithSequencerNumberSent(String aMessage, String aProcessName,
      OperationName anOperationName, int anIndex, Object anElement, String aList,
      String aDestinationOrSource, Object aFinder) {
    super(aMessage, aProcessName, anOperationName, anIndex, anElement, aList, aDestinationOrSource,
        aFinder);
  }

  public MessageWithSequencerNumberSent(String aMessage, CommunicatedListEditInfo aListEditInfo) {
    super(aMessage, aListEditInfo);
  }

  public static MessageWithSequencerNumberSent toTraceable(String aMessage) {
    CommunicatedListEditInfo aListEditInfo = CommunicatedListEditInfo.toTraceable(aMessage);
    return new MessageWithSequencerNumberSent(aMessage, aListEditInfo);
  }

  public static MessageWithSequencerNumberSent newCase(String aProcessName,
      OperationName anOperationName, int anIndex, Object anElement, String aList,
      String aSourceOrDestination, Object aFinder) {

    String aMessage =
        toString(aProcessName, anOperationName, anIndex, anElement, aList, aSourceOrDestination);
    MessageWithSequencerNumberSent retVal =
        new MessageWithSequencerNumberSent(aMessage, aProcessName, anOperationName, anIndex,
            anElement, aList, aSourceOrDestination, aFinder);
    retVal.announce();
    return retVal;
  }
}
