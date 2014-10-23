package Assignment3;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import util.awt.ASerializableAWTEvent;
import util.awt.AWTEventQueueHandler;
import util.awt.AnExtendibleAWTEventQueue;
import util.awt.SerializableAWTEvent;
import util.session.Communicator;
import util.session.CommunicatorSelector;
import util.session.PeerMessageListener;

public class ListeningInputDistributer implements AWTEventQueueHandler,
		PeerMessageListener {

	AnExtendibleAWTEventQueue anExtendibleAWTEventQueue;
	Communicator communicator;
	List<Component> componentList;

	public ListeningInputDistributer(
			AnExtendibleAWTEventQueue anExtendibleAWTEventQueue) {
		this.anExtendibleAWTEventQueue = anExtendibleAWTEventQueue;
		componentList = new ArrayList<Component>();
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
		SerializableAWTEvent aSerializableAWTEvent = (SerializableAWTEvent) message;
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

	public void compose(String[] args) {
		communicator = createCommunicator(args);
		addCollaborationFunctions();
		doJoin();
	}

	protected void addCollaborationFunctions() {
		communicator.addPeerMessageListener(this);
	}

	protected void doJoin() {
		communicator.join();
	}

	public void checkArgs(String[] args) {
		if (args.length < 5) {
			System.out
					.println("Please supply server host name, session name,  user name and application name as main arguments");
			System.exit(-1);
		}
	}

	public Communicator createCommunicator(String args[]) {
		checkArgs(args);
		if (args.length == 5) {
			if (args[4].equalsIgnoreCase(Communicator.DIRECT))
				CommunicatorSelector.selectDirectCommunicator();
			else if (args[4].equalsIgnoreCase(Communicator.RELAYED))
				CommunicatorSelector.selectRelayerCommunicator();
		}
		return CommunicatorSelector.getCommunicator(args[0], args[1], args[2],
				args[3]);
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
