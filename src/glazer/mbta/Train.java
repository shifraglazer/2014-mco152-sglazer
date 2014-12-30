package glazer.mbta;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class Train {
	private TripList TripList;
	
	public Train(TripList tripList) {
		TripList = tripList;
	}

	public TripList getTripList() {
		return TripList;
	}

	public void setTripList(TripList tripList) {
		TripList = tripList;
	}

	public static void main(String[] args) {
		Gson gson=new Gson();
		
		try {
			URL url=new URL("http://www.mbta.com/uploadedfiles/SampleRed.json");
			URLConnection connection=url.openConnection();
			InputStream in;
			in = connection.getInputStream();
			String json=IOUtils.toString(in);
			Train train=gson.fromJson(json, Train.class);
			System.out.println(train.getTripList().getLine());
			int length=train.getTripList().getTrips().length;
			Trips[] trip=train.getTripList().getTrips();
			for(int i=0;i<length;i++){
			System.out.println("Trip ID: " +trip[i].getTripID());	
			
			System.out.println("Destination "+trip[i].getDestination());
			int size=trip[i].getPredictions().length;
			Predictions[] pred=trip[i].getPredictions();
			for(int j=0;j<size;j++){
			System.out.println("Stop: "+pred[j].getStop());
			System.out.println(pred[j].getSeconds());
			System.out.println();
			}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
