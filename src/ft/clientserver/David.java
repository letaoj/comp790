package ft.clientserver;

import trace.causal.CausalTracerSetter;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.DistributedTags;
import util.trace.Tracer;
import ft.AGUIClientComposerAndLauncher;
import ft.ExampleGUISession;

@Tags({DistributedTags.CLIENT_4, ApplicationTags.IM, ApplicationTags.EDITOR})
public class David implements ExampleGUISession {
  public static final String USER_NAME = DistributedTags.CLIENT_4;

  public static void main(String[] args) {
    String[] launcherArgs =
        {SESSION_SERVER_HOST, SESSION_NAME, USER_NAME, APPLICATION_NAME, Communicator.DIRECT};

    Tracer.showInfo(true);
    CausalTracerSetter.traceCausal();

    (new AGUIClientComposerAndLauncher()).composeAndLaunch(launcherArgs);
  }

}
