package glazer.opportunity;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PictureDownload extends Thread {

	private URL url;
	private JLabel label;

	public PictureDownload(URL url, JLabel label) {
		this.url = url;
		this.label = label;
	}

	@Override
	public void run() {
		ImageIcon icon;
		icon = new ImageIcon(url);
		label.setIcon(icon);
		label.setVisible(true);

	}
}
