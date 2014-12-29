package glazer.weather;

public class WeatherNow {

	private String name;
	private Weather[] weather;
	// has temp
	private Main main;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Weather[] getWeather() {
		return weather;
	}

	public void setWeather(Weather[] weather) {
		this.weather = weather;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
