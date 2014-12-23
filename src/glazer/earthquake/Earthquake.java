package glazer.earthquake;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class Earthquake {

	private Features[] features;

	public Features[] getFeatures() {
		return features;
	}

	public void setFeatures(Features[] features) {
		this.features = features;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url;
		try {
			url = new URL(
					"http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
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
			String jsons = json.toString();
			Earthquake quake = gson.fromJson(jsons, Earthquake.class);

			EarthquakeGUI gui = new EarthquakeGUI(quake);
			gui.setVisible(true);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
