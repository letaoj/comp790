package ot.letao;

import java.io.Serializable;

import util.misc.Misc;

public class OTTimeStamp implements Serializable {
  protected int L;
  protected int R;

  public OTTimeStamp(int L, int R) {
    this.L = L;
    this.R = R;
  }

  public int getL() {
    return L;
  }

  public int getR() {
    return R;
  }

  public static OTTimeStamp deepCopy(OTTimeStamp orig) {
    return (OTTimeStamp) Misc.deepCopy(orig);
  }

  public static boolean isConcurrent(OTTimeStamp a, OTTimeStamp b) {
    return !(a.L == b.L || a.R == b.R);
  }
}
