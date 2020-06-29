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
				discreteGroundWater2,
				discreteGroundWater3);
	}

	@Test
	public void testGet() {
		// get new data, return list of discrete gw objects
		request.setFieldVisitIdentifiers(
				List.of(
					FIELD_VISIT_IDENTIFIER_1,
					FIELD_VISIT_IDENTIFIER_2,
					FIELD_VISIT_IDENTIFIER_3));
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(request.getFieldVisitIdentifiers());
		assertNotNull(actualData);
		assertEquals(discreteGroundWaterList, actualData);
	}

	@Test
	public void testGetIncludingOneBadIdentifier() {
		// get new data, return list of discrete gw objects, bad identifier should not affect query outcome
		request.setFieldVisitIdentifiers(
				List.of(
					FIELD_VISIT_IDENTIFIER_1,
					FIELD_VISIT_IDENTIFIER_2,
					FIELD_VISIT_IDENTIFIER_3,
					BAD_FIELD_VISIT_IDENTIFIER));
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(request.getFieldVisitIdentifiers());
		assertNotNull(actualData);
		assertEquals(discreteGroundWaterList, actualData);
	}

	@Test
	public void testNotFound() {
		// try to get data using a bad identifier
		List<DiscreteGroundWater> expectedDiscreteGroundWaterList = Collections.emptyList();
		request.setFieldVisitIdentifiers(List.of(BAD_FIELD_VISIT_IDENTIFIER));
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(request.getFieldVisitIdentifiers());
		assertEquals(expectedDiscreteGroundWaterList, actualData);
	}
}