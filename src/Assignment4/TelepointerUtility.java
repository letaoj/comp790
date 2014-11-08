package Assignment4;

import java.awt.Graphics;

import javax.swing.JComponent;

import util.awt.AnExtendibleTelePointerGlassPane;
import util.awt.GraphicsPainter;

public class TelepointerUtility implements GraphicsPainter {

	AnExtendibleTelePointerGlassPane glassPane;
	AGUIGlassPaneController guiGlassPaneController;

	public TelepointerUtility(JComponent glassPane) {
		this.glassPane = (AnExtendibleTelePointerGlassPane) glassPane;
		this.guiGlassPaneController = (AGUIGlassPaneController) this.glassPane
				.getGlassPaneController();
	}

	@Override
	public void paint(Graphics g) {
		if (guiGlassPaneController.isShowTelePointer()) {
			g.setColor(guiGlassPaneController.getColor());
			g.fillRect(glassPane.getPointerX(), glassPane.getPointerY(),
					guiGlassPaneController.getPointerWidth(),
					guiGlassPaneController.getPointerHeight());
		}
	}
}
