package ft.letao;

import im.ListEdit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import trace.echo.modular.OperationName;
import trace.ft.HistoryRecovered;
import trace.ft.SentRequestSent;
import trace.ft.SequencerElected;
import trace.ft.SequencerReElected;
import util.Misc;
import util.session.Communicator;
import ft.FTGUI;

public class AFTManager implements FTManager {
  int localSeqNum;
  int globalSeqNum;
  boolean ft;
  static String sequencer;
  String sequencerCandidate;
  Queue<String> clients;
  String ftType;
  FTGUI gui;
  int count = 0;
  List<ListEdit> history;
  Communicator communicator;
  HashSet<ListEdit> hs;

  public AFTManager(Communicator communicator) {
    globalSeqNum = 0;
    ft = false;
    sequencer = "";
    sequencerCandidate = "";
    clients = new PriorityQueue<String>();
    history = new ArrayList<ListEdit>();
    ftType = "Unicast-Broadcast";
    hs = new HashSet<ListEdit>();
    this.communicator = communicator;
  }

  @Override
  public void clientJoined(String aClientName, String anApplicationName, String aSessionName,
      boolean isNewSession, boolean isNewApplication, Collection<String> allUsers) {
    electSequencer(aClientName, isNewSession, allUsers);
  }

  public void electSequencer(String aClientName, boolean isNewSession, Collection<String> allUsers) {
    gui.setAlgorithmStatus("Client " + aClientName + " Joined");
    clients.add(aClientName);
    if (sequencer.equals("") && isNewSession) {
      sequencer = aClientName;
      SequencerElected.newcase(sequencer, aClientName, this);
      gui.setAlgorithmStatus("Sequencer Elected!");
    } else if (!isNewSession) {
      sequencer = clients.peek();
    }
    if (allUsers.size() > 1 || sequencerCandidate.equals("") && !isNewSession) {
      Queue<String> tmp = (Queue<String>) Misc.deepCopy(clients);
      tmp.poll();
      sequencerCandidate = tmp.peek();
    }
    if (gui != null)
      gui.setSequencer(sequencer);
  }

  @Override
  public void clientLeft(String aClientName, String anApplicationName) {
    clients.remove(aClientName); // remove the client from the client list
    gui.setAlgorithmStatus("Client " + aClientName + " Left");
    // If the sequencer crash or left, re-elect a new sequencer
    if (aClientName.equals(sequencer))
      reElect();
  }

  public void reElect() {
    // TODO find a more elegant way to elect the sequencer
    gui.setAlgorithmStatus("Sequencer Re-electing!");
    sequencer = sequencerCandidate;
    for (String s : clients) {
      if (!s.equals(sequencerCandidate)) {
        sequencerCandidate = s;
        globalSeqNum = localSeqNum;
      }
    }
    gui.setSequencer(sequencer);
    gui.setAlgorithmStatus("Sequencer Re-elected!");
    SequencerReElected.newcase(sequencer, sequencer, this);
    if (isFT()) {
      gui.refresh();
    }
  }

  public void setAlgorithmStatus(String s) {
    gui.setAlgorithmStatus(s);
  }

  public static void requestSending(Communicator communicator, ASentRequest sendRequest) {
    ListEdit listEdit = sendRequest.getListEdit();
    switch (sendRequest.getFTType()) {
      case FTType.UB:
        communicator.toClient(getSequencer(), sendRequest);
        SentRequestSent.newCase(communicator.getClientName(), OperationName.ADD,
            listEdit.getIndex(), listEdit.getElement(), listEdit.getList(), getSequencer(),
            AFTManager.class);
        break;
      case FTType.BB:
        communicator.toAll(sendRequest);
        SentRequestSent
            .newCase(communicator.getClientName(), OperationName.ADD, listEdit.getIndex(),
                listEdit.getElement(), listEdit.getList(), "ALL", AFTManager.class);
        break;
      case FTType.UUB:
        communicator.toClient(getSequencer(), sendRequest);
        SentRequestSent.newCase(communicator.getClientName(), OperationName.ADD,
            listEdit.getIndex(), listEdit.getElement(), listEdit.getList(), getSequencer(),
            AFTManager.class);
        break;
      default:
        break;
    }
  }

  public void recover(String aClientName) {
    if (communicator.getClientName().equals(getSequencer())) {
      for (ListEdit e : getHistory()) {
        communicator.toClient(aClientName, e);
      }
    }
    HistoryRecovered.newcase(aClientName, aClientName, this);
  }

  public int getLocalSeqNum() {
    return localSeqNum;
  }

  public void incGlobal() {
    globalSeqNum++;
  }

  public void incLocal() {
    localSeqNum++;
  }

  public boolean isFT() {
    return ft;
  }

  public void setFTType(String ftType) {
    this.ftType = ftType;
  }

  public String getFTType() {
    return ftType;
  }

  public void setDynamicFT(boolean ft) {
    this.ft = ft;
  }

  public static String getSequencer() {
    return sequencer;
  }

  public void setUI(FTGUI gui) {
    this.gui = gui;
  }

  public void addToHistory(ListEdit listEdit) {
    System.out.println("ListEdit" + listEdit);
    history.add(listEdit);
  }

  public List<ListEdit> getHistory() {
    return history;
  }

  public int getGlobalSeqNum() {
    return globalSeqNum;
  }

  @Override
  public void objectReceived(Object message, String userName) {
    if (message instanceof RecoverHistory) {
      recover(((RecoverHistory) message).getClientName());
    }
  }

  @Override
  public SentRequest warpSentRequest(ListEdit listEdit) {
    gui.setAlgorithmStatus("SentRequest Sent!");
    return new ASentRequest(communicator.getClientName(), listEdit, ftType);
  }

  @Override
  public ListEdit unwrapSentRequest(SentRequest aSentRequest) {
    gui.setAlgorithmStatus("SentRequest Received!");
    return aSentRequest.getListEdit();
  }

  @Override
  public MessageWithObj wrapMessage(ListEdit listEdit, String variant) {
    incLocal();
    if (variant.equals(FTType.UB)) {
      return new AMessageWithSeqNum(localSeqNum, listEdit);
    } else if (variant.equals(FTType.BB)) {
      return new AMessageWithHashCode(localSeqNum, listEdit.toString().hashCode(), listEdit);
    } else if (variant.equals(FTType.UUB)) {
      return new AMessageWithSeqNumFromSequencer(localSeqNum, listEdit);
    }
    return null;
  }

  public void bufferMessage(ListEdit msg) {
    hs.add(msg);
  }

  public void retriveMessage(ListEdit msg) {
    hs.remove(msg);
  }

}
