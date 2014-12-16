package glazer.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;

public class CurrentWeather {

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
