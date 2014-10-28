package lock.letao;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import trace.sharedWindow.AWTEventSent;
import trace.sharedWindow.ReceivedAWTEventDispatched;
import util.awt.ASerializableAWTEvent;
import util.awt.AWTEventQueueHandler;
import util.awt.AnExtendibleAWTEventQueue;
import util.awt.SerializableAWTEvent;
import util.session.Communicator;
import util.session.PeerMessageListener;

public class ListeningInputDistributer implements AWTEventQueueHandler,
		PeerMessageListener {

	AnExtendibleAWTEventQueue anExtendibleAWTEventQueue;
	Communicator communicator;
	List<Component> componentList;

	public ListeningInputDistributer(
			AnExtendibleAWTEventQueue anExtendibleAWTEventQueue,
			Communicator communicator) {
		this.anExtendibleAWTEventQueue = anExtendibleAWTEventQueue;
		componentList = new ArrayList<Component>();
		this.communicator = communicator;
	}

	@Override
	public void newEvent(AWTEvent anEvent) {
		if (anEvent == null)
			return;
		AWTEventSent.newCase(anEvent, getID((Component) anEvent.getSource()),
				"SOURCE", this);
		SerializableAWTEvent aSerializableAWTEvent = new ASerializableAWTEvent(
				anEvent, getID((Component) anEvent.getSource()));
		communicator.toOthers(aSerializableAWTEvent);
	}

	@Override
	public void objectReceived(Object message, String userName) {
		if (message instanceof LockValue) {
			return;
		}
		SerializableAWTEvent aSerializableAWTEvent = (SerializableAWTEvent) message;
		if (aSerializableAWTEvent.getSource() == null) {
			return;
		}
		AWTEvent anEvent = anExtendibleAWTEventQueue
				.getCommunicatedEventSupport().toDispatchedEvent(
						aSerializableAWTEvent,
						getComponent(Integer.parseInt((aSerializableAWTEvent
								.getSource()))));
		if (anEvent == null)
			return;
		ReceivedAWTEventDispatched.newCase(anEvent,
				aSerializableAWTEvent.getSource(), "DESTINATION", this);
		anExtendibleAWTEventQueue.dispatchReceivedEvent(anEvent);
	}

	public void compose() {
		addCollaborationFunctions();
	}

	protected void addCollaborationFunctions() {
		communicator.addPeerMessageListener(this);
	}

	protected void doJoin() {
		communicator.join();
	}

	public Communicator getCommunicator() {
		return communicator;
	}

	public void registerTree(UI c) {
		addComponent(c);
		addComponent(c.getController());
	}

	public void addComponent(Component c) {
		componentList.add(c);
		if (!(c instanceof Container)) {
			return;
		}
		for (Component sc : ((Container) c).getComponents()) {
			addComponent(sc);
		}
	}

	public Component getComponent(int id) {
		return componentList.get(id);
	}

	public String getID(Component c) {
		for (int i = 0; i < componentList.size(); ++i) {
			if (componentList.get(i) == c) {
				return i + "";
			}
		}
		return null;
	}
}
