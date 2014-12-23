package glazer.nytimes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class NYTimes {

	private Response response;

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public static void main(String[] args) {
		Gson gson = new Gson();
		try {
			URL url = new URL(
					"http://api.nytimes.com/svc/search/v2/articlesearch.json?q=headline&page=0&sort=newest&api-key=2a9da2ffbe28f55ce356da64297b3fda:13:70503580");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			byte b[] = new byte[4096];
			int n = -1;
			StringBuilder builder = new StringBuilder();

			while ((n = in.read(b)) != -1) {
				builder.append(new String(b, 0, n));
			}

			String json = builder.toString();
			NYTimes nytimes = gson.fromJson(json, NYTimes.class);
			GUI gui = new GUI(nytimes);

			gui.setVisible(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
