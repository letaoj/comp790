package echo.modular;


public interface SimpleList<ElementType> {
	 void add(int index, ElementType input) ;
	 void add(ElementType input);
//	 void add(ElementType input) ;
	 void observableAdd(int index, ElementType input) ;
	 void observableAdd(ElementType input) ;
	 int size();
	 ElementType get(int index);
	 void addObserver(ListObserver<ElementType> anObserver) ;
	ElementType remove(int anIndex);
	boolean remove(ElementType anElement);
	boolean observableRemove(ElementType anElement);
	void removeObserver(ListObserver<ElementType> anObserver);
		
}
