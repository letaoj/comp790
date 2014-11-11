package Assignment5;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import trace.causal.VectorTimeStampCreated;
import util.Misc;

public class VectorTimeStamp implements Serializable {

	private Map<String, Integer> timeStamp;

	public VectorTimeStamp() {
		timeStamp = new HashMap<String, Integer>();
		VectorTimeStampCreated.newCase(timeStamp, this);
	}

	public void addPeer(String peerName) {
		timeStamp.put(peerName, 0);
	}

	public void removePeer(String peerName) {
		timeStamp.remove(peerName);
	}

	public void inc(String name) {
		timeStamp.put(name, timeStamp.get(name) + 1);
	}

	public Map<String, Integer> getTimeStamp() {
		return timeStamp;
	}

	public boolean successor(VectorTimeStamp vts) throws Exception {
		int sum = 0;
		Map<String, Integer> timeStamp = vts.getTimeStamp();
		for (String key : this.timeStamp.keySet()) {
			int diff = this.timeStamp.get(key) - timeStamp.get(key);
			if (diff < 0) {
				throw new Exception("Concurrent message detected!");
			}
			sum += diff;
		}

		return sum == 1;
	}

	public VectorTimeStamp deepCopy(VectorTimeStamp orig) {
		return (VectorTimeStamp) Misc.deepCopy(orig);
	}
}
