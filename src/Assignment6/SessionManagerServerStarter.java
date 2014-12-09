package Assignment6;

import trace.ot.OTTracerSetter;
import util.annotations.Tags;
import util.session.ASessionManager;
import util.session.ASessionManagerSelector;
import util.session.ServerSentMessageFilterSelector;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.ImplicitKeywordKind;
import util.trace.MessagePrefixKind;
import util.trace.Tracer;
import util.trace.session.ServerClientJoined;

@Tags({DistributedTags.SERVER, DistributedTags.SESSION_MANAGER, ApplicationTags.IM,
    ApplicationTags.EDITOR})
public class SessionManagerServerStarter {
  static ASessionManager server;

  public static void main(String[] args) {
    Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
    Tracer.setMessagePrefixKind(MessagePrefixKind.FULL_CLASS_NAME);
    Tracer.showInfo(true);
    OTTracerSetter.traceOT();
    Tracer.setKeywordPrintStatus(ServerClientJoined.class, true);
    ServerSentMessageFilterSelector.setMessageFilterFactory(new MyServerFilterCreator());
    server = ASessionManagerSelector.getSessionManager();
    server.register();
  }
}
