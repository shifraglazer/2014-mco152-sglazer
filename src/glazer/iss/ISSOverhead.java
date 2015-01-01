package glazer.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JList;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class ISSOverhead {
	private Results[] results;
	private Response[] response;
	public Response[] getResponse() {
		return response;
	}

	public void setResponse(Response[] response) {
		this.response = response;
	}

	public Results[] getResults() {
		return results;
	}

	public void setResults(Results[] results) {
		this.results = results;
	}
	
	public static void main(String args []){
		
	}
		

	public JList<String> compute(String address,ISSOverheadFrame frame) {
		Gson gson=new Gson();
		JList<String> list=null;
		try{
			String string="https://maps.googleapis.com/maps/api/geocode/json?address=";
			string+=URLEncoder.encode(address,"UTF-8");
			string+="&key=AIzaSyAirHEsA08agmW9uizDvXagTjWS3mRctPE";
			URL url=new URL(string);
			URLConnection connection= url.openConnection();
			InputStream in=connection.getInputStream();
			String json;
			json = IOUtils.toString(in);
			ISSOverhead iss=gson.fromJson(json, ISSOverhead.class);
			double lat=iss.getResults()[0].getGeometry().getLocation().getLat();
			double lng=iss.getResults()[0].getGeometry().getLocation().getLng();
			String space="http://api.open-notify.org/iss-pass.json?lat=";
			space+=lat;
			space+="&lon=";
			space+=lng;
			URL url2=new URL(space);
			URLConnection connection2= url2.openConnection();
			InputStream in2=connection2.getInputStream();
			String json2=IOUtils.toString(in2);
			ISSOverhead pass=gson.fromJson(json2,ISSOverhead.class);
		
			Response[] response=pass.getResponse();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			String[] strings=new String[response.length];
			for(int i=0;i<response.length;i++){
				int timeStamp=response[i].getRisetime();
				Date time=new Date((long)timeStamp*1000);
				strings[i]=format.format(time);
			}
			//list.set
			//list=new JList<String>(strings);
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
