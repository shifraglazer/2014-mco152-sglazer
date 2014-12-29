package glazer.bike;

public class StationBeanList {

	private String stationName;
	private int availableDocks;
	private int totalDocks;
	private int availableBikes;
	
	public StationBeanList(String stationName, int availableDocks,
			int totalDocks, int availableBikes) {
		
		this.stationName = stationName;
		this.availableDocks = availableDocks;
		this.totalDocks = totalDocks;
		this.availableBikes = availableBikes;
	}


	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getAvailableDocks() {
		return availableDocks;
	}
	public void setAvailableDocks(int availableDocks) {
		this.availableDocks = availableDocks;
	}
	public int getTotalDocks() {
		return totalDocks;
	}
	public void setTotalDocks(int totalDocks) {
		this.totalDocks = totalDocks;
	}
	public int getAvailableBikes() {
		return availableBikes;
	}
	public void setAvailableBikes(int availableBikes) {
		this.availableBikes = availableBikes;
	}
	
}
