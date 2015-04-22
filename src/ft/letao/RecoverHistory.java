package ft.letao;

import java.io.Serializable;

public class RecoverHistory implements Serializable {
  private String aClientName;

  public RecoverHistory(String aClientName) {
    this.aClientName = aClientName;
  }

  public String getClientName() {
    return aClientName;
  }
}
