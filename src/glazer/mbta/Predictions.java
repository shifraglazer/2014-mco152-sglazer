package glazer.mbta;

public class Predictions {
	private String Stop;
	private int Seconds;
	public Predictions(String stop, int seconds) {
		Stop = stop;
		Seconds = seconds;
	}
	public String getStop() {
		return Stop;
	}
	public void setStop(String stop) {
		Stop = stop;
	}
	public int getSeconds() {
		return Seconds;
	}
	public void setSeconds(int seconds) {
		Seconds = seconds;
	}
}
