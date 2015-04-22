package trace.ft;

import util.trace.session.ProcessInfo;

public class HistoryRecovered extends ProcessInfo {

  public HistoryRecovered(String aMessage, String aProcessName, Object aFinder) {
    super(aMessage, aProcessName, aFinder);
  }

  public static HistoryRecovered newcase(String aMessage, String aProcessName, Object aFinder) {
    HistoryRecovered retVal = new HistoryRecovered(aMessage, aProcessName, aFinder);
    retVal.announce();
    return retVal;
  }

}
