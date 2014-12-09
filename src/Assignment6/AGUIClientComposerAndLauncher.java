package Assignment6;

import java.util.HashMap;
import java.util.Map;

import util.session.Communicator;
import util.session.MessageFilterCreator;
import util.session.PeerMessageListener;
import util.session.ReceivedMessage;
import util.session.ReceivedMessageFilterSelector;
import util.session.SentMessage;
import util.session.SentMessageFilterSelector;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

public class AGUIClientComposerAndLauncher {
  protected AGUIClientComposer aGUIClientComposer;
  protected Communicator communicator;
  protected ListObserver historyInteractor;
  protected ListObserver topicInteractor;
  protected UI ui;
  protected AnOTGUI anOTGUI;
  protected Map<String, OTManager> otManagers;
  protected OTTimeStamp vectorTimeStamp;
  protected SimpleList<Character> topic;
  protected SimpleList<String> history;
  protected PeerMessageListener historyInCoupler;
  protected PeerMessageListener topicInCoupler;

  public void composeAndLaunch(String[] args) {
    // create the list and ot manager
    history = new AReplicatedSimpleList<String>("IM");
    topic = new AReplicatedSimpleList<Character>("EDITOR");
    otManagers = new HashMap<String, OTManager>();
    otManagers.put("IM", new OTManager(args[2], false));
    otManagers.put("EDITOR", new OTManager(args[2], false));

    // communicator initilization
    MessageFilterCreator<ReceivedMessage> receivedMessageCreator =
        new MyReceiveFilterCreator(otManagers);
    MessageFilterCreator<SentMessage> sentMessageCreator =
        new MySendFilterCreator(otManagers);
    ReceivedMessageFilterSelector.setMessageFilterCreator(receivedMessageCreator);
    SentMessageFilterSelector.setMessageFilterCreator(sentMessageCreator);
    createCommunicator(args);

    // list set communicator
    ((AReplicatedSimpleList) history).setCommunicator(this.communicator);
    ((AReplicatedSimpleList) topic).setCommunicator(this.communicator);

    // init the interactor
    historyInteractor = new AnIMInteractor(history, communicator);
    topicInteractor = new AnEditorInteractor(topic);
    history.addObserver(historyInteractor);
    topic.addObserver(topicInteractor);
    historyInCoupler = new AListInCoupler("IM", history);
    topicInCoupler = new AListInCoupler("EDITOR", topic);
    communicator.addPeerMessageListener(historyInCoupler);
    communicator.addPeerMessageListener(topicInCoupler);

    communicator.join();
    launch();
  }

  public void launch() {
    launchUI();
  }

  private void createCommunicator(String[] args) {
    aGUIClientComposer = new AGUIClientComposer();
    aGUIClientComposer.compose(args);
    communicator = aGUIClientComposer.getCommunicator();
  }

  private void launchUI() {
    ui = new UI(historyInteractor, topicInteractor);
    anOTGUI = new AnOTGUI(communicator, otManagers);
    ((AnIMInteractor) historyInteractor).setUI(ui);
    ((AnEditorInteractor) topicInteractor).setUI(ui);
  }

}
