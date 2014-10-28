package lock.letao;

import trace.sharedWindow.SharedWindowTracerSetter;
import util.annotations.Tags;
import util.session.ASessionManager;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Tracer;
import util.trace.session.ServerClientJoined;

@Tags({ DistributedTags.SERVER, DistributedTags.SESSION_MANAGER,
		ApplicationTags.REPLICATED_WINDOW })
public class SessionManagerServerStarter {
	static ASessionManager server;

	public static void main(String[] args) {
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
		// Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
		Tracer.showInfo(true);
		SharedWindowTracerSetter.traceSharedWindow();
		Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);

		// SessionTracerSetter.setSessionPrintStatus();

		server = new ASessionManager();
		server.register();
	}
}
