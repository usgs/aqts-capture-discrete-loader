package gov.usgs.wma.waterdata;

public class ResultObject {
	private int insertCount;
	private int deleteCount;
	String monitoringLocationIdentifier;

	public int getInsertCount() {
		return insertCount;
	}

	public void setInsertCount(final int count) {
		this.insertCount = count;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(final int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public String getMonitoringLocationIdentifier() {
		return monitoringLocationIdentifier;
	}

	public void setMonitoringLocationIdentifier(final String monitoringLocationIdentifier) {
		this.monitoringLocationIdentifier = monitoringLocationIdentifier;
	}
}
