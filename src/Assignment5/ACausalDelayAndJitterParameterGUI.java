package Assignment5;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.session.Communicator;

public class ACausalDelayAndJitterParameterGUI extends JFrame {

	protected JPanel p1;
	protected JPanel p2;
	protected JPanel p3;
	protected JPanel p4;
	protected JPanel p5;

	protected JLabel l1;
	protected JLabel l2;
	protected JLabel l3;
	protected JLabel l4;
	protected JTextField peerName;
	protected JTextField minDelay;
	protected JTextField jitter;
	protected JButton noJitter;
	protected JButton noDelay;
	protected JCheckBox cb;

	protected JTable history;

	protected int j = 0;
	protected int d = 0;
	protected boolean jc = true;
	protected boolean dc = true;

	protected String[] columnNames = { "Name", "Min Delay" };
	protected Object[][] listData = { { "Alice", new Integer(0) },
			{ "Bob", new Integer(0) }, { "Cathy", new Integer(0) },
			{ "David", new Integer(0) } };;

	protected Communicator communicator;
	protected CausalityManager causalityManager;

	public ACausalDelayAndJitterParameterGUI(Communicator communicator,
			CausalityManager causalityManager) {
		initGUI();
		this.communicator = communicator;
		this.causalityManager = causalityManager;
	}

	public void initGUI() {
		setTitle("ACausalDelayAndJitterParameterGUI");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		initComponent();

		p1.setMaximumSize(new Dimension(350, 20));
		p4.setMaximumSize(new Dimension(350, 20));

		p1.add(l1);
		p1.add(peerName);
		p2.add(l2);
		p2.add(minDelay);
		p3.add(l3);
		p3.add(jitter);
		p4.add(noJitter);
		p4.add(noDelay);
		p5.add(cb);
		p5.add(l4);

		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(history);

		setSize(360, 260);
		setVisible(true);

	}

	private void initComponent() {
		p1 = new JPanel(new GridLayout(1, 2));
		p2 = new JPanel(new GridLayout(1, 2));
		p3 = new JPanel(new GridLayout(1, 2));
		p4 = new JPanel(new FlowLayout());
		p5 = new JPanel();

		p1.setPreferredSize(new Dimension(350, 30));
		p2.setPreferredSize(new Dimension(350, 30));
		p3.setPreferredSize(new Dimension(350, 30));

		l1 = new JLabel("Peer Name: ");
		l2 = new JLabel("Minimum Delay to Peer: ");
		l3 = new JLabel("Delay Variation: ");
		l4 = new JLabel("Causal");

		history = new JTable(listData, columnNames);
		history.setPreferredSize(new Dimension(350, 80));

		j = 0;
		d = 0;

		peerName = new JTextField();
		minDelay = new JTextField(d + "");
		jitter = new JTextField(j + "");

		minDelay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = peerName.getText();
				int delay = Integer.parseInt(minDelay.getText());
				if (delay != 0 && name != "") {
					for (int i = 0; i < listData.length; i++) {
						if (listData[i][0].equals(name)) {
							listData[i][1] = delay;
							history.setValueAt(new Integer(delay), i, 1);
						}
					}
					d = delay;
					setDelay(name);
					dc = false;
				}
			}

		});

		minDelay.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (dc) {
					l2.setText("Minimum Delay to Peer: *");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

		});

		jitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int jit = Integer.parseInt(jitter.getText());
				if (jit != 0) {
					setJitter();
					jc = false;
				}
			}

		});

		jitter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (jc) {
					l3.setText("Delay Variation: *");
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

		});

		noJitter = new JButton("No Jitter");
		noDelay = new JButton("No Delay");

		noJitter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				j = 0;
				setJitter();
				jitter.setText("0");
				jc = true;
			}

		});

		noDelay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				d = 0;
				listData[history.getSelectedRow()][1] = d;
				setDelay((String) listData[history.getSelectedRow()][0]);
				history.setValueAt(new Integer(d), history.getSelectedRow(), 1);
				minDelay.setText("0");
				dc = true;
			}

		});

		cb = new JCheckBox();
		cb.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				causalityManager.setdynamicCausality(cb.isSelected());
			}

		});
	}

	protected void setJitter() {
		l3.setText("Delay Variation: ");
		communicator.setDelayVariation(j);
	}

	protected void setDelay(String name) {
		l2.setText("Minimum Delay to Peer: ");
		communicator.setMinimumDelayToPeer(name, d);
	}

	public static void main(String[] args) {
		new ACausalDelayAndJitterParameterGUI(null, null);
	}

}
