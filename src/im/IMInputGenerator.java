package im;

import bus.uigen.pipe.Demoer;

public interface IMInputGenerator extends Demoer, IMFinalOutputs {

	boolean allJoined();
	public int getNumCausalPhasesFinished() ;



	public boolean isAliceReceivedCathyCausalInput() ;



	



	public boolean isBobReceivedCathyCausalInput() ;






	public boolean isAliceReceivedBobCausalInput();






	public boolean isBobReceivedAliceCausalInput() ;



	


	public boolean isCathyReceivedAliceCausalInput() ;





	public boolean isCathyReceivedBobCausalInput() ;
	
	public boolean isAliceJoined();



	public boolean isBobJoined();



	public boolean isCathyInputOver() ;



	public boolean isAliceFinalOutputOver() ;


	public boolean isCathyReceivedCathyCausalOutput();



	public boolean isJoinPhaseTerminated() ;


	public boolean isCausalPhaseTerminated() ;



	public boolean isCausalPhasesTerminated() ;


	public boolean isViewOutputPhaseStarted() ;


	public String getFinalOutput() ;
	int getNumClientsJoined();
	



}
