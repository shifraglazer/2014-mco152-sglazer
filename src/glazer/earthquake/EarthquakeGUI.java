package glazer.earthquake;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class EarthquakeGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EarthquakeGUI(Earthquake earthquake) {
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Significant Earthquakes in Past Month");
		Container container = getContentPane();
		container.setLayout(new FlowLayout());

		Features[] features = earthquake.getFeatures();
		JLabel label = new JLabel();
		label.setText("Significant Earthquakes in past month:");
		container.add(label);
		String[] text = new String[features.length];
		for (int i = 0; i < features.length; i++) {
			text[i] = "Place: " + features[i].getProperties().getPlace()
					+ " Magnitude: " + features[i].getProperties().getMag();
		}
		JList<String> list = new JList<String>(text);
		container.add(list);
		container.setVisible(true);

	}

}
