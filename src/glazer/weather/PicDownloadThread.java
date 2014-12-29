package glazer.weather;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PicDownloadThread extends Thread {
	private String url;
	private JLabel label;

	public PicDownloadThread(String url, JLabel label) {
		this.url = url;
		this.label = label;
	}

	@Override
	public void run() {
		ImageIcon icon;
		try {
			icon = new ImageIcon(new URL(url));
			label.setIcon(icon);
			label.setVisible(true);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
