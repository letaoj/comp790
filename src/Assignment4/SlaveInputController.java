package Assignment4;

import java.awt.AWTEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

import trace.locking.SlaveLockGrantReceived;
import trace.locking.SlaveLockGrantRequestMade;
import trace.locking.SlaveLockGrantRequestSent;
import trace.locking.SlaveLockGranted;
import trace.locking.SlaveLockReleaseReceived;
import trace.locking.SlaveLockReleaseRequestMade;
import trace.locking.SlaveLockReleaseRequestSent;
import trace.locking.SlaveLockReleased;
import trace.locking.SlaveMyLockGrantMadeReceived;
import util.annotations.Tags;
import util.session.Communicator;
import util.session.PeerMessageListener;
import util.tags.DistributedTags;
import util.tags.FunctionTags;

@Tags({ FunctionTags.LOCK_CONTROLS, FunctionTags.CONCURRENCY_CONTROLLED })
public class SlaveInputController implements VetoableChangeListener,
		PeerMessageListener {
	String cache;
	long timestamp;
	Communicator communicator;
	AReplicatedLockController aReplicatedLockController;

	public SlaveInputController(Communicator communicator,
			AReplicatedLockController aReplicatedLockController) {
		cache = "";
		this.communicator = communicator;
		this.communicator.addPeerMessageListener(this);
		this.aReplicatedLockController = aReplicatedLockController;
	}

	@Override
	public void vetoableChange(PropertyChangeEvent evt)
			throws PropertyVetoException {
		if (evt.getNewValue() instanceof AWTEvent) {
			Object o = ((AWTEvent) evt.getNewValue()).getSource();
			if (o.equals(aReplicatedLockController)) {
				return;
			}
		}
		String propertyName = evt.getPropertyName();
		if (propertyName.equals(LockStatus.REQUEST)) {
			SlaveLockGrantRequestMade.newCase(this);
			if (cache.equals("")) {
				communicator.toClient(DistributedTags.CLIENT_1, new LockValue(
						communicator.getClientName(), LockStatus.REQUEST,
						System.currentTimeMillis()));
				SlaveLockGrantRequestSent.newCase(this);
			} else {
				throw new PropertyVetoException(cache, evt);
			}
		} else if (propertyName.equals(LockStatus.RELEASE)) {
			SlaveLockReleaseRequestMade.newCase(this);
			if (!cache.equals("") && cache.equals(communicator.getClientName())) {
				cache = "";
				communicator.toClient(DistributedTags.CLIENT_1, new LockValue(
						communicator.getClientName(), LockStatus.RELEASE,
						System.currentTimeMillis()));
				SlaveLockReleaseRequestSent.newCase(this);
			}
		} else {
			if (cache.equals(communicator.getClientName())) {
				communicator.toAll(new LockValue(System.currentTimeMillis()));
			} else if (aReplicatedLockController.implicitLock()) {
				if (cache.equals("")) {
					communicator.toClient(
							DistributedTags.CLIENT_1,
							new LockValue(communicator.getClientName(),
									LockStatus.REQUEST, System
											.currentTimeMillis()));
					SlaveLockGrantRequestSent.newCase(this);
				} else if (System.currentTimeMillis() - timestamp > 3000) {
					communicator.toClient(
							DistributedTags.CLIENT_1,
							new LockValue(cache, LockStatus.RELEASE, System
									.currentTimeMillis()));
					cache = "";
					SlaveLockReleaseRequestSent.newCase(this);
					communicator.toClient(
							DistributedTags.CLIENT_1,
							new LockValue(communicator.getClientName(),
									LockStatus.REQUEST, System
											.currentTimeMillis()));
					SlaveLockGrantRequestSent.newCase(this);
				}
			} else {
				throw new PropertyVetoException(cache, evt);
			}
		}
	}

	@Override
	public void objectReceived(Object message, String userName) {
		if (!(message instanceof LockValue)) {
			return;
		}
		LockValue lv = (LockValue) message;
		if (lv.updateTime()) {
			timestamp = lv.getTimestamp();
		}
		String status = lv.getStatus();
		String name = lv.getName();
		if (status == null)
			return;
		if (status.equals(LockStatus.UPDATE)) {
			if (name.equals("")) {
				SlaveLockReleaseReceived.newCase(this);
				SlaveLockReleased.newCase(cache, this);
			} else {
				SlaveLockGrantReceived.newCase(name, this);
				SlaveLockGranted.newCase(name, this);
				if (name.equals(communicator.getClientName())) {
					SlaveMyLockGrantMadeReceived.newCase(this);
				}
			}
			cache = name;
			timestamp = lv.getTimestamp();
			aReplicatedLockController.updateText(cache);
			aReplicatedLockController.updateLockHistory(lv.getHistory());
		}
	}
}
