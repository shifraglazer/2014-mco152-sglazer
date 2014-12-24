package glazer.weather;

import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
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
		container.add(picture);
		container.add(label);
		container.add(tempmin);
		container.add(tempmax);
		container.add(temperature);
		container.add(des);
		WeatherDownloadThread thread = new WeatherDownloadThread(this);
		thread.start();

	}

	public void displayWeather(WeatherNow now) {
		StringBuilder builder = new StringBuilder();
		builder.append("Weather description: ");
		Weather[] descr = now.getWeather();
		for (int i = 0; i < descr.length; i++) {
			builder.append(descr[i].getDescription() + " \n");
		}
		des.setText(builder.toString());
		des.setOpaque(true);
		URL pic;
		String url = "http://openweathermap.org/img/w/";
		String pictureurl = descr[0].getIcon();
		try {
			pic = new URL(url + pictureurl + ".png");

			PicDownloadThread picThread = new PicDownloadThread(pic, picture);
			picThread.start();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setText("Weather for " + now.getName());
		label.setOpaque(true);
		temperature.setText("Temperature now is: " + now.getMain().getTemp());
		temperature.setOpaque(true);
		tempmin.setText("Temperature min: " + now.getMain().getTempMin());
		tempmin.setOpaque(true);
		tempmax.setText("Temperature max: " + now.getMain().getTempMax());
		tempmax.setOpaque(true);

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

	}
}
