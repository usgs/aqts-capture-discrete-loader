package gov.usgs.wma.waterdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE,
		classes={
			DBTestConfig.class,
			TransformDao.class})
@DatabaseSetup("classpath:/testData/transformDb/")
@ActiveProfiles("it")
public class TransformDaoIT extends BaseTestDao {

	@Autowired
	public TransformDao transformDao;
	List<DiscreteGroundWater> discreteGroundWaterList;

	@BeforeEach
	public void setupTransformDaoIT() {
		discreteGroundWaterList = List.of(
				discreteGroundWater1,
				discreteGroundWater4,
				discreteGroundWater5,
				discreteGroundWater6);
	}

	@Test
	public void testGet() {
		// get new data, return list of discrete gw objects
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(LOCATION_IDENTIFIER_1);
		assertNotNull(actualData);
		assertEquals(discreteGroundWaterList, actualData);
	}

	@Test
	public void testNotFound() {
		// try to get data using a bad identifier
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(BAD_LOCATION_IDENTIFIER);
		assertEquals(Collections.emptyList(), actualData);
	}

	@Test
	public void testGetWithNull() {
		// get new data, return list of discrete gw objects
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(null);
		assertEquals(Collections.emptyList(), actualData);
	}
}
