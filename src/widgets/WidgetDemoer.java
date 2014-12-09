package widgets;


import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import util.awt.AnExtendibleAWTEventQueue;
import util.awt.AGlassPaneRedispatcher;
import util.awt.AnExtendibleTelePointerGlassPane;

public class WidgetDemoer {
	public static final String JTEXTFIELD_NAME = "Demo JText";
	public static final String TEXTFIELD_NAME = "Demo Text";
	public static void main(String[] args) {
		AnExtendibleAWTEventQueue.useAsEventQueue();

		createFrameWithWidgets("frame 1");
		createFrameWithWidgets("frame 2");
	}
	public static JFrame createFrameWithWidgets(String theTitle) {

		JFrame frame = new JFrame(theTitle);
		frame.setLayout(new GridLayout(5, 2));
		JTextField jTextField = new JTextField("JTextField: Edit me");
		AJTextFieldListener jTextFieldListener = new AJTextFieldListener(jTextField);
		jTextField.addActionListener(jTextFieldListener);
		jTextField.getDocument().addDocumentListener(jTextFieldListener);
		jTextField.setName(JTEXTFIELD_NAME);
		JSlider slider = new JSlider();
		TextField textField = new TextField("Text Field: Edit me");
		textField.setName(TEXTFIELD_NAME);
		Checkbox checkBox = new Checkbox(); // auto scrolled by OS with no events sent
		JCheckBox jCheckBox = new JCheckBox();
		Button button = new Button("Button: Press me");
		JButton jButton = new JButton("JButton: Press me");
		JTextArea jTextArea = new JTextArea ("JTextArea");
		TextArea textArea = new TextArea ("TextArea");
		String[] choices = {"Red", "Green", "Blue"};
		JComboBox jComboBox = new JComboBox(choices);
		JScrollPane jTextAreaScrollPane = new JScrollPane();
		jTextAreaScrollPane.setViewportView(jTextArea);
		frame.add(jTextField);
		frame.add(textField);
		frame.add(jTextAreaScrollPane);
		frame.add(textArea);
		frame.add(jButton);
		frame.add(button);
		frame.add(checkBox);
		frame.add(jCheckBox);
		frame.add(slider);
		frame.add(jComboBox);
		frame.setSize(300, 300);
		frame.setVisible(true);
		// to be commented in later
//		
//		JComponent glassPane = new AnExtendibleTelePointerGlassPane(frame);
//
//		AGlassPaneRedispatcher redispatcher = new AGlassPaneRedispatcher(glassPane, frame);
//		frame.setGlassPane(glassPane);
//		glassPane.setVisible(true);
		return frame;
//		putGlassPane(frame);

//		return frame;
	}
	
}
