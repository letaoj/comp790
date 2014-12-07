package Assignment6;


public class OTManager {
  public boolean dynamicOT;
  protected String userName;
  protected boolean isServer;

  public OTManager(String userName, boolean isServer) {
    this.userName = userName;
    this.isServer = isServer;
  }

  public boolean isDynamicCaulity() {
    return dynamicOT;
  }

  public void setdynamicOT(boolean dynamicOT) {
    this.dynamicOT = dynamicOT;
  }

}
