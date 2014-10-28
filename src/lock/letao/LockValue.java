package lock.letao;

import java.io.Serializable;
import java.util.List;

public class LockValue implements Serializable {
	String name;
	String status;
	List<String> history;
	long timestamp;
	boolean updateTime;

	public LockValue(long timestamp) {
		this.timestamp = timestamp;
		updateTime = true;
	}

	public LockValue(String name, String status, long timestamp) {
		this.name = name;
		this.status = status;
		history = null;
		this.timestamp = timestamp;
		updateTime = false;
	}

	public LockValue(String name, String status, long timestamp,
			List<String> history) {
		this.name = name;
		this.status = status;
		this.history = history;
		this.timestamp = timestamp;
		updateTime = false;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public List getHistory() {
		return history;
	}

	public long getTimestamp() {
		return timestamp;
	}
	
	public boolean updateTime() {
		return updateTime;
	}
}
