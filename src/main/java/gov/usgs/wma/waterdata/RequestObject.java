package gov.usgs.wma.waterdata;

import java.util.List;

public class RequestObject {
	private List<String> fieldVisitIdentifiers;

	public List<String> getFieldVisitIdentifiers() {
		return fieldVisitIdentifiers;
	}
	public void setFieldVisitIdentifiers(List<String> fieldVisitIdentifiers) {
		this.fieldVisitIdentifiers = fieldVisitIdentifiers;
	}
}
