package ft.letao;

import im.ListEdit;

public interface FTManager {
  SentRequest warpSentRequest(ListEdit<String> listEdit);

  ListEdit<String> upwarpSentRequest(SentRequest aSentRequest);

  MessageWithSeqNum wrapMessage(ListEdit<String> listEdit);

  ListEdit<String> unwarp(MessageWithSeqNum aMessageWithSeqNum);

  void requestSending(ListEdit<String> listEdit, String variant);

  void recover(String aClientName);

  void reElect();
}

