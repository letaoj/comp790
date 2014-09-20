package Assignment2;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class MyBean {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private ArrayList<String> history = new ArrayList<String>();
	private ArrayList<Character> topic = new ArrayList<Character>();

	public List<String> getValue() {
		return history;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
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
