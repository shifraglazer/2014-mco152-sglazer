package glazer.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class WeatherDownloadThread  extends Thread{
	@Override
	public void run(){
		URL url;
		try {
			url = new URL(
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
