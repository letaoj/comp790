package ot.letao;

import java.io.Serializable;

public class AMessageWithTimeStamp implements Serializable {

  Object message;
  OTTimeStamp vectorTimeStamp;

  public AMessageWithTimeStamp(Object theMessage, OTTimeStamp theTimeStamp) {
    message = theMessage;
    vectorTimeStamp = theTimeStamp;
  }

  public Object getMessage() {
    return message;
  }

  public OTTimeStamp getVectorTimeStamp() {
    return vectorTimeStamp;
  }


}
