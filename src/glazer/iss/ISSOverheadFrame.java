package glazer.iss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ISSOverheadFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField text;
	JButton button;
	JList<String> list;
	JPanel panel;
	JPanel listPanel;

	public ISSOverheadFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(400, 400);
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		text = new JTextField();
		button = new JButton();
		button.setText("Get Times");
		list = new JList<String>();

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		panel.add(text);

		ActionListener times = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String address = text.getText();
				AddressDownloadThread thread = new AddressDownloadThread(
						ISSOverheadFrame.this, address);
				thread.start();
			}

		};
		button.addActionListener(times);
		panel.add(button);
		panel.setVisible(true);

		listPanel = new JPanel();
		listPanel.add(list);
		listPanel.setBackground(Color.WHITE);
		listPanel.setVisible(true);
		container.add(panel, BorderLayout.NORTH);
		container.add(listPanel, BorderLayout.WEST);
		container.setBackground(Color.WHITE);
		container.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ISSOverheadFrame frame = new ISSOverheadFrame();
		frame.setVisible(true);
	}

}
