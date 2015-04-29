package ft;

import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.session.MessageFilterCreator;
import util.session.PeerMessageListener;
import util.session.ReceivedMessage;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessage;
import util.session.SentMessageFilterSelector;
import ft.letao.AFTManager;

public class AGUIClientComposer {

  protected Communicator communicator;
  protected PeerMessageListener listInCoupler;

  public Communicator compose(String[] args) {
    return communicator = createCommunicator(args);
  }

  public void checkArgs(String[] args) {
    if (args.length < 5) {
      System.out
          .println("Please supply server host name, session name,  user name and application name as main arguments");
      System.exit(-1);
    }
  }

  // parameters to factory
  public Communicator createCommunicator(String args[]) {
    // set factories used to create communicator
    // MessageFilterCreator<ReceivedMessage> receivedMessageCreator =
    // new MyReceivedFilterCreator(ftManager);
    // MessageFilterCreator<SentMessage> sentMessageCreator =
    // new MySentFilterCreator(ftManager);
    // ReceivedMessageFilterSelector.setMessageFilterCreator(receivedMessageCreator);
    // SentMessageFilterSelector.setMessageFilterCreator(sentMessageCreator);

    checkArgs(args);
    if (args.length == 5) {
      if (args[4].equalsIgnoreCase(Communicator.DIRECT))
        CommunicatorSelector.selectDirectCommunicator();
      else if (args[4].equalsIgnoreCase(Communicator.RELAYED))
        CommunicatorSelector.selectRelayerCommunicator();
    }
    return CommunicatorSelector.getCommunicator(args[0], args[1], args[2], args[3]);
  }

  public PeerMessageListener getRemoteInputEchoer() {
    return listInCoupler;
  }

  public Communicator getCommunicator() {
    return communicator;
  }

}
