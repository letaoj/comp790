package ot.letao;


public class OTManager {
  public boolean dynamicOT;
  protected String userName;
  protected boolean isServer;
  protected OTTimeStamp otTimeStamp;

  public OTManager(String userName, boolean isServer) {
    this.userName = userName;
    this.isServer = isServer;
    otTimeStamp = new OTTimeStamp(0, 0);
  }

  public boolean isDynamicCaulity() {
    return dynamicOT;
  }

  public void setdynamicOT(boolean dynamicOT) {
    this.dynamicOT = dynamicOT;
  }

}
