package gov.usgs.wma.waterdata;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
@DbUnitConfiguration(
		dataSetLoader=FileSensingDataSetLoader.class,
		databaseConnection={
			"transform",
			"observation"})
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional(propagation=Propagation.NOT_SUPPORTED)
@Import({DBTestConfig.class})
@DirtiesContext
public abstract class BaseTestDao {


	public static final String LOCATION_IDENTIFIER_1 = "323302117055201";
	public static final String LOCATION_IDENTIFIER_2 = "415502124084701";
	public static final String LOCATION_IDENTIFIER_3 = "344405117580201";
	public static final String MONITORING_LOCATION_IDENTIFIER_1 = "USGS-323302117055201";
	public static final String MONITORING_LOCATION_IDENTIFIER_2 = "USGS-415502124084701";
	public static final String MONITORING_LOCATION_IDENTIFIER_3 = "USGS-344405117580201";
	public static final String BAD_LOCATION_IDENTIFIER = "badLocationIdentifier";
	public static final String BAD_MONITORING_LOCATION_IDENTIFIER = "USGS-badLocationIdentifier";
	public RequestObject request;
	public DiscreteGroundWater discreteGroundWater1;
	public DiscreteGroundWater discreteGroundWater2;
	public DiscreteGroundWater discreteGroundWater3;
	public DiscreteGroundWater discreteGroundWater4;

	@BeforeEach
	public void setup() {
		request = new RequestObject();

		discreteGroundWater1 = new DiscreteGroundWater();
		discreteGroundWater1.setFieldVisitIdentifier("0c8d1725-db51-47d9-aabb-a1dd9d1bdf08");
		discreteGroundWater1.setLocationIdentifier(LOCATION_IDENTIFIER_1);
		discreteGroundWater1.setAgencyCode("USGS");
		discreteGroundWater1.setStartTime(Timestamp.valueOf("2013-01-08 08:00:00"));
		discreteGroundWater1.setEndTime(Timestamp.valueOf("2013-01-09 07:59:59"));
		discreteGroundWater1.setParty("GOM");
		discreteGroundWater1.setRemarks("");
		discreteGroundWater1.setWeather(null);
		discreteGroundWater1.setIsValidHeaderInfo("true");
		discreteGroundWater1.setCompletedWork("{\"LevelsPerformed\": false, \"CollectionAgency\": \"USGS\", \"OtherSampleTaken\": false, \"SedimentSampleTaken\": false, \"BiologicalSampleTaken\": false, \"RecorderDataCollected\": false, \"WaterQualitySampleTaken\": false, \"GroundWaterLevelPerformed\": true, \"SafetyInspectionPerformed\": false}");
		discreteGroundWater1.setLastModified(Timestamp.valueOf("2020-05-11 04:20:35.874233"));
		discreteGroundWater1.setParameter("Water level, depth MP");
		discreteGroundWater1.setParmCd("61055");
		discreteGroundWater1.setMonitoringMethod("GW level, calib electric tape");
		discreteGroundWater1.setFieldVisitValue("11.28");
		discreteGroundWater1.setUnit("ft");
		discreteGroundWater1.setUncertainty("0.01");
		discreteGroundWater1.setReadingType("ReferencePrimary");
		discreteGroundWater1.setManufacturer("Unspecified");
		discreteGroundWater1.setModel("Unspecified");
		discreteGroundWater1.setSerialNumber("Unspecified");
		discreteGroundWater1.setFieldVisitTime(Timestamp.valueOf("2013-01-08 21:00:00"));
		discreteGroundWater1.setFieldVisitComments("Source: Reporting Agency");
		discreteGroundWater1.setPublish("true");
		discreteGroundWater1.setIsValidReadings("true");
		discreteGroundWater1.setReferencePointUniqueId("e42ffa2b69a3488aa8567a7e0c31e8cc");
		discreteGroundWater1.setUseLocationDatumAsReference("false");
		discreteGroundWater1.setReadingQualifier("Static");
		discreteGroundWater1.setReadingQualifiers("[\"Static\"]");
		discreteGroundWater1.setGroundWaterMeasurement("{}");

		discreteGroundWater2 = new DiscreteGroundWater();
		discreteGroundWater2.setFieldVisitIdentifier("035594ec-0451-4d63-a0a9-7fd471ee6f78");
		discreteGroundWater2.setLocationIdentifier(LOCATION_IDENTIFIER_2);
		discreteGroundWater2.setAgencyCode("USGS");
		discreteGroundWater2.setDateTimeAccuracyCode(DateTimeAccuracy.DAY.getCode());
		discreteGroundWater2.setDateTimeAccuracyText(DateTimeAccuracy.DAY.getText());
		discreteGroundWater2.setStartTime(Timestamp.valueOf("1964-01-16 08:00:00"));
		discreteGroundWater2.setEndTime(Timestamp.valueOf("1964-01-17 07:59:59"));
		discreteGroundWater2.setParty(null);
		discreteGroundWater2.setRemarks("");
		discreteGroundWater2.setWeather(null);
		discreteGroundWater2.setIsValidHeaderInfo("true");
		discreteGroundWater2.setCompletedWork("{\"LevelsPerformed\": false, \"OtherSampleTaken\": false, \"SedimentSampleTaken\": false, \"BiologicalSampleTaken\": false, \"RecorderDataCollected\": false, \"WaterQualitySampleTaken\": false, \"GroundWaterLevelPerformed\": true, \"SafetyInspectionPerformed\": false}");
		discreteGroundWater2.setLastModified(Timestamp.valueOf("2020-05-11 15:02:32.640317"));
		discreteGroundWater2.setParameter("Water level, depth MP");
		discreteGroundWater2.setParmCd("61055");
		discreteGroundWater2.setMonitoringMethod("GW level, steel tape");
		discreteGroundWater2.setFieldVisitValue("16.16");
		discreteGroundWater2.setUnit("ft");
		discreteGroundWater2.setUncertainty("0.01");
		discreteGroundWater2.setReadingType("ReferencePrimary");
		discreteGroundWater2.setManufacturer("Unspecified");
		discreteGroundWater2.setModel("Unspecified");
		discreteGroundWater2.setSerialNumber("Unspecified");
		discreteGroundWater2.setFieldVisitTime(Timestamp.valueOf("1964-01-16 12:00:00"));
		discreteGroundWater2.setFieldVisitComments("Water level date represents the day, Source: Unknown");
		discreteGroundWater2.setPublish("true");
		discreteGroundWater2.setIsValidReadings("true");
		discreteGroundWater2.setReferencePointUniqueId("f2f1d100913742e2b8b9cea16764ff30");
		discreteGroundWater2.setUseLocationDatumAsReference("false");
		discreteGroundWater2.setReadingQualifier("Static");
		discreteGroundWater2.setReadingQualifiers("[\"Static\"]");
		discreteGroundWater2.setGroundWaterMeasurement("{}");

		discreteGroundWater3 =  new DiscreteGroundWater();
		discreteGroundWater3.setFieldVisitIdentifier("014cc69b-b5ed-4d12-9be7-90e4252db81c");
		discreteGroundWater3.setLocationIdentifier(LOCATION_IDENTIFIER_3);
		discreteGroundWater3.setAgencyCode("USGS");
		discreteGroundWater3.setDateTimeAccuracyCode(DateTimeAccuracy.MONTH.getCode());
		discreteGroundWater3.setDateTimeAccuracyText(DateTimeAccuracy.MONTH.getText());
		discreteGroundWater3.setStartTime(Timestamp.valueOf("1952-03-28 08:00:00"));
		discreteGroundWater3.setEndTime(Timestamp.valueOf("1952-03-29 07:59:59"));
		discreteGroundWater3.setParty(null);
		discreteGroundWater3.setRemarks("");
		discreteGroundWater3.setWeather(null);
		discreteGroundWater3.setIsValidHeaderInfo("true");
		discreteGroundWater3.setCompletedWork("{\"LevelsPerformed\": false, \"CollectionAgency\": \"USGS\", \"OtherSampleTaken\": false, \"SedimentSampleTaken\": false, \"BiologicalSampleTaken\": false, \"RecorderDataCollected\": false, \"WaterQualitySampleTaken\": false, \"GroundWaterLevelPerformed\": true, \"SafetyInspectionPerformed\": false}");
		discreteGroundWater3.setLastModified(Timestamp.valueOf("2020-05-11 08:57:10.376957"));
		discreteGroundWater3.setParameter("Water level, depth MP");
		discreteGroundWater3.setParmCd("61055");
		discreteGroundWater3.setMonitoringMethod("GW level, steel tape");
		discreteGroundWater3.setFieldVisitValue("83.08");
		discreteGroundWater3.setUnit("ft");
		discreteGroundWater3.setUncertainty("0.01");
		discreteGroundWater3.setReadingType("ReferencePrimary");
		discreteGroundWater3.setManufacturer("Unspecified");
		discreteGroundWater3.setModel("Unspecified");
		discreteGroundWater3.setSerialNumber("Unspecified");
		discreteGroundWater3.setFieldVisitTime(Timestamp.valueOf("1952-03-28 12:00:00"));
		discreteGroundWater3.setFieldVisitComments("Water level date represents the day, Source: Reporting Agency");
		discreteGroundWater3.setPublish("true");
		discreteGroundWater3.setIsValidReadings("true");
		discreteGroundWater3.setReferencePointUniqueId("85352e7a8c734208907d67f512ac5b50");
		discreteGroundWater3.setUseLocationDatumAsReference("false");
		discreteGroundWater3.setReadingQualifier("Static");
		discreteGroundWater3.setReadingQualifiers("[\"Static\"]");
		discreteGroundWater3.setGroundWaterMeasurement("{}");

		discreteGroundWater4 =  new DiscreteGroundWater();
		discreteGroundWater4.setFieldVisitIdentifier("2a618902-18ad-4b5f-b0fe-36b7eb2071f7");
		discreteGroundWater4.setLocationIdentifier(LOCATION_IDENTIFIER_1);
		discreteGroundWater4.setAgencyCode("USGS");
		discreteGroundWater4.setStartTime(Timestamp.valueOf("2017-10-30 08:00:00"));
		discreteGroundWater4.setEndTime(Timestamp.valueOf("2017-10-31 07:59:59"));
		discreteGroundWater4.setParty("GMENDE");
		discreteGroundWater4.setRemarks("");
		discreteGroundWater4.setWeather(null);
		discreteGroundWater4.setIsValidHeaderInfo("true");
		discreteGroundWater4.setCompletedWork("{\"LevelsPerformed\": false, \"CollectionAgency\": \"USGS\", \"OtherSampleTaken\": false, \"SedimentSampleTaken\": false, \"BiologicalSampleTaken\": false, \"RecorderDataCollected\": false, \"WaterQualitySampleTaken\": false, \"GroundWaterLevelPerformed\": true, \"SafetyInspectionPerformed\": false}");
		discreteGroundWater4.setLastModified(Timestamp.valueOf("2020-05-11 04:20:42.246364"));
		discreteGroundWater4.setParameter("Water level, depth MP");
		discreteGroundWater4.setParmCd("61055");
		discreteGroundWater4.setMonitoringMethod("GW level, calib electric tape");
		discreteGroundWater4.setFieldVisitValue("11.36");
		discreteGroundWater4.setUnit("ft");
		discreteGroundWater4.setUncertainty("0.01");
		discreteGroundWater4.setReadingType("ReferencePrimary");
		discreteGroundWater4.setManufacturer("Unspecified");
		discreteGroundWater4.setModel("Unspecified");
		discreteGroundWater4.setSerialNumber("SDP.ET.500.03");
		discreteGroundWater4.setFieldVisitTime(Timestamp.valueOf("2017-10-30 18:08:00"));
		discreteGroundWater4.setFieldVisitComments("Source: Reporting Agency");
		discreteGroundWater4.setPublish("true");
		discreteGroundWater4.setIsValidReadings("true");
		discreteGroundWater4.setReferencePointUniqueId("e42ffa2b69a3488aa8567a7e0c31e8cc");
		discreteGroundWater4.setUseLocationDatumAsReference("false");
		discreteGroundWater4.setReadingQualifier("Static");
		discreteGroundWater4.setReadingQualifiers("[\"Static\"]");
		discreteGroundWater4.setGroundWaterMeasurement("{}");
	}
}
