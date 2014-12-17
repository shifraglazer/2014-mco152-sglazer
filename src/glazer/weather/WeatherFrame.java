package glazer.weather;

import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;

public class WeatherFrame extends JFrame {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public WeatherFrame(String city, double temp, double temp_min,
			double temp_max, Weather[] description) {
		this.setSize(800, 600);
		this.setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Container container = getContentPane();
		BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);

		container.setLayout(layout);
		WeatherDownloadThread thread=new WeatherDownloadThread();
		JLabel name = new JLabel("Weather for " + city);
		name.setOpaque(true);
		container.add(name);
		JLabel temperature = new JLabel("Temperature now is: " + temp);
		temperature.setOpaque(true);
		container.add(temperature);
		JLabel tempmin = new JLabel("Temperature min: " + temp_min);
		tempmin.setOpaque(true);
		container.add(tempmin);
		JLabel tempmax = new JLabel("Temperature max: " + temp_max);
		tempmax.setOpaque(true);
		container.add(tempmax);
		Weather[] descr = description;
		StringBuilder builder = new StringBuilder();
		builder.append("Weather description: ");
		for (int i = 0; i < descr.length; i++) {
			builder.append(descr[i].getDescription() + " \n");
		}
		JLabel des = new JLabel(builder.toString());
		des.setOpaque(true);
		container.add(des);

		URL pic;
		try {
			String url="http://openweathermap.org/img/w/";
			String pictureurl=descr[0].getIcon();
			pic = new URL(url+pictureurl+".png");

			ImageIcon icon = new ImageIcon(pic);

			JLabel picture = new JLabel();
			picture.setIcon(icon);
			picture.setOpaque(true);
			container.add(picture);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
			WeatherFrame frame=new WeatherFrame(now.getName(),now.getMain().getTemp(),now.getMain().getTempMin(),now.getMain().getTempMax(),now.getWeather());
			frame.setVisible(true);
			
			
			
			
		}
}
