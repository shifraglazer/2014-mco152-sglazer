package glazer.mbta;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

/*
  @SerializedName("Person Id") public String PersonId;
    @SerializedName("Last Name") public String LastName;
    @SerializedName("First Name") public String FirstName;
 */
public class Train2 {
	private String route_id;
	private String route_name;
	private String mode_name;
	private Direction[] direction;
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public String getMode_name() {
		return mode_name;
	}
	public void setMode_name(String mode_name) {
		this.mode_name = mode_name;
	}
	public Direction[] getDirection() {
		return direction;
	}
	public void setDirection(Direction[] direction) {
		this.direction = direction;
	}
	public static void main(String[] args) {
		Gson gson=new Gson();
		URL url;
		try {
			url = new URL("http://realtime.mbta.com/developer/api/v2/schedulebyroute?api_key=wX9NwuHnZU2ToO7GmGR9uw&route=CR-Providence&direction=0&format=json");
			URLConnection connection=url.openConnection();
			InputStream in;
			in = connection.getInputStream();
			String json=IOUtils.toString(in);
			Train2 train=gson.fromJson(json, Train2.class);
			System.out.println("RouteID: "+train.getRoute_id());
			System.out.println("Route Name: "+train.getRoute_name());
			System.out.println("Mode Name: "+train.getMode_name());
			Direction[] direction=train.getDirection();
			int length=direction.length;
			for(int i=0;i<length;i++){
			System.out.println("Direction "+direction[i].getDirection_name());
			Trip[] trip=direction[i].getTrip();
			int size=trip.length;
			for(int j=0;j<size;j++){
				System.out.println("Trip ID: "+trip[j].getTrip_id());
				System.out.println("Trip Name: "+trip[j].getTrip_name());
				System.out.println("Trip Headsign: "+trip[j].getTrip_headsign());
				System.out.println();
			}
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
