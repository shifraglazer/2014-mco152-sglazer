package glazer.weather;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PicDownloadThread extends Thread{
	private URL url;
	private JLabel label;
	public PicDownloadThread(URL url,JLabel label){
		this.url=url;
	}
	public void Run(){
		ImageIcon icon = new ImageIcon(url);
		label.setIcon(icon);
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
