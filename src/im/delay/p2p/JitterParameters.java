package im.delay.p2p;

import util.annotations.Column;
import util.annotations.Row;
import util.models.PropertyListenerRegistrar;

public interface JitterParameters extends PropertyListenerRegistrar{
//	public int getMinimumDelayToPeer() ;
//	public void setMinimumDelayToPeer(int minimumDelayToPeer) ;
	public int getDelayVariation() ;
	public void setDelayVariation(int variationToPeer) ;
	public void noJitter() ;
//	public void noDelay();
//	String getPeerName();
//	void setPeerName(String peerName);

}
