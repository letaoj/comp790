package im.aware;

import im.IMClientComposerAndLauncher;
import util.session.SessionMessageListener;

public interface AwareIMComposerAndLauncher extends IMClientComposerAndLauncher {
	public SessionMessageListener getSessionAwarenesManager();

}
