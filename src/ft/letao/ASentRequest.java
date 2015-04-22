package ft.letao;

import im.ListEdit;

import java.io.Serializable;

public class ASentRequest<T> implements Serializable, SentRequest {
  private String clientName;
  private ListEdit<T> listEdit;
  private String ftType;

  public ASentRequest(String clientName, ListEdit<T> listEdit, String variant) {
    this.clientName = clientName;
    this.listEdit = listEdit;
    this.ftType = variant;
  }

  public String getClientName() {
    return clientName;
  }

  public ListEdit<T> getListEdit() {
    return listEdit;
  }

  public String getFTType() {
    return ftType;
  }
}
