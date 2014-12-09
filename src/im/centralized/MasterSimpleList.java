package im.centralized;

import echo.modular.SimpleList;

public interface MasterSimpleList<ElementType> extends SimpleList<ElementType> {
	void centralizedAdd(ElementType newVal, String aUserName);
	

}
