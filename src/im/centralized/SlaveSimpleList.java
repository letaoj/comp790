package im.centralized;

import echo.modular.SimpleList;

public interface SlaveSimpleList<ElementType> extends SimpleList<ElementType>{
	public void  proxyAdd(ElementType input) ;

}
