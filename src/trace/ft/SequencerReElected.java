package trace.ft;

import util.trace.session.ProcessInfo;

public class SequencerReElected extends ProcessInfo {

  public SequencerReElected(String aMessage, String aProcessName, Object aFinder) {
    super(aMessage, aProcessName, aFinder);
    // TODO Auto-generated constructor stub
  }

  public static SequencerReElected newcase(String aMessage, String aProcessName, Object aFinder) {
    SequencerReElected retVal = new SequencerReElected(aMessage, aProcessName, aFinder);
    retVal.announce();
    return retVal;
  }

}
