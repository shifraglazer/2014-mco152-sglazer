package glazer.mbta;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class TripList {
	private String Line;
	private Trips[] Trips;
	public String getLine() {
		return Line;
	}
	public TripList(String line, Trips[] trips) {
		this.Line = line;
		this.Trips = trips;
	}
	public void setLine(String line) {
		this.Line = line;
	}
	public Trips[] getTrips() {
		return Trips;
	}
	public void setTrips(Trips[] trips) {
		Trips = trips;
	}
	public static void main(String args[]){
	Gson gson=new Gson();
		
		try {
			URL url=new URL("http://www.mbta.com/uploadedfiles/SampleRed.json");
			URLConnection connection=url.openConnection();
			InputStream in;
			in = connection.getInputStream();
			String json=IOUtils.toString(in);
			TripList list=gson.fromJson(json, TripList.class);
			System.out.println(list.getLine());
			System.out.println(list.getTrips()[0].getDestination());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

