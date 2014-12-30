package glazer.mbta;

public class Direction {
private String direction_name;
private Trip[] trip;
public String getDirection_name() {
	return direction_name;
}
public void setDirection_name(String direction_name) {
	this.direction_name = direction_name;
}
public Trip[] getTrip() {
	return trip;
}
public void setTrip(Trip[] trip) {
	this.trip = trip;
}
}
