package im;

import util.session.Communicator;
import util.session.PeerMessageListener;
import echo.modular.EchoerComposerAndLauncher;

public interface IMClientComposerAndLauncher extends EchoerComposerAndLauncher,  CommunicatorBasedComposerAndLauncher {
//	public static final String DIRECT = "P2P";
//	public static final String RELAYED = "Relayed";
//	public String getApplicationName();
	public PeerMessageListener getRemoteInputEchoer();
//	public Communicator getCommunicator();

}
