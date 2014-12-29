package glazer.bike;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class Bike {
	private StationBeanList[] stationBeanList;

	public StationBeanList[] getStationBeanList() {
		return stationBeanList;
	}



	public void setStationBeanList(StationBeanList[] stationBeanList) {
		this.stationBeanList = stationBeanList;
	}



	public static void main(String args[]){
		Gson gson=new Gson();
		BikeGui gui=new BikeGui();
		
		try {
			URL url=new URL("http://www.citibikenyc.com/stations/json");
			URLConnection connection= url.openConnection();
			InputStream in=connection.getInputStream();
			String json;
			json = IOUtils.toString(in);
			Bike bike=gson.fromJson(json.toString(), Bike.class);
			gui.setUp(bike);
			gui.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
