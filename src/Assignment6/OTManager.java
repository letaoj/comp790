package Assignment6;

import java.util.ArrayList;
import java.util.List;

import trace.ot.InitialOTTimeStampCreated;
import trace.ot.LocalEditCountIncremented;
import trace.ot.TransformationResult;
import util.Misc;


public class OTManager {
  public boolean dynamicOT;
  protected String userName;
  protected boolean isServer;
  protected OTTimeStamp otTimeStamp;
  protected List<OTMessage> buffer;

  public OTManager(String userName, boolean isServer) {
    dynamicOT = false;
    this.userName = userName;
    this.isServer = isServer;
    otTimeStamp = new OTTimeStamp(0, 0);
    InitialOTTimeStampCreated.newCase(userName, userName, 0, 0, isServer, this);
    buffer = new ArrayList<OTMessage>();
  }

  public boolean isDynamicOT() {
    return dynamicOT;
  }

  public void setdynamicOT(boolean dynamicOT) {
    this.dynamicOT = dynamicOT;
  }

  public OTTimeStamp getTimeStamp() {
    return otTimeStamp;
  }

  public void addToBuffer(OTMessage msg) {
    buffer.add(msg);
  }

  public List<OTMessage> getBuffer() {
    return buffer;
  }

  public String getUserName() {
    return userName;
  }

  public boolean isServer() {
    return isServer;
  }

  public ListEdit transform(ListEdit remoteOp, OTTimeStamp remoteTimeStamp) {
    ListEdit trans = (ListEdit) Misc.deepCopy(remoteOp);
    List<OTMessage> remove = new ArrayList<OTMessage>();
    for (OTMessage otm : buffer) {
      OTTimeStamp localTimeStamp = otm.getOTTimeStamp();
      if (!OTTimeStamp.isConcurrent(localTimeStamp, remoteTimeStamp)) {
        remove.add(otm);
      }
    }
    buffer.removeAll(remove);
    System.out.println("buffer size" + buffer.size());
    for (OTMessage otm : buffer) {
      OTTimeStamp localTimeStamp = otm.getOTTimeStamp();
      ListEdit localOp = (ListEdit) otm.getMessage();
      if (OTTimeStamp.isConcurrent(remoteTimeStamp, localTimeStamp)) {
        trans = transformInsertInsert(remoteOp, localOp);
        TransformationResult.newCase(trans.getList(), trans.getOperationName(), trans.getIndex(),
            trans.getElement(), remoteTimeStamp.L, remoteTimeStamp.R, userName, isServer, this);
        localOp = transformInsertInsert(localOp, remoteOp);
        TransformationResult.newCase(localOp.getList(), localOp.getOperationName(),
            localOp.getIndex(), localOp.getElement(), localTimeStamp.L, localTimeStamp.R, userName,
            isServer, this);
        OTTimeStamp.incRemote(localTimeStamp);
        LocalEditCountIncremented.newCase(localOp.getList(), localOp.getOperationName(),
            localOp.getIndex(), localOp.getElement(), localTimeStamp.L, localTimeStamp.R, userName,
            this);
        remoteOp = trans;
      }
    }
    return trans;
  }

  public ListEdit transformInsertInsert(ListEdit remote, ListEdit local) {
    ListEdit remoteTran = (ListEdit) Misc.deepCopy(remote);
    if ((remote.getIndex() > local.getIndex())
        || (remote.getIndex() == local.getIndex() && isServer())) {
      remoteTran.setIndex(remote.getIndex() + 1);
    }
    return remoteTran;
  }

}
