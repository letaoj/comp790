package Assignment4;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import trace.sharedWindow.ComponentTreeRegistered;
import util.awt.AnExtendibleTelePointerGlassPane;

@SuppressWarnings("serial")
public class UI extends JFrame {

	private class Listeners extends Thread implements ActionListener,
			DocumentListener {
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
								changeStatus("Someone is typing...");
							} else {
								changeStatus("Someone has typed!");
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
				changeStatus("");
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
			} else if (obj.equals(message)) {
				text = message.getText();
				message.selectAll();
			}
			changeHistory(text);
			history.setCaretPosition(history.getDocument().getLength());
		}
	}

	protected AnExtendibleTelePointerGlassPane glassPane;
	protected AGUIGlassPaneController aGUIGlassPaneController;
	protected JFrame telePointedFrame;
	protected JPanel p1;
	protected JPanel p2;
	protected JPanel p3;
	protected JPanel p4;
	protected JTextField topic;
	protected JTextField status;
	protected JTextArea history;
	protected JTextField awareMessage;
	protected TextField message;
	protected TelepointerUtility tele;
	protected Listeners listeners;
	protected ListeningInputDistributer listeningInputDistributer;
	protected boolean flag = true;

	public UI(ListeningInputDistributer listeningInputDistributer) {
		initUI();
		this.listeningInputDistributer = listeningInputDistributer;
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
			e.printStackTrace();
		}
	}

	public JFrame getController() {
		return aGUIGlassPaneController.getFrame();
	}

	public void initUI() {
		setTitle("Shared Window system");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.weightx = 1.0;
		c.fill = GridBagConstraints.HORIZONTAL;

		listeners = new Listeners();

		// Setup Topic panel
		p1 = new JPanel(new GridBagLayout());
		p1.setBorder(BorderFactory.createTitledBorder("Topic"));
		topic = new JTextField();
		topic.setEditable(true);
		p1.add(topic, c);

		// Add Status panel
		p2 = new JPanel(new GridBagLayout());
		p2.setBorder(BorderFactory.createTitledBorder("Status"));
		status = new JTextField();
		status.setEditable(false);
		status.setBackground(Color.LIGHT_GRAY);
		p2.add(status, c);

		// Setup History Text Area
		history = new JTextArea(10, 20);
		history.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(history);

		// Add Aware Message panel
		p3 = new JPanel(new GridBagLayout());
		p3.setBorder(BorderFactory.createTitledBorder("Aware Message"));
		awareMessage = new JTextField();
		awareMessage.setEditable(true);
		awareMessage.getDocument().addDocumentListener(listeners);
		awareMessage.addActionListener(listeners);
		p3.add(awareMessage, c);

		// Setup Message panel
		p4 = new JPanel(new GridBagLayout());
		p4.setBorder(BorderFactory.createTitledBorder("Message"));
		message = new TextField();
		message.setEditable(true);
		message.addActionListener(listeners);
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

		glassPane = new AnExtendibleTelePointerGlassPane(this);
		aGUIGlassPaneController = new AGUIGlassPaneController(glassPane);
		glassPane.setGlassPaneController(aGUIGlassPaneController);
		tele = new TelepointerUtility(glassPane);
		glassPane.addPainter(tele);
		glassPane.setVisible(true);

		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				if (flag) {
					flag = false;
					ComponentTreeRegistered.newCase(e, "0",
							listeningInputDistributer);
				}
			}
		});

		// Display the window.
		pack();
		setVisible(true);
	}
}
