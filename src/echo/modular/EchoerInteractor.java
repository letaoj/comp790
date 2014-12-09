package echo.modular;

import echo.monolithic.EchoUtilities;

public interface EchoerInteractor extends ListObserver {
//	public static final String ECHO_INDICATOR = EchoUtilities.ECHO_INDICATOR;
//	public static final String QUIT = EchoUtilities.QUIT;
//	public static final String HISTORY = EchoUtilities.HISTORY;
	public void doInput() ;
	public  void printHistory() ;
//	String toString(History aHistory);

}
