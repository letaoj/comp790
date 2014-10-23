package Assignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.awt.AGlassPaneController;
import util.awt.AnExtendibleTelePointerGlassPane;
import util.awt.GlassPaneController;

public class AGUIGlassPaneController extends AGlassPaneController {

	private JFrame frame;
	private JCheckBox checkbox;
	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	private JLabel widthLabel;
	private JLabel heightLabel;
	private JPanel labelAndSliderPanel;
	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	private JSlider widthSlider;
	private JSlider heightSlider;
	private MyListener listener;
	private GlassPaneController glassPane;

	protected int red;
	protected int green;
	protected int blue;

	private boolean display;

	public AGUIGlassPaneController(GlassPaneController glassPane) {
		super(glassPane);
		this.glassPane = glassPane;
		listener = new MyListener();
		initController();
		red = 255;
		green = 0;
		blue = 0;
	}

	private class MyListener implements ChangeListener, ActionListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			Object o = e.getSource();
			if (o.equals(redSlider)) {
				setRed(redSlider.getValue());
			} else if (o.equals(blueSlider)) {
				setBlue(blueSlider.getValue());
			} else if (o.equals(greenSlider)) {
				setGreen(greenSlider.getValue());
			} else if (o.equals(widthSlider)) {
				setPointerWidth(widthSlider.getValue());
			} else if (o.equals(heightSlider)) {
				setPointerHeight(heightSlider.getValue());
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			display = !display;
			setShowTelePointer(display);
		}

	}

	protected void initController() {
		frame = new JFrame("A Glass Pane Controller");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(new Dimension(400, 400));

		checkbox = new JCheckBox("Show Tele Pointer");

		redLabel = new JLabel("Red: ");
		greenLabel = new JLabel("Green: ");
		blueLabel = new JLabel("Blue: ");
		widthLabel = new JLabel("Pointer Width: ");
		heightLabel = new JLabel("Pointer Height: ");

		redSlider = new JSlider(0, 255, 255);
		greenSlider = new JSlider(0, 255, 0);
		blueSlider = new JSlider(0, 255, 0);
		widthSlider = new JSlider(1, 100, 5);
		heightSlider = new JSlider(1, 100, 5);

		display = false;

		redSlider.addChangeListener(listener);
		greenSlider.addChangeListener(listener);
		blueSlider.addChangeListener(listener);
		widthSlider.addChangeListener(listener);
		heightSlider.addChangeListener(listener);
		checkbox.addActionListener(listener);

		labelAndSliderPanel = new JPanel(new GridLayout(6, 2));
		labelAndSliderPanel.setSize(new Dimension(100, 100));

		labelAndSliderPanel.add(redLabel);
		labelAndSliderPanel.add(redSlider);
		labelAndSliderPanel.add(greenLabel);
		labelAndSliderPanel.add(greenSlider);
		labelAndSliderPanel.add(blueLabel);
		labelAndSliderPanel.add(blueSlider);
		labelAndSliderPanel.add(widthLabel);
		labelAndSliderPanel.add(widthSlider);
		labelAndSliderPanel.add(heightLabel);
		labelAndSliderPanel.add(heightSlider);

		frame.add(checkbox, BorderLayout.PAGE_START);
		frame.add(labelAndSliderPanel, BorderLayout.LINE_START);

		frame.pack();
		frame.setVisible(true);
	}

	protected Color getColor() {
		return new Color(red, green, blue);
	}

	protected void setRed(int r) {
		red = r;
		((AnExtendibleTelePointerGlassPane) glassPane).repaint();
	}

	protected void setGreen(int g) {
		green = g;
		((AnExtendibleTelePointerGlassPane) glassPane).repaint();
	}

	protected void setBlue(int b) {
		blue = b;
		((AnExtendibleTelePointerGlassPane) glassPane).repaint();
	}

	protected JFrame getFrame() {
		return frame;
	}
	
	protected void fireResize() {
		
	}
}
