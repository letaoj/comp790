package echo.monolithic;

import static bus.uigen.query.QueryUtility.inOrder;
import static bus.uigen.query.QueryUtility.indexOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import trace.echo.EchoTraceChecker;
import util.annotations.Tags;
import util.pipe.ConsoleModel;
import util.tags.ApplicationTags;
import util.trace.Traceable;
import util.trace.TraceableLog;
import util.trace.Tracer;
import util.trace.console.ConsoleInput;
import util.trace.console.ConsoleOutput;
import bus.uigen.pipe.ATranscriptBasedTester;
import bus.uigen.trace.TraceUtility;
@Tags(ApplicationTags.ECHOER)
public class AMonolithicEchoTranscriptBasedTester extends ATranscriptBasedTester {
	protected ConsoleModel echoConsole;
	protected boolean echoTestSucceeded;
	protected boolean echoTranscriptTestSucceeded;

	public static long DEFAULT_TIME_OUT = 5000;
	static Class[] expectedClasses = {
		ConsoleOutput.class,
};

public static Class[] expectedClasses() {
	return expectedClasses;
}


	
	public boolean intraSequenceTest(List<Traceable> aTraceableList) {
//		System.out.println("Intra Sequence Check: Echoer");
		echoTestSucceeded =  matchInputOutSequences(aTraceableList);
		System.out.println("Intra Sequence Check: Echoer:" + echoTestSucceeded);
		return echoTestSucceeded;
	}
	public  boolean matchInputOutSequences(List<Traceable> aTraceableList) {
		Integer aStartIndex = 0;
		boolean retVal = true;
		while (true) {
			Integer anInputIndex = indexOf(aTraceableList, ConsoleInput.class, aStartIndex, new ArrayList());
			aStartIndex = anInputIndex + 2; // at one input and output occured after this

			if (anInputIndex < 0) 
//				return true; 
				return retVal; 
			ConsoleInput aConsoleInput = (ConsoleInput) aTraceableList.get(anInputIndex);
			// ignore history and quit commands
			if (!EchoUtilities.isInput(aConsoleInput.getInput())) {
				continue;
			}
			// start getting trace of comparison result traces
			TraceableLog traceableLog = TraceUtility.startNewTrace();
			boolean checkInputEcho = checkInputEcho(aTraceableList, aConsoleInput, anInputIndex);
			TraceUtility.stopExistingTrace(traceableLog);
			// stop getting trace of comparisons
			// does not seem we do anyting with traceable log
			if (!checkInputEcho ) {
				Tracer.info("Did not find  echo for input:" + aConsoleInput.getInput());
				 retVal = false;
//				return false;
			}
//			Tracer.info("Fouund echo for input:" + aConsoleInput.getInput());

//			aStartIndex = anInputIndex + 2; // at one input and output occured after this
		}
			
		
	}
	protected String echo (String anInput) {
		return EchoUtilities.echo(anInput);
	}
	public boolean checkInputEcho(List<Traceable> aTraceableList, ConsoleInput anInput, int anInputIndex ) {
//		Class[] anExpectedClasses = {
//				ConsoleOutput.class,
//		};
		Map<String, Object> propertyToValue = new HashMap();
//		propertyToValue.put(ConsoleOutput.OUTPUT, EchoUtilities.echo(anInput.getInput()));
		propertyToValue.put(ConsoleOutput.OUTPUT, echo(anInput.getInput()));

		return inOrder(aTraceableList, expectedClasses, propertyToValue, anInputIndex + 1, true);
		
	}
	public  boolean compareInputOutSequences(
			List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
//		Class[] anExpectedClasses = {ConsoleOutput.class};
//		List<Traceable> aFilteredTest = TraceUtility.filterTraceList(aTestTraceableList, anExpectedClasses);		
//		List<Traceable> aFilteredCorrect = TraceUtility.filterTraceList(aCorrectTraceableList, anExpectedClasses);
//		
		TraceableLog traceableLog = TraceUtility.startNewTrace();
//		String[] aProperties = {"Output"};
		
//		ObjectQuery[] objectQueries = traceablesToQueries(aCorrectTraceableList);
//		boolean retVal = inOrder(aFilteredTest, objectQueries, anExpe);
		boolean retVal = inOrder(aTestTraceableList, aCorrectTraceableList, expectedClasses, true);
		TraceUtility.stopExistingTrace(traceableLog);
		return retVal;		
	}
	@Override
	public Boolean test() {
//		echoTestSucceeded = EchoTraceChecker.checkInputOutSequences(getGlobalTraceableList());
//		return echoTestSucceeded;
		return intraSequenceTest(getGlobalTraceableList());
	}
	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
//		echoTranscriptTestSucceeded = EchoTraceChecker.compareInputOutSequences(aTestTraceableList, aCorrectTraceableList);
		echoTranscriptTestSucceeded = compareInputOutSequences(aTestTraceableList, aCorrectTraceableList);

		System.out.println("Inter sequence echo:" + echoTranscriptTestSucceeded);
		return echoTranscriptTestSucceeded;
				
	}
	@Override
	public Boolean testAgainstCorrectTranscripts() {
		return testAgainstCorrectTranscripts(getGlobalTraceableList(), getCorrectGlobalTraceableList());

	}

}
