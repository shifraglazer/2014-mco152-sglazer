package glazer.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class AddressDownloadThread extends Thread {
	private ISSOverheadFrame frame;
	private String address;

	public AddressDownloadThread(ISSOverheadFrame frame, String address) {
		this.frame = frame;
		this.address = address;
	}

	@Override
	public void run() {
		Gson gson = new Gson();
		try {
			String string = "https://maps.googleapis.com/maps/api/geocode/json?address=";
			string += URLEncoder.encode(address, "UTF-8");
			string += "&key=AIzaSyAirHEsA08agmW9uizDvXagTjWS3mRctPE";
			URL url = new URL(string);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json;
			json = IOUtils.toString(in);
			ISSOverhead iss = gson.fromJson(json, ISSOverhead.class);
			double lat = iss.getResults()[0].getGeometry().getLocation()
					.getLat();
			double lng = iss.getResults()[0].getGeometry().getLocation()
					.getLng();
			String space = "http://api.open-notify.org/iss-pass.json?lat=";
			space += lat;
			space += "&lon=";
			space += lng;
			SpaceDownloadThread thread = new SpaceDownloadThread(frame.list,
					space);
			thread.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
