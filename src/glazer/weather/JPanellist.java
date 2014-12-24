package glazer.weather;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class JPanellist extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPanellist() {
		this.setSize(800, 600);
		this.setTitle("JPanel list");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel[] panel = new JPanel[3];
		Container co = this.getContentPane();
		co.setLayout(new BoxLayout(co, BoxLayout.X_AXIS));
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new JPanel();
			JLabel label = new JLabel();
			label.setText(String.valueOf(i) + " panel");
			panel[i].add(label);
			panel[i].setVisible(true);
			co.add(panel[i]);
		}
		JList<JPanel> list = new JList<JPanel>(panel);
		list.setVisible(true);

		co.add(list);
		co.setVisible(true);
	}

	public static void main(String[] args) {
		JPanellist list = new JPanellist();
		list.setVisible(true);
	}

}
