package gov.usgs.wma.waterdata;

import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import java.util.List;

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
		value="classpath:/testData/observationDb/")
@ActiveProfiles("it")
public class LoadDiscreteGroundWaterIT extends BaseTestDao {

	@Autowired
	public LoadDiscreteGroundWater loadDiscreteGroundWater;

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/empty/")
	@ExpectedDatabase(
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED,
			connection="observation")
	public void testInsert() {
		request.setFieldVisitIdentifiers(List.of(
				FIELD_VISIT_IDENTIFIER_1
				,FIELD_VISIT_IDENTIFIER_2
				,FIELD_VISIT_IDENTIFIER_3
				,FIELD_VISIT_IDENTIFIER_4));
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		Integer expectedCount = 4;
		assertEquals(expectedCount, result.getCount());
		assertEquals(LoadDiscreteGroundWater.STATUS_SUCCESS, result.getStatus());
		assertNull(result.getFailMessage());
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/afterInsert/")
	@ExpectedDatabase(
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED,
			connection="observation")
	public void testReplace() {
		request.setFieldVisitIdentifiers(List.of(
				FIELD_VISIT_IDENTIFIER_1
				,FIELD_VISIT_IDENTIFIER_2
				,FIELD_VISIT_IDENTIFIER_3
				,FIELD_VISIT_IDENTIFIER_4));
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		Integer expectedCount = 4;
		assertEquals(expectedCount, result.getCount());
		assertEquals(LoadDiscreteGroundWater.STATUS_SUCCESS, result.getStatus());
		assertNull(result.getFailMessage());
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/empty/")
	@ExpectedDatabase(
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/empty/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED,
			connection="observation")
	public void testNoRecordsFound() {
		request.setFieldVisitIdentifiers(List.of(BAD_FIELD_VISIT_IDENTIFIER));
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		assertNull(result.getCount());
		assertEquals(LoadDiscreteGroundWater.STATUS_SUCCESS, result.getStatus());
		assertDoesNotThrow(() -> {
			loadDiscreteGroundWater.apply(request);
		}, "should have thrown an exception but did not");
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/empty/")
	@ExpectedDatabase(
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/empty/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED,
			connection="observation")
	public void testNullUniqueId() {
		request.setFieldVisitIdentifiers(null);
		ResultObject result = loadDiscreteGroundWater.processRequest(request);
		assertNull(result.getCount());
		assertEquals(LoadDiscreteGroundWater.STATUS_FAIL, result.getStatus());
		assertEquals(LoadDiscreteGroundWater.FAIL_MESSAGE_NULL_IDENTIFIER, result.getFailMessage());
		assertThrows(RuntimeException.class, () -> {
			loadDiscreteGroundWater.apply(request);
		}, "should have thrown an exception but did not");
	}
}
