package Assignment5;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import trace.causal.UserAddedToVectorTimeStamp;
import util.session.SessionMessageListener;

public class CausalityManager implements SessionMessageListener {

	VectorTimeStamp vectorTimeStamp;
	Queue<AMessageWithTimeStamp> buffer;
	boolean dynamicCausality = false;

	class AMessageWithTimeStampComparator implements
			Comparator<AMessageWithTimeStamp> {

		@Override
		public int compare(AMessageWithTimeStamp o1, AMessageWithTimeStamp o2) {
			VectorTimeStamp v1 = o1.getVectorTimeStamp();
			VectorTimeStamp v2 = o2.getVectorTimeStamp();
			boolean lessThan = true;
			if (v1.getTimeStamp().equals(v2.getTimeStamp()))
				return 0;
			for (String key : v1.getTimeStamp().keySet()) {
				if (v1.getTimeStamp().get(key) > v2.getTimeStamp().get(key)) {
					lessThan = false;
				}
			}
			if (lessThan) {
				return -1;
			}
			return 1;
		}

	}

	public CausalityManager() {
		vectorTimeStamp = new VectorTimeStamp();
		buffer = new PriorityQueue<AMessageWithTimeStamp>(
				new AMessageWithTimeStampComparator());
	}

	@Override
	public void clientJoined(String aClientName, String anApplicationName,
			String aSessionName, boolean isNewSession,
			boolean isNewApplication, Collection<String> allUsers) {
		vectorTimeStamp.addPeer(aClientName);
		for (String user : allUsers) {
			vectorTimeStamp.addPeer(user);
		}
		UserAddedToVectorTimeStamp.newCase(vectorTimeStamp.getTimeStamp(), this);
	}

	@Override
	public void clientLeft(String aClientName, String anApplicationName) {
		vectorTimeStamp.removePeer(aClientName);
	}

	public VectorTimeStamp getTimeStamp() {
		return vectorTimeStamp;
	}
	
	public void setTimeStamp(VectorTimeStamp vectorTimeStamp) {
		this.vectorTimeStamp = vectorTimeStamp;
	}

	public void addToBuffer(AMessageWithTimeStamp msg) {
		buffer.add(msg);
	}

	public Queue getBuffer() {
		return buffer;
	}
	
	public boolean isDynamicCaulity() {
		return dynamicCausality;
	}
	
	public void setdynamicCausality(boolean dynamicCausality) {
		this.dynamicCausality = dynamicCausality;
	}

}
