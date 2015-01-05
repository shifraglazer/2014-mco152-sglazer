package glazer.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JList;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class SpaceDownloadThread extends Thread {
	private JList<String> list;
	private String url;

	public SpaceDownloadThread(JList<String> list, String url) {
		this.list = list;
		this.url = url;
	}

	@Override
	public void run() {

		try {
			Gson gson = new Gson();
			URL url2 = new URL(url);
			URLConnection connection2;
			connection2 = url2.openConnection();
			InputStream in2 = connection2.getInputStream();
			String json2 = IOUtils.toString(in2);
			ISSOverhead pass = gson.fromJson(json2, ISSOverhead.class);

			Response[] response = pass.getResponse();
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss z");
			String[] strings = new String[response.length];
			for (int i = 0; i < response.length; i++) {
				int timeStamp = response[i].getRisetime();
				Date time = new Date((long) timeStamp * 1000);
				strings[i] = format.format(time);
			}
			list.setListData(strings);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
