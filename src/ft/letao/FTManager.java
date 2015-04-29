package ft.letao;

import im.ListEdit;
import util.session.PeerMessageListener;
import util.session.SessionMessageListener;

public interface FTManager extends SessionMessageListener, PeerMessageListener {
  SentRequest warpSentRequest(ListEdit<String> listEdit);

  ListEdit<String> upwarpSentRequest(SentRequest aSentRequest);

  MessageWithSeqNum wrapMessage(ListEdit<String> listEdit);

  ListEdit<String> unwarp(MessageWithSeqNum aMessageWithSeqNum);

  void recover(String aClientName);

  void reElect();
}
