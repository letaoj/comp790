package Assignment2;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import echo.modular.ListObserver;

@SuppressWarnings("serial")
public class UI extends JFrame {

	class Listeners extends Thread implements ActionListener, DocumentListener,
			PropertyChangeListener {
		private long prev;
		private boolean flag = true;

		public Listeners() {
			start();
		}

		@Override
		public void run() {
			synchronized (this) {
				while (true) {
					try {
						this.wait(3000);
						if (flag) {

						} else {
							long diff = System.currentTimeMillis() - prev;
							if (diff < 3000) {
								// historyInteractor.setValue("status",
								// clientName
								// + " is typing..");
							} else {
								// historyInteractor.setValue("status",
								// clientName
								// + " has typed.");
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			synchronized (this) {
				prev = System.currentTimeMillis();
				flag = false;
				this.notify();
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (e.getDocument().getLength() == 0) {
				
				flag = true;
			}
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			String text = "";
			if (obj.equals(awareMessage)) {
				text = awareMessage.getText();
				awareMessage.setText("");
				historyInteractor.processHistory(text);
				history.setCaretPosition(history.getDocument().getLength());
			} else if (obj.equals(message)) {
				text = message.getText();
				message.selectAll();
				historyInteractor.processHistory(text);
				history.setCaretPosition(history.getDocument().getLength());
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			String property = evt.getPropertyName();
			if ("history".equals(property)) {
				history.setText("");
				for (String s : (ArrayList<String>) (evt.getNewValue())) {
					history.append(s + "\n");
				}
			} else if ("status".equals(property)) {
				status.setText((String) evt.getNewValue());
			} else if ("topic".equals(property)) {
				StringBuilder sb = new StringBuilder();
				for (Character c : (ArrayList<Character>) evt.getNewValue()) {
					sb.append(c);
				}
				if (!topic.getText().equals(sb.toString())) {
					topic.setText(sb.toString());
				}
			}
		}
	}

	protected AnIMInteractor historyInteractor;
	protected AnEditorInteractor topicInteractor;
	protected JPanel p1;
	protected JPanel p2;
	protected JPanel p3;
	protected JPanel p4;
	protected JTextField topic;
	protected JTextField status;
	protected JTextArea history;
	protected JTextField awareMessage;
	protected Listeners listeners;
	protected TextField message;

	public UI(ListObserver historyInteractor, ListObserver topicInteractor) {
		listeners = new Listeners();
		this.historyInteractor = (AnIMInteractor) historyInteractor;
		this.topicInteractor = (AnEditorInteractor) topicInteractor;
		initUI();
	}

	public void changeHistory(String aHistory) {
		history.append(aHistory + "\n");
	}

	public void changeStatus(String aStatus) {
		status.setText(aStatus);
	}

	public void addTopic(int index, Character input) {
		try {
			topic.getDocument().insertString(index, "" + input, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void removeTopic(int index) {
		try {
			topic.getDocument().remove(index, 1);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initUI() {
		setTitle("IM Tool");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;

		// Setup Topic panel
		p1 = new JPanel(new GridBagLayout());
		p1.setBorder(BorderFactory.createTitledBorder("Topic"));
		topic = new JTextField();
		topic.addActionListener(listeners);
		topic.addPropertyChangeListener(listeners);
		topic.setEditable(true);
		topic.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					String text = e.getDocument().getText(e.getOffset(),
							e.getLength());
					topicInteractor.processAddTopic(e.getOffset(), text);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					String text = e.getDocument().getText(e.getOffset(),
							e.getLength());
					topicInteractor.processRemoveTopic(e.getOffset(), text);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {

			}

		});
		p1.add(topic, c);

		// Add Status panel
		p2 = new JPanel(new GridBagLayout());
		p2.setBorder(BorderFactory.createTitledBorder("Status"));
		status = new JTextField();
		status.addActionListener(listeners);
		status.addPropertyChangeListener(listeners);
		status.setEditable(false);
		status.setBackground(Color.LIGHT_GRAY);
		p2.add(status, c);

		// Setup History Text Area
		history = new JTextArea(10, 20);
		history.setEditable(false);
		history.addPropertyChangeListener(listeners);
		JScrollPane scrollPane = new JScrollPane(history);

		// Add Aware Message panel
		p3 = new JPanel(new GridBagLayout());
		p3.setBorder(BorderFactory.createTitledBorder("Aware Message"));
		awareMessage = new JTextField();
		awareMessage.addActionListener(listeners);
		awareMessage.getDocument().addDocumentListener(listeners);
		awareMessage.setEditable(true);
		p3.add(awareMessage, c);

		// Setup Message panel
		p4 = new JPanel(new GridBagLayout());
		p4.setBorder(BorderFactory.createTitledBorder("Message"));
		message = new TextField();
		message.addActionListener(listeners);
		message.setEditable(true);
		p4.add(message, c);

		// Add contents to the window.
		c.fill = GridBagConstraints.HORIZONTAL;
		add(p1, c);
		add(p2, c);
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		add(scrollPane, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0.0;
		add(p3, c);
		add(p4, c);

		// Display the window.
		pack();
		setVisible(true);
	}
}
