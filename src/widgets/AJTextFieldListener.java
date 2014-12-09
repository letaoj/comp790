package widgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AJTextFieldListener implements ActionListener, DocumentListener{
	JTextField jTextField;
	public AJTextFieldListener(JTextField aJTextField) {
		jTextField = aJTextField;
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("New text entered:"  + jTextField.getText());		
	}
	public void insertUpdate(DocumentEvent e) {	
		int newPos = e.getOffset();		
		char newChar = jTextField.getText().charAt(newPos);
		System.out.println("Character " + newChar + "  inserted at " + newPos);			
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
