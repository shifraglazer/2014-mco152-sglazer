package glazer.opportunity;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class NasaThread extends Thread {

	private URL[] images;
	private NasaFrame frame;

	public NasaThread(NasaFrame frame) {
		images = new URL[255];
		this.frame = frame;
	}

	@Override
	public void run() {
		Gson gson = new Gson();
		try {

			URL url = new URL(
					"http://merpublic.s3.amazonaws.com/oss/mera/images/images_sol13.json ");
			URLConnection connection = url.openConnection();
			InputStream input = connection.getInputStream();
			String json = IOUtils.toString(input);
			NasaDownload nasa = gson.fromJson(json.toString(),
					NasaDownload.class);
			images = new URL[255];
			int image = 0;
			int length = nasa.getMi_images().length;
			for (int i = 0; i < length; i++) {
				int l = nasa.getMi_images()[i].getImages().length;
				for (int j = 0; j < l; j++) {
					images[image] = nasa.getMi_images()[i].getImages()[j]
							.getUrl();
					image++;

				}
			}
			length = nasa.getNcam_images().length;
			for (int i = 0; i < length; i++) {
				int l = nasa.getNcam_images()[i].getImages().length;
				for (int j = 0; j < l; j++) {
					images[image] = nasa.getNcam_images()[i].getImages()[j]
							.getUrl();
					image++;

				}
			}
			length = nasa.getFcam_images().length;
			for (int i = 0; i < length; i++) {
				int l = nasa.getFcam_images()[i].getImages().length;
				for (int j = 0; j < l; j++) {
					images[image] = nasa.getFcam_images()[i].getImages()[j]
							.getUrl();
					image++;

				}
			}
			length = nasa.getPcam_images().length;
			for (int i = 0; i < length; i++) {
				int l = nasa.getPcam_images()[i].getImages().length;
				for (int j = 0; j < l; j++) {
					images[image] = nasa.getPcam_images()[i].getImages()[j]
							.getUrl();
					image++;

				}
			}

			frame.setImages(images);
		} catch (Exception e) {
		}

	}

}
