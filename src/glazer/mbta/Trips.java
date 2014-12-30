package glazer.mbta;

public class Trips {
private String TripID;
private String Destination;
private Predictions[] Predictions;
public Trips(String tripID, String destination,Predictions[] predictions) {
	TripID = tripID;
	Destination = destination;
	Predictions = predictions;
}
public String getTripID() {
	return TripID;
}
public void setTripID(String tripID) {
	TripID = tripID;
}
public String getDestination() {
	return Destination;
}
public void setDestination(String destination) {
	Destination = destination;
}
public Predictions[] getPredictions() {
	return Predictions;
}
public void setPredictions(Predictions[] predictions) {
	Predictions = predictions;
}

}
