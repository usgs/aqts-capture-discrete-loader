package gov.usgs.wma.waterdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import java.util.List;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE,
		classes={
			DBTestConfig.class,
			ObservationDao.class})
@ActiveProfiles("it")
public class ObservationDaoIT extends BaseTestDao {

	@Autowired
	public ObservationDao observationDao;

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/afterInsert/")
	@ExpectedDatabase(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/cleanseOutput/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testDelete() {
		// delete existing data
		Integer actualRowsDeletedCount = observationDao.deleteDiscreteGroundWater(
				List.of(
						discreteGroundWater1
						,discreteGroundWater2
						,discreteGroundWater3
						,discreteGroundWater4));
		assertNotNull(actualRowsDeletedCount);
		assertEquals(4, actualRowsDeletedCount);
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testData/observationDb/")
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/empty/")
	@ExpectedDatabase(
			connection="observation",
			value="classpath:/testResult/observationDb/discreteGroundWaterAQTS/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testInsert() {
		// insert new data
		Integer actualRowsInsertedCount = observationDao.insertDiscreteGroundWater(List.of(
				discreteGroundWater1
				,discreteGroundWater2
				,discreteGroundWater3
				,discreteGroundWater4));
		assertNotNull(actualRowsInsertedCount);
		assertEquals(4, actualRowsInsertedCount);
	}
}
