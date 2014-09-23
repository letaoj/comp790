package Assignment2;



public class ListEditDisplayed extends ListEditInfo{

	public ListEditDisplayed(String aMessage, OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {
		super(aMessage, anOperationName, anIndex, anElement, aList, aFinder);
	}
	public ListEditDisplayed(String aMessage, ListEditInfo aListEditInfo) {
		super(aMessage, aListEditInfo);
	}

	public static ListEditDisplayed toTraceable (String aMessage) {
		ListEditInfo aListEditInfo = ListEditInfo.toTraceable(aMessage);
		return new ListEditDisplayed(aMessage, aListEditInfo);
	}
	public static ListEditDisplayed newCase(
			OperationName anOperationName, int anIndex,
			Object anElement, String aList, Object aFinder) {			
		String aMessage = toString(anOperationName, anIndex, anElement, aList);
		ListEditDisplayed retVal = new ListEditDisplayed(aMessage, anOperationName, anIndex, anElement,aList, aFinder);
		retVal.announce();
		return retVal;
	}

}
