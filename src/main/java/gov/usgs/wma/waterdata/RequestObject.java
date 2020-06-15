package gov.usgs.wma.waterdata;

public class RequestObject {

	// TODO which ID are we using?  for sure field_visit_identifier but possibly other stuff should come in from
	// from the transform lambda...
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}