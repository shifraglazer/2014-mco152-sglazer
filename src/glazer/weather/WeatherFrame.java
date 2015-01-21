package glazer.weather;

import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;

public class WeatherFrame extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JLabel tempmin;
	private JLabel tempmax;
	private JLabel temperature;
	private JLabel des;
	private JLabel picture;

	// private JList<JPanel> list;

	public WeatherFrame() {
		this.setSize(800, 600);
		this.setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);

		container.setLayout(layout);
		label = new JLabel("Downloading Weather....");
		tempmin = new JLabel();
		tempmax = new JLabel();
		temperature = new JLabel();
		des = new JLabel();
		picture = new JLabel();
		container.add(label);
		container.add(temperature);
		container.add(tempmax);
		container.add(tempmin);
		// container.add(list);
		container.add(des);
		container.add(picture);
		WeatherDownloadThread thread = new WeatherDownloadThread(this);
		thread.start();

	}

	public void displayWeather(WeatherNow now) {
		StringBuilder builder = new StringBuilder();

		Weather[] descr = now.getWeather();
		// descriptions = new JPanel[descr.length];
		for (int i = 0; i < descr.length; i++) {
			/*
			 * descriptions[i]=new JPanel();
			 * des.setText(descr[i].getDescription()); descriptions[i].add(des);
			 * String url = descr[i].getIcon(); PicDownloadThread pic = new
			 * PicDownloadThread(url, picture); descriptions[i].add(picture);
			 * list.add(descriptions[i]);
			 */
			builder.append(descr[i].getDescription());
			if (i < descr.length - 1) {
				builder.append(", ");
			}
		}
		// list.setVisible(true);
		des.setText(builder.toString());
		des.setOpaque(true);
		String pic;
		String url = "http://openweathermap.org/img/w/";
		String pictureurl = descr[0].getIcon();
		pic = url;
		pic += pictureurl;
		pic += ".png";

		PicDownloadThread picThread = new PicDownloadThread(pic, picture);
		picThread.start();
		label.setText(now.getName());
		label.setFont(label.getFont().deriveFont(30f));
		temperature.setText(now.getMain().getTemp() + " F");
		temperature.setFont(temperature.getFont().deriveFont(50f));
		tempmin.setText("L: " + now.getMain().getTempMin() + " F");
		tempmax.setText("H: " + now.getMain().getTempMax() + " F");

	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		URL url = new URL(
				"http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		byte b[] = new byte[4096];
		// num bytes returned
		int n = -1;
		StringBuilder json = new StringBuilder();
		while ((n = in.read(b)) != -1) {
			json.append(new String(b, 0, n));

		}
		Gson gson = new Gson();

		WeatherNow now = gson.fromJson(json.toString(), WeatherNow.class);
		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);
		now.getClass();

	}
}
