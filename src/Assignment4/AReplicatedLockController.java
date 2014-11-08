package Assignment4;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AReplicatedLockController extends JFrame {

	protected JPanel p1;
	protected JPanel p2;
	protected JPanel p3;
	protected JPanel p4;
	protected JCheckBox checkbox;
	protected JLabel label;
	protected JTextField textField;
	protected JButton request;
	protected JButton release;
	protected JTextArea history;
	protected boolean implicit;
	VetoableChangeSupport vetoChangeSupport = new VetoableChangeSupport(this);

	public AReplicatedLockController() {
		initController();
	}

	protected void initController() {
		setTitle("AReplicatedLockController");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p4 = new JPanel();
		checkbox = new JCheckBox();
		label = new JLabel("Implicit Lock");
		textField = new JTextField();
		request = new JButton("Request Lock");
		release = new JButton("Release Lock");
		implicit = false;

		request.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vetoChangeSupport.fireVetoableChange("request", null, "");
				} catch (PropertyVetoException exception) {
					return;
				}
			}

		});
		release.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					vetoChangeSupport.fireVetoableChange("release", null, "");
				} catch (PropertyVetoException exception) {
					return;
				}
			}

		});

		checkbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				implicit = !implicit;
			}

		});

		p1.setBorder(BorderFactory.createTitledBorder("Current Lock Holder"));
		textField.setPreferredSize(new Dimension(150, 20));
		textField.setEditable(false);
		textField.setAlignmentX(LEFT_ALIGNMENT);
		p1.add(textField);
		p1.setMaximumSize(new Dimension(160, 50));
		p1.setAlignmentX(LEFT_ALIGNMENT);
		add(p1);

		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.add(request);
		p2.add(release);
		p2.setAlignmentX(LEFT_ALIGNMENT);
		p2.setMaximumSize(new Dimension(300, 40));
		add(p2);

		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.add(checkbox);
		p3.add(label);
		p3.setAlignmentX(LEFT_ALIGNMENT);
		p3.setMaximumSize(new Dimension(300, 40));
		add(p3);

		history = new JTextArea(10, 20);
		history.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(history);
		p4.add(scrollPane);
		p4.setAlignmentX(LEFT_ALIGNMENT);
		p4.setMaximumSize(new Dimension(300, 40));
		add(p4);

		setSize(new Dimension(300, 320));
		setVisible(true);
	}

	protected void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoChangeSupport.addVetoableChangeListener(listener);
	}

	protected void updateText(String name) {
		textField.setText(name);
	}

	public static void main(String[] args) {
		new AReplicatedLockController();
	}

	public boolean implicitLock() {
		return implicit;
	}

	public void updateLockHistory(List<String> his) {
		history.setText("");
		for (String s : his) {
			history.append(s);
		}
		history.setCaretPosition(history.getDocument().getLength());
	}
}
