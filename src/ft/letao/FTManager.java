package ft.letao;

import im.ListEdit;

import java.util.Collection;

import util.session.PeerMessageListener;
import util.session.SessionMessageListener;

public interface FTManager extends SessionMessageListener, PeerMessageListener {
  SentRequest warpSentRequest(ListEdit<String> listEdit);

  ListEdit<String> unwrapSentRequest(SentRequest aSentRequest);

  MessageWithObj wrapMessage(ListEdit<String> listEdit, String variant);

  void recover(String aClientName);

  void electSequencer(String aClientName, boolean isNewSession, Collection<String> allUsers);

  void reElect();
}
