package gov.usgs.wma.waterdata;

import java.sql.Timestamp;
import java.util.Objects;

public class DiscreteGroundWater {

	protected String fieldVisitIdentifier;
	protected String locationIdentifier;
	protected String agencyCode;
	protected String dateTimeAccuracyCode;
	protected String dateTimeAccuracy;
	protected Timestamp startTime;
	protected Timestamp endTime;
	protected String party;
	protected String remarks;
	protected String weather;
	protected String isValidHeaderInfo;
	protected String completedWork;
	protected Timestamp lastModified;
	protected String parameter;
	protected String parmCd;
	protected String monitoringMethod;
	protected String nwisMethodCode;
	protected String fieldVisitValue;
	protected String unit;
	protected String uncertainty;
	protected String readingType;
	protected String manufacturer;
	protected String model;
	protected String serialNumber;
	protected Timestamp fieldVisitTime;
	protected String fieldVisitComments;
	protected String publish;
	protected String isValidReadings;
	protected String referencePointUniqueId;
	protected String useLocationDatumAsReference;
	protected String readingQualifier;
	protected String readingQualifiers;
	protected String groundWaterMeasurement;
	protected String datum;
	protected String collectionAgency;

	public String getFieldVisitIdentifier() {
		return fieldVisitIdentifier;
	}

	public void setFieldVisitIdentifier(String fieldVisitIdentifier) {
		this.fieldVisitIdentifier = fieldVisitIdentifier;
	}

	public String getLocationIdentifier() {
		return locationIdentifier;
	}

	public void setLocationIdentifier(String locationIdentifier) {
		this.locationIdentifier = locationIdentifier;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}

	public String getDateTimeAccuracyCode() {
		return dateTimeAccuracyCode;
	}

	public void setDateTimeAccuracyCode(final String dateTimeAccuracyCode) {
		this.dateTimeAccuracyCode = dateTimeAccuracyCode;
	}

	public String getDateTimeAccuracyText() {
		return dateTimeAccuracy;
	}

	public void setDateTimeAccuracyText(final String dateTimeAccuracy) {
		this.dateTimeAccuracy = dateTimeAccuracy;
	}

	public String getMonitoringLocationIdentifier() {
		return agencyCode + '-' + locationIdentifier;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getIsValidHeaderInfo() {
		return isValidHeaderInfo;
	}

	public void setIsValidHeaderInfo(String isValidHeaderInfo) {
		this.isValidHeaderInfo = isValidHeaderInfo;
	}

	public String getCompletedWork() {
		return completedWork;
	}

	public void setCompletedWork(String completedWork) {
		this.completedWork = completedWork;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParmCd() {
		return parmCd;
	}

	public void setParmCd(String parmCd) {
		this.parmCd = parmCd;
	}

	public String getMonitoringMethod() {
		return monitoringMethod;
	}

	public void setMonitoringMethod(String monitoringMethod) {
		this.monitoringMethod = monitoringMethod;
	}

	public String getNwisMethodCode() {
		return nwisMethodCode;
	}

	public void setNwisMethodCode(String nwisMethodCode) {
		this.nwisMethodCode = nwisMethodCode;
	}

	public String getFieldVisitValue() {
		return fieldVisitValue;
	}

	public void setFieldVisitValue(String fieldVisitValue) {
		this.fieldVisitValue = fieldVisitValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(String uncertainty) {
		this.uncertainty = uncertainty;
	}

	public String getReadingType() {
		return readingType;
	}

	public void setReadingType(String readingType) {
		this.readingType = readingType;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public Timestamp getFieldVisitTime() {
		return fieldVisitTime;
	}

	public void setFieldVisitTime(Timestamp fieldVisitTime) {
		this.fieldVisitTime = fieldVisitTime;
	}

	public String getFieldVisitComments() {
		return fieldVisitComments;
	}

	public void setFieldVisitComments(String fieldVisitComments) {
		this.fieldVisitComments = fieldVisitComments;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public String getIsValidReadings() {
		return isValidReadings;
	}

	public void setIsValidReadings(String isValidReadings) {
		this.isValidReadings = isValidReadings;
	}

	public String getReferencePointUniqueId() {
		return referencePointUniqueId;
	}

	public void setReferencePointUniqueId(String referencePointUniqueId) {
		this.referencePointUniqueId = referencePointUniqueId;
	}

	public String getUseLocationDatumAsReference() {
		return useLocationDatumAsReference;
	}

	public void setUseLocationDatumAsReference(String useLocationDatumAsReference) {
		this.useLocationDatumAsReference = useLocationDatumAsReference;
	}

	public String getReadingQualifier() {
		return readingQualifier;
	}

	public void setReadingQualifier(String readingQualifier) {
		this.readingQualifier = readingQualifier;
	}

	public String getReadingQualifiers() {
		return readingQualifiers;
	}

	public void setReadingQualifiers(String readingQualifiers) {
		this.readingQualifiers = readingQualifiers;
	}

	public String getGroundWaterMeasurement() {
		return groundWaterMeasurement;
	}

	public void setGroundWaterMeasurement(String groundWaterMeasurement) {
		this.groundWaterMeasurement = groundWaterMeasurement;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getCollectionAgency() {
		return collectionAgency;
	}

	public void setCollectionAgency(String collectionAgency) {
		this.collectionAgency = collectionAgency;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		DiscreteGroundWater that = (DiscreteGroundWater) object;
		return java.util.Objects.equals(fieldVisitIdentifier, that.fieldVisitIdentifier) &&
				java.util.Objects.equals(locationIdentifier, that.locationIdentifier) &&
				java.util.Objects.equals(agencyCode, that.agencyCode) &&
				java.util.Objects.equals(dateTimeAccuracyCode, that.dateTimeAccuracyCode) &&
				java.util.Objects.equals(dateTimeAccuracy, that.dateTimeAccuracy) &&
				java.util.Objects.equals(startTime, that.startTime) &&
				java.util.Objects.equals(endTime, that.endTime) &&
				java.util.Objects.equals(party, that.party) &&
				java.util.Objects.equals(remarks, that.remarks) &&
				java.util.Objects.equals(weather, that.weather) &&
				java.util.Objects.equals(isValidHeaderInfo, that.isValidHeaderInfo) &&
				java.util.Objects.equals(completedWork, that.completedWork) &&
				java.util.Objects.equals(lastModified, that.lastModified) &&
				java.util.Objects.equals(parameter, that.parameter) &&
				java.util.Objects.equals(parmCd, that.parmCd) &&
				java.util.Objects.equals(monitoringMethod, that.monitoringMethod) &&
				java.util.Objects.equals(nwisMethodCode, that.nwisMethodCode) &&
				java.util.Objects.equals(fieldVisitValue, that.fieldVisitValue) &&
				java.util.Objects.equals(unit, that.unit) &&
				java.util.Objects.equals(uncertainty, that.uncertainty) &&
				java.util.Objects.equals(readingType, that.readingType) &&
				java.util.Objects.equals(manufacturer, that.manufacturer) &&
				java.util.Objects.equals(model, that.model) &&
				java.util.Objects.equals(serialNumber, that.serialNumber) &&
				java.util.Objects.equals(fieldVisitTime, that.fieldVisitTime) &&
				java.util.Objects.equals(fieldVisitComments, that.fieldVisitComments) &&
				java.util.Objects.equals(publish, that.publish) &&
				java.util.Objects.equals(isValidReadings, that.isValidReadings) &&
				java.util.Objects.equals(referencePointUniqueId, that.referencePointUniqueId) &&
				java.util.Objects.equals(useLocationDatumAsReference, that.useLocationDatumAsReference) &&
				java.util.Objects.equals(readingQualifier, that.readingQualifier) &&
				java.util.Objects.equals(readingQualifiers, that.readingQualifiers) &&
				java.util.Objects.equals(groundWaterMeasurement, that.groundWaterMeasurement) &&
				java.util.Objects.equals(datum, that.datum) &&
				java.util.Objects.equals(collectionAgency, that.collectionAgency);
	}

	public int hashCode() {
		return Objects.hash(
				fieldVisitIdentifier,
				locationIdentifier,
				agencyCode,
				dateTimeAccuracyCode,
				dateTimeAccuracy,
				startTime,
				endTime,
				party,
				remarks,
				weather,
				isValidHeaderInfo,
				completedWork,
				lastModified,
				parameter,
				parmCd,
				monitoringMethod,
				nwisMethodCode,
				fieldVisitValue,
				unit,
				uncertainty,
				readingType,
				manufacturer,
				model,
				serialNumber,
				fieldVisitTime,
				fieldVisitComments,
				publish,
				isValidReadings,
				referencePointUniqueId,
				useLocationDatumAsReference,
				readingQualifier,
				readingQualifiers,
				groundWaterMeasurement,
				datum,
				collectionAgency
		);
	}
}
