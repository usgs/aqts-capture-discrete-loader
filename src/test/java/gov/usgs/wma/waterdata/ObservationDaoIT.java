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
			value="classpath:/testResult/afterInsert/")
	@ExpectedDatabase(
			connection="observation",
			value="classpath:/testResult/afterDelete/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testDelete() {
		// delete existing data
		Integer actualRowsDeletedCount = observationDao.deleteDiscreteGroundWater(discreteGroundWater1.getMonitoringLocationIdentifier());
		assertNotNull(actualRowsDeletedCount);
		assertEquals(4, actualRowsDeletedCount);
	}

	@Test
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testData/monitoringLocation/")
	@DatabaseSetup(
			connection="observation",
			value="classpath:/testResult/empty/")
	@ExpectedDatabase(
			connection="observation",
			value="classpath:/testResult/afterInsert/",
			assertionMode= DatabaseAssertionMode.NON_STRICT_UNORDERED)
	public void testInsert() {
		// insert new data
		Integer actualRowsInsertedCount = observationDao.insertDiscreteGroundWater(List.of(
				discreteGroundWater1,
				discreteGroundWater2,
				discreteGroundWater3,
				discreteGroundWater4,
				discreteGroundWater5,
				discreteGroundWater6));
		assertNotNull(actualRowsInsertedCount);
		assertEquals(6, actualRowsInsertedCount);
	}
}
