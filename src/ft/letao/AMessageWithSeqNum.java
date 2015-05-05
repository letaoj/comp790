package ft.letao;

import java.io.Serializable;

public class AMessageWithSeqNum implements Serializable, MessageWithObj {
  private int seqNum;
  private Object message;

  public AMessageWithSeqNum(int seqNum, Object message) {
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
