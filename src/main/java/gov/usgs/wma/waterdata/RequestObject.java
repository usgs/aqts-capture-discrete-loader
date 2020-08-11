package gov.usgs.wma.waterdata;

public class RequestObject {

	private String locationIdentifier;
	private String monitoringLocationIdentifier;

	public String getLocationIdentifier() {
		return locationIdentifier;
	}
	public void setLocationIdentifier(String locationIdentifier) {
		this.locationIdentifier = locationIdentifier;
	}
	public String getMonitoringLocationIdentifier() {
		return monitoringLocationIdentifier;
	}
	public void setMonitoringLocationIdentifier(String monitoringLocationIdentifier) {
		this.monitoringLocationIdentifier = monitoringLocationIdentifier;
	}
}
