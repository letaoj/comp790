package ft.letao;

import java.io.Serializable;

public class AMessageWithSeqNumFromSequencer implements Serializable, MessageWithObj {

  private int seqNum;
  private Object message;

  public AMessageWithSeqNumFromSequencer(int seqNum, Object message) {
    this.seqNum = seqNum;
    this.message = message;
  }

  public int getSeqNum() {
    return seqNum;
  }

  public Object getMessage() {
    return message;
  }

}
