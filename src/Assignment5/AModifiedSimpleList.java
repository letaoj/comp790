package Assignment5;

import java.util.List;

import trace.echo.ListEditMade;
import trace.echo.modular.ListEditNotified;
import trace.echo.modular.OperationName;
import util.annotations.Tags;
import util.tags.InteractiveTags;
import echo.modular.ASimpleList;
import echo.modular.ListObserver;

@Tags({ InteractiveTags.MODEL })
public class AModifiedSimpleList<ElementType> extends ASimpleList<ElementType> {

	String tag;

	public AModifiedSimpleList(String tag) {
		this.tag = tag;
	}

	public void notifyAdd(List<ListObserver<ElementType>> observers, int index,
			ElementType newValue) {
		ListEditNotified.newCase(OperationName.ADD, index, newValue, tag, this);
		for (ListObserver<ElementType> observer : observers)
			observer.elementAdded(index, newValue);
	}

	protected void traceAdd(int anIndex, ElementType anElement) {
		ListEditMade.newCase(OperationName.ADD, anIndex, anElement, tag, this);
	}

	protected void traceRemove(int anIndex, ElementType anElement) {
		ListEditMade.newCase(OperationName.DELETE, anIndex, anElement, tag,
				this);
	}

	public void notifyRemove(List<ListObserver<ElementType>> observers,
			int index, ElementType newValue) {
		ListEditNotified.newCase(OperationName.DELETE, index, newValue, tag,
				this);
		for (ListObserver<ElementType> observer : observers)
			observer.elementRemoved(index, newValue);
	}

}
