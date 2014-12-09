package Assignment6;

import java.io.Serializable;

public class OTMessage implements Serializable {

  Object message;
  OTTimeStamp otTimeStamp;

  public OTMessage(Object message, OTTimeStamp otTimeStamp) {
    this.message = message;
    this.otTimeStamp = otTimeStamp;
  }

  public Object getMessage() {
    return message;
  }

  public OTTimeStamp getOTTimeStamp() {
    return otTimeStamp;
  }
}
