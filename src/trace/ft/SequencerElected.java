package trace.ft;

import util.trace.session.ProcessInfo;

public class SequencerElected extends ProcessInfo {

  public SequencerElected(String aMessage, String aProcessName, Object aFinder) {
    super(aMessage, aProcessName, aFinder);
    // TODO Auto-generated constructor stub
  }

  public static SequencerElected newcase(String aMessage, String aProcessName, Object aFinder) {
    SequencerElected retVal = new SequencerElected(aMessage, aProcessName, aFinder);
    retVal.announce();
    return retVal;
  }

}
