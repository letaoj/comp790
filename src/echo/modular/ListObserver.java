package echo.modular;

public interface ListObserver<ElementType> {
	void elementAdded(int anIndex, ElementType aNewValue);
	void elementRemoved(int anIndex, ElementType aNewValue);

}
