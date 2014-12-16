package glazer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WeatherFrame extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public WeatherFrame() {
		this.setSize(800, 600);
		this.setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		container.setLayout(layout);
		northContainer.add(new JLabel("Hello World"));
		northContainer.add(new JLabel("second label"));
		container.add(northContainer, BorderLayout.NORTH);

		// container.add(new JLabel("Hello World"),BorderLayout.NORTH);
		// container.add(new JLabel("ello Wod"),BorderLayout.NORTH);
		container.add(new JLabel("Happy Birthday"), BorderLayout.WEST);
		container.add(new JLabel("Happy Tuesday"), BorderLayout.EAST);
		container.add(new JLabel("This class rocks!"), BorderLayout.SOUTH);
		container.add(new JLabel("Thanx for silence."), BorderLayout.CENTER);
		JLabel label = new JLabel("Thank you");
		label.setBackground(Color.GREEN);
		label.setOpaque(true);
		container.add(label, BorderLayout.CENTER);
		/*
		 * BoxLayout layout=new BoxLayout(container,BoxLayout.Y_AXIS);
		 * container.add(new JLabel("Hello World")); container.add(new
		 * JLabel("This class rocks!")); container.add(new
		 * JLabel("Thanx for silence.")); container.setLayout(layout);
		 */

	}

	public static void main(String args[]) {
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
	}
}
