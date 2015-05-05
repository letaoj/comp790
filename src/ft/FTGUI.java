package ft;

import im.ListEdit;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.session.Communicator;
import ft.letao.AFTManager;
import ft.letao.ARegistry;
import ft.letao.FTType;
import ft.letao.RecoverHistory;

public class FTGUI extends JFrame {

  protected JPanel p1;
  protected JPanel p2;
  protected JPanel p3;
  protected JPanel p4;
  protected JPanel p5;
  protected JPanel p6;
  protected JPanel p7;
  protected JPanel p8;

  protected JLabel l1;
  protected JLabel l2;
  protected JLabel l3;
  protected JLabel l5;
  protected JLabel l6;
  protected JLabel l7;
  protected JLabel l8;

  protected JTextField peerName;
  protected JTextField minDelay;
  protected JTextField jitter;
  protected JTextField status;
  protected JButton noJitter;
  protected JButton noDelay;
  protected JButton jb;
  protected JCheckBox cb2;
  protected JComboBox<String> jcb;
  protected JTextField sequencer;

  protected int j = 0;
  protected int d = 0;
  protected boolean jc = true;
  protected boolean dc = true;
  public String sequencerName;

  protected Communicator communicator;
  protected AFTManager ftManager;

  public FTGUI(Communicator communicator) {
    initGUI();
    this.communicator = communicator;
    this.ftManager = (AFTManager) ARegistry.getFTManager(communicator);
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        communicator.leave();
        FTGUI.this.dispose();
      }
    });
  }

  public void initGUI() {
    setTitle("AFTDelayAndJitterParameterGUI");
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
    p4.add(jb);
    p5.add(cb2);
    p5.add(l5);
    p5.add(status);
    p7.add(l7);
    p8.add(l8);
    p8.add(status);
    p7.add(sequencer);
    p6.add(l6);
    p6.add(jcb);

    add(p1);
    add(p2);
    add(p3);
    add(p4);
    add(p5);
    add(p8);
    add(p7);
    add(p6);

    setSize(440, 320);
    setVisible(true);

  }

  private void initComponent() {
    p1 = new JPanel(new GridLayout(1, 2));
    p2 = new JPanel(new GridLayout(1, 2));
    p3 = new JPanel(new GridLayout(1, 2));
    p4 = new JPanel(new FlowLayout());
    p5 = new JPanel();
    p7 = new JPanel(new GridLayout(1, 2));
    p6 = new JPanel();
    p8 = new JPanel(new GridLayout(1, 2));

    p1.setPreferredSize(new Dimension(350, 30));
    p2.setPreferredSize(new Dimension(350, 30));
    p3.setPreferredSize(new Dimension(350, 30));
    p7.setPreferredSize(new Dimension(420, 30));
    p8.setPreferredSize(new Dimension(420, 30));

    l1 = new JLabel("Peer Name: ");
    l2 = new JLabel("Minimum Delay to Peer: ");
    l3 = new JLabel("Delay Variation: ");
    l5 = new JLabel("Total Order");
    l7 = new JLabel("Current Sequencer: ");
    l8 = new JLabel("Current Status: ");
    l6 = new JLabel("Current Fix Sequencer Variant: ");

    j = 0;
    d = 0;

    peerName = new JTextField();
    minDelay = new JTextField(d + "");
    jitter = new JTextField(j + "");
    status = new JTextField();
    status.setEditable(false);
    sequencer = new JTextField();
    sequencer.setEditable(false);
    jcb = new JComboBox<String>();

    jcb.addItem(FTType.UB);
    jcb.addItem(FTType.BB);
    jcb.addItem(FTType.UUB);

    jcb.addItemListener(new ItemListener() {

      @Override
      public void itemStateChanged(ItemEvent e) {
        if (cb2.isSelected()) {
          ftManager.setFTType(e.getItem().toString());
        }
      }

    });

    minDelay.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String name = peerName.getText();
        int delay = Integer.parseInt(minDelay.getText());
        if (delay != 0 && name != "") {
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
      public void removeUpdate(DocumentEvent e) {}

      @Override
      public void changedUpdate(DocumentEvent e) {}

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
      public void removeUpdate(DocumentEvent e) {}

      @Override
      public void changedUpdate(DocumentEvent e) {}

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
        minDelay.setText("0");
        dc = true;
      }

    });

    jb = new JButton("View History");
    jb.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        communicator.toClient(ftManager.getSequencer(),
            new RecoverHistory(communicator.getClientName()));
        jb.setEnabled(false);
      }

    });

    cb2 = new JCheckBox();
    cb2.addChangeListener(new ChangeListener() {

      @Override
      public void stateChanged(ChangeEvent e) {
        ftManager.setDynamicFT(cb2.isSelected());
        refresh();
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

  public void setSequencer(String s) {
    sequencerName = s;
  }

  public void refresh() {
    sequencer.setText(ftManager.getSequencer());
  }

  public void setAlgorithmStatus(String s) {
    status.setText(s);
  }

  public static void main(String[] args) {
    new FTGUI(null);
  }

}
