package im.access;

import bus.uigen.ObjectEditor;
import bus.uigen.attributes.AttributeNames;
import bus.uigen.undo.ExecutableCommand;

public class AnAccessControllerAR implements ExecutableCommand{

	@Override
	public Object execute(Object theFrame) {
		ObjectEditor.setPropertyAttribute(AnAccessController.class, "owner", AttributeNames.LABEL_POSITION, AttributeNames.LABEL_IN_BORDER);
		return null;
	}
	

}
