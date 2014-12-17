package glazer.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;

public class CurrentWeather {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		WeatherFrame frame=new WeatherFrame(now.getName(),now.getMain().getTemp(),now.getMain().getTempMin(),now.getMain().getTempMax(),now.getWeather());
		frame.setVisible(true);
		
		
		
		
	}

}
