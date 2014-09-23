package Assignment2;

import java.io.Serializable;

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

}