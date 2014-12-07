package ot.letao;

import java.io.Serializable;

import trace.echo.ListEditInfo;
import trace.echo.modular.OperationName;

public interface ListEdit<ElementType> extends Serializable {	
	int getIndex();
	void setIndex(int anIndex);
	ElementType getElement();
	void setElement(ElementType anElement);
	OperationName getOperationName();
	void setOperationName(OperationName name);
	public String getList() ;
	public void setList(String list) ;
	ListEdit copy();
	public ListEditInfo toListEditInfo();
	public boolean isServer();
	public void setServer(boolean isServer);
}