package Assignment6;

import java.io.Serializable;

import util.Misc;

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

  public static void incLocal(OTTimeStamp t) {
    ++t.L;
  }

  public static void incRemote(OTTimeStamp t) {
    ++t.R;
  }

  public static OTTimeStamp deepCopy(OTTimeStamp orig) {
    return (OTTimeStamp) Misc.deepCopy(orig);
  }

  public static boolean isConcurrent(OTTimeStamp a, OTTimeStamp b) {
    return (a.L != b.R) && (a.R != b.L);
  }

  public static OTTimeStamp filp(OTTimeStamp orig) {
    return new OTTimeStamp(orig.R, orig.L);
  }
}
