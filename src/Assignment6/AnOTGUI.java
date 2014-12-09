package Assignment6;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.session.Communicator;

public class AnOTGUI extends JFrame {

  private static final String DELAY_DISPLAY = "Minimum Delay To Server: ";
  private static final String JITTER_DISPLAY = "Delay Variation:        ";

  protected JPanel p2;
  protected JPanel p3;
  protected JPanel p4;
  protected JPanel p5;

  protected JLabel l2;
  protected JLabel l3;
  protected JLabel l4;
  protected JTextField minDelay;
  protected JTextField jitter;
  protected JButton noJitter;
  protected JButton noDelay;
  protected JCheckBox cb;

  protected int j = 0;
  protected int d = 0;
  protected boolean jc = true;
  protected boolean dc = true;

  protected Communicator communicator;
  protected Map<String, OTManager> otManagers;

  public AnOTGUI(Communicator communicator, Map<String, OTManager> otManagers) {
    initGUI();
    this.communicator = communicator;
    this.otManagers = otManagers;
  }

  public void initGUI() {
    setTitle("AnOTGUI");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new FlowLayout(FlowLayout.LEFT));

    initComponent();

    p4.setMaximumSize(new Dimension(350, 20));

    p2.add(l2);
    p2.add(minDelay);
    p3.add(l3);
    p3.add(jitter);
    p4.add(noJitter);
    p4.add(noDelay);
    p5.add(cb);
    p5.add(l4);

    add(p2);
    add(p3);
    add(p4);
    add(p5);

    setSize(360, 140);
    setVisible(true);

  }

  private void initComponent() {
    p2 = new JPanel(new GridLayout(1, 2));
    p3 = new JPanel(new GridLayout(1, 2));
    p4 = new JPanel(new FlowLayout());
    p5 = new JPanel();

    p2.setPreferredSize(new Dimension(350, 30));
    p3.setPreferredSize(new Dimension(350, 30));

    l2 = new JLabel(DELAY_DISPLAY);
    l3 = new JLabel(JITTER_DISPLAY);
    l4 = new JLabel("Transformed");

    minDelay = new JTextField("0");
    jitter = new JTextField("0");

    minDelay.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        l2.setText(DELAY_DISPLAY);
        communicator.setMinimumDelayToServer(Integer.parseInt(minDelay.getText()));
      }

    });

    minDelay.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void insertUpdate(DocumentEvent e) {
        l2.setText(DELAY_DISPLAY + "*");
      }

      @Override
      public void removeUpdate(DocumentEvent e) {}

      @Override
      public void changedUpdate(DocumentEvent e) {}

    });

    jitter.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        l3.setText(JITTER_DISPLAY);
        communicator.setDelayVariation(Integer.parseInt(jitter.getText()));
      }

    });

    jitter.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void insertUpdate(DocumentEvent e) {
        l3.setText(JITTER_DISPLAY + "*");
      }

      @Override
      public void removeUpdate(DocumentEvent e) {}

      @Override
      public void changedUpdate(DocumentEvent e) {}

    });

    noJitter = new JButton("No Jitter");
    noDelay = new JButton("No Delay");

    noJitter.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        jitter.setText("0");
        l3.setText(JITTER_DISPLAY);
        communicator.setDelayVariation(0);
      }

    });

    noDelay.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        minDelay.setText("0");
        l2.setText(DELAY_DISPLAY);
        communicator.setMinimumDelayToServer(0);
      }

    });

    cb = new JCheckBox();
    cb.addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(ChangeEvent e) {
        for (OTManager otManager : otManagers.values()) {
          otManager.setdynamicOT(cb.isSelected());
        }
      }

    });
  }

  public static void main(String[] args) {
    new AnOTGUI(null, null);
  }

}
