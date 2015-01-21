package glazer.opportunity;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NasaFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel picture;
	private JPanel scroll;
	private JButton next;
	private JButton prev;
	private JLabel label;
	private URL[] images;
	private JLabel pic;
	private int numPic;

	private JTextField text;
	private JButton getPicture;

	NasaFrame() {
		NasaThread download = new NasaThread(this);
		download.start();
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());
		picture = new JPanel();
		container.add(picture, BorderLayout.CENTER);
		numPic = 0;
		pic = new JLabel();
		scroll = new JPanel();
		next = new JButton();
		getPicture = new JButton();
		prev = new JButton();
		label = new JLabel();
		text = new JTextField();

		text.setColumns(3);
		picture.add(pic);
		label.setText("1 of 255");
		prev.setText("<");
		next.setText(">");
		scroll.add(prev);
		scroll.add(label);
		scroll.add(next);
		getPicture.setText("Get Picture");
		scroll.add(getPicture);
		scroll.add(text);
		container.add(scroll, BorderLayout.SOUTH);
		ActionListener previous = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (numPic == 0) {
					numPic = 254;
				} else {
					numPic--;
				}
				URL url = images[numPic];
				PictureDownload down = new PictureDownload(url, pic);
				down.start();
				label.setText((numPic + 1) + " of 255");
			}

		};
		ActionListener findPicture = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (images.length > 253) {
					Integer in = Integer.valueOf(text.getText());
					URL url = images[in];
					PictureDownload down = new PictureDownload(url, pic);
					down.start();
					numPic = (in - 1);
					label.setText((in) + " of 255");
				}

			}

		};
		getPicture.addActionListener(findPicture);
		ActionListener nextPic = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (numPic == 254) {
					numPic = 0;
				} else {
					numPic++;
				}
				URL url = images[numPic];
				PictureDownload down = new PictureDownload(url, pic);
				down.start();
				label.setText((numPic + 1) + " of 255");
			}

		};
		next.addActionListener(nextPic);
		prev.addActionListener(previous);

	}

	public void setImages(URL[] images) {
		this.images = images;
		PictureDownload down = new PictureDownload(images[0], pic);
		down.start();

	}

	public static void main(String[] args) {
		NasaFrame frame = new NasaFrame();
		frame.setVisible(true);

	}
}
