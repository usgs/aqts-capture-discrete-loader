package gov.usgs.wma.waterdata;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DiscreteGroundWaterRowMapper implements RowMapper<DiscreteGroundWater> {

	@Override
	public DiscreteGroundWater mapRow(ResultSet rs, int rowNum) throws SQLException {
		DiscreteGroundWater discreteGroundWater = new DiscreteGroundWater();

		discreteGroundWater.setFieldVisitIdentifier(rs.getString("field_visit_identifier"));
		discreteGroundWater.setLocationIdentifier(rs.getString("location_identifier"));
		discreteGroundWater.setStartTime(rs.getTimestamp("start_time"));
		discreteGroundWater.setEndTime(rs.getTimestamp("end_time"));
		discreteGroundWater.setParty(rs.getString("party"));
		discreteGroundWater.setRemarks(rs.getString("remarks"));
		discreteGroundWater.setWeather(rs.getString("weather"));
		discreteGroundWater.setIsValidHeaderInfo(rs.getString("is_valid_header_info"));
		discreteGroundWater.setCompletedWork(rs.getString("completed_work"));
		discreteGroundWater.setLastModified(rs.getTimestamp("last_modified"));
		discreteGroundWater.setParameter(rs.getString("parameter"));
		discreteGroundWater.setParmCd(rs.getString("parm_cd"));
		discreteGroundWater.setMonitoringMethod(rs.getString("monitoring_method"));
		discreteGroundWater.setFieldVisitValue(rs.getString("field_visit_value"));
		discreteGroundWater.setUnit(rs.getString("unit"));
		discreteGroundWater.setUncertainty(rs.getString("uncertainty"));
		discreteGroundWater.setReadingType(rs.getString("reading_type"));
		discreteGroundWater.setManufacturer(rs.getString("manufacturer"));
		discreteGroundWater.setModel(rs.getString("model"));
		discreteGroundWater.setSerialNumber(rs.getString("serial_number"));
		discreteGroundWater.setFieldVisitTime(rs.getTimestamp("field_visit_time"));
		discreteGroundWater.setFieldVisitComments(rs.getString("field_visit_comments"));
		discreteGroundWater.setPublish(rs.getString("publish"));
		discreteGroundWater.setIsValidReadings(rs.getString("is_valid_readings"));
		discreteGroundWater.setReferencePointUniqueId(rs.getString("reference_point_unique_id"));
		discreteGroundWater.setUseLocationDatumAsReference(rs.getString("use_location_datum_as_reference"));
		discreteGroundWater.setReadingQualifier(rs.getString("reading_qualifier"));
		discreteGroundWater.setReadingQualifiers(rs.getString("reading_qualifiers"));
		discreteGroundWater.setGroundWaterMeasurement(rs.getString("ground_water_measurement"));

		return discreteGroundWater;
	}
}