package im;

import java.util.List;
import java.util.Set;

import trace.im.IMTraceChecker;
import util.trace.Traceable;
import echo.modular.AModularEchoTranscriptBasedTester;

public class AnIMTranscriptBasedTester extends AModularEchoTranscriptBasedTester {
	protected boolean aliceEqualsBobOutput;
	protected boolean aliceEqualsCathyOutput;
	protected boolean bobEqualsCathyOutput;
	protected IMFinalOutputs finalOutputs;
	protected String currentTestedProcess; // ugh, global, but want to reuse code from superclass which means cannot add parameters
	public AnIMTranscriptBasedTester(IMFinalOutputs anEqualHistoryChecker) {
		finalOutputs = anEqualHistoryChecker;
	}
	
	public   boolean intraSequenceTest(List<Traceable> aTraceableList) {
		boolean superTest = super.intraSequenceTest(aTraceableList);
		boolean imTest = IMTraceChecker.intraSequenceCheckIM(aTraceableList);
		return superTest && imTest;
				
	}
	/*
	 * (non-Javadoc)
	 * @see echo.monolithic.AMonolithicEchoTranscriptBasedTester#echo(java.lang.String)
	 * ocerriding the echo echo with the im echo
	 */
	protected String echo (String anInput) {
		return IMUtililties.remoteEcho(anInput, currentTestedProcess);
	}
	protected Boolean intraSequenceTest() {
		Set<String> aProcesses = processToLocalTraceableList.keySet();
		boolean retVal = true;
		for (String aProcess : aProcesses) {
			if (aProcess.equals(processNames.get(0)))
				continue;
			currentTestedProcess = aProcess;
			System.out.println("intra sequence testr for:"
					+ aProcess);
			List<Traceable> aTestTraceableList = processToLocalTraceableList
					.get(aProcess);
			
			retVal = retVal & intraSequenceTest(aTestTraceableList);
//			modularityTranscriptTestSucceeded = super.testAgainstCorrectTranscripts(testTranscript, correctTranscript);
////			if (!modularityTranscriptTestSucceeded)
////				System.out.println("Modularity test failed");
//			
//			if (!IMTraceChecker.compareIMSequences(testTranscript,
//					correctTranscript)) {
//				retVal = false;
//				System.out.println("IM Sequence Test failed");
//
//			}
		}

		return retVal;

	}
	@Override
	protected   Boolean testAgainstCorrectTranscripts(List<Traceable> aTestTraceableList, 
			List<Traceable> aCorrectTraceableList) {
		return super.testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList)
		& IMTraceChecker.compareIMSequences(aTestTraceableList,
				aCorrectTraceableList);
				
	}
	

	@Override
	public Boolean testAgainstCorrectTranscripts() {
		Set<String> titles = processToLocalTraceableList.keySet();
		boolean retVal = true;
		for (String title : titles) {
			if (title.equals(processNames.get(0)))
				continue;
			System.out.println("Comparing test and local transcripts for:"
					+ title);
			List<Traceable> aTestTraceableList = processToLocalTraceableList
					.get(title);
			List<Traceable> aCorrectTraceableList = processToCorrectTraceableList
					.get(title);
			retVal &= testAgainstCorrectTranscripts(aTestTraceableList, aCorrectTraceableList);
//			modularityTranscriptTestSucceeded = super.testAgainstCorrectTranscripts(testTranscript, correctTranscript);
////			if (!modularityTranscriptTestSucceeded)
////				System.out.println("Modularity test failed");
//			
//			if (!IMTraceChecker.compareIMSequences(testTranscript,
//					correctTranscript)) {
//				retVal = false;
//				System.out.println("IM Sequence Test failed");
//
//			}
		}

		return retVal;

	}

	

	@Override
	public Boolean test() {
//		boolean superTestSucceeded = super.test();
		
		aliceEqualsBobOutput = finalOutputs.getAliceFinalOutput().equals(
				finalOutputs.getBobFinalOutput());
		aliceEqualsCathyOutput = finalOutputs.getAliceFinalOutput().equals(
				finalOutputs.getCathyFinalOutput());
		bobEqualsCathyOutput = finalOutputs.getBobFinalOutput().equals(
				finalOutputs.getCathyFinalOutput());
		boolean historiesEqual = finalOutputs.getAliceFinalOutput() != null // null check makes no sense after equals
				&& aliceEqualsBobOutput &&aliceEqualsCathyOutput
				&& bobEqualsCathyOutput;
		System.out.println("Hisstories Equal:" + historiesEqual);

		boolean intraSequenceTest = intraSequenceTest();
		return historiesEqual && intraSequenceTest;

	}

}
