package glazer.opportunity;

public class NasaDownload {

	private MI_Images[] mi_images;
	private Pcam_Images[] pcam_images;
	private Ncam_Images[] ncam_images;
	private Fcam_images[] fcam_images;

	public NasaDownload() {

	}

	public MI_Images[] getMi_images() {
		return mi_images;
	}

	public void setMi_images(MI_Images[] mi_images) {
		this.mi_images = mi_images;
	}

	public Pcam_Images[] getPcam_images() {
		return pcam_images;
	}

	public void setPcam_images(Pcam_Images[] pcam_images) {
		this.pcam_images = pcam_images;
	}

	public Ncam_Images[] getNcam_images() {
		return ncam_images;
	}

	public void setNcam_images(Ncam_Images[] ncam_images) {
		this.ncam_images = ncam_images;
	}

	public Fcam_images[] getFcam_images() {
		return fcam_images;
	}

	public void setFcam_images(Fcam_images[] fcam_images) {
		this.fcam_images = fcam_images;
	}

}
