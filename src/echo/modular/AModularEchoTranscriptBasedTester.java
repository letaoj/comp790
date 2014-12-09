package echo.modular;

import java.util.List;

import trace.echo.modular.ModularEchoTraceChecker;
import util.trace.Traceable;
import echo.monolithic.AMonolithicEchoTranscriptBasedTester;

public class AModularEchoTranscriptBasedTester extends AMonolithicEchoTranscriptBasedTester {

	protected boolean modularityTestSucceeded;
	protected boolean modularityTranscriptTestSucceeded;

	
	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		boolean superTestSucceeded = super.testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList);
		modularityTranscriptTestSucceeded =  ModularEchoTraceChecker.compareMVCSequences(aTestTraceableList, aCorrectTraceableList);
		return superTestSucceeded && modularityTranscriptTestSucceeded;
	}
	
	@Override
	public boolean intraSequenceTest(List<Traceable> aTraceableList) {
		boolean superTestSucceeded = super.intraSequenceTest(aTraceableList);
		modularityTestSucceeded =  ModularEchoTraceChecker.intraSequenceCheckModularEchoer(aTraceableList);
		System.out.println( "Intra Sequence Check, Modularity:" + modularityTestSucceeded );
		return superTestSucceeded &
				modularityTestSucceeded;

	}
	

}
