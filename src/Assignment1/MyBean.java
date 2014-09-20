package Assignment1;


import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MyBean {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private SingleUI alice;
	private SingleUI bob;
	private SingleUI cathy;
	
	public MyBean() {
		alice = new SingleUI("Alice", this);
		//bob = new SingleUI("Bob", this);
		//cathy = new SingleUI("Cathy", this);
		pcs.addPropertyChangeListener(alice.listeners);
		//pcs.addPropertyChangeListener(bob.listeners);
		//pcs.addPropertyChangeListener(cathy.listeners);
	}

	private ArrayList<String> history = new ArrayList<String>();
	private ArrayList<Character> topic = new ArrayList<Character>();

	public List<String> getValue() {
		return history;
	}

	public void setValue(String property, String newValue) {
		if ("history".equals(property)) {
			history.add(newValue);
			System.out.println(newValue);
			pcs.firePropertyChange(property, null, history);
		} else if ("status".equals(property)) {
			pcs.firePropertyChange(property, null, newValue);
		} else if ("topic".equals(property)) {
			topic.clear();
			for (Character c : newValue.toCharArray()) {
				topic.add(c);
			}
			pcs.firePropertyChange(property, null, topic);
		}
	}

	public void close() {
		System.exit(1);
	}
}
