package gov.usgs.wma.waterdata;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE,
		classes={
			DBTestConfig.class,
			LoadDiscreteGroundWater.class,
			TransformDao.class,
			ObservationDao.class})
@DatabaseSetup(
		connection="transform",
		value="classpath:/testData/transformDb/")
@DatabaseSetup(
		connection="observation",
		value="classpath:/testData/monitoringLocation/")
@ActiveProfiles("it")
public class LoadDiscreteGroundWaterIT extends BaseTestDao {

	@Autowired
	public LoadDiscreteGroundWater loadDiscreteGroundWater;

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/existingData/")
	@ExpectedDatabase(
			connection="observation",
			value="classpath:/testResult/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testInsertNewData() {
		request.setLocationIdentifier(LOCATION_IDENTIFIER_1);
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		assertEquals("USGS-323302117055201", result.getMonitoringLocationIdentifier());
		assertEquals(2, result.getInsertCount());
		assertEquals(0, result.getDeleteCount());
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/afterInsert/")
	@ExpectedDatabase(
			value="classpath:/testResult/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED,
			connection="observation")
	public void testReplaceExistingData() {
		request.setLocationIdentifier(LOCATION_IDENTIFIER_1);
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		assertEquals("USGS-323302117055201", result.getMonitoringLocationIdentifier());
		assertEquals(2, result.getInsertCount());
		assertEquals(2, result.getDeleteCount());
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testData/discreteGWAqtsDbWithExtraRecord/")
	@ExpectedDatabase(
			value="classpath:/testResult/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED,
			connection="observation")
	public void testDeleteRecordsNolongerAssociatedWithMonitoringLocation() {
		request.setLocationIdentifier(LOCATION_IDENTIFIER_1);
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		assertEquals("USGS-323302117055201", result.getMonitoringLocationIdentifier());
		assertEquals(2, result.getInsertCount());
		assertEquals(4, result.getDeleteCount());
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/empty/")
	@ExpectedDatabase(
			connection="observation",
			value="classpath:/testResult/empty/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testNoRecordsFound() {
		request.setLocationIdentifier(BAD_LOCATION_IDENTIFIER);
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		assertNull(result.getMonitoringLocationIdentifier());
		assertEquals(0, result.getInsertCount());
		assertEquals(0, result.getDeleteCount());

		assertDoesNotThrow(() -> {
			loadDiscreteGroundWater.apply(request);
		}, "should not have thrown an exception but did");
	}
}
