package gov.usgs.wma.waterdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

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
		discreteGroundWaterList = new ArrayList<>();
		discreteGroundWaterList.add(discreteGroundWater1);
		discreteGroundWaterList.add(discreteGroundWater2);
		discreteGroundWaterList.add(discreteGroundWater3);
	}

	@Test
	public void testGet() {
		// get new data, return unique ids
		request.setFieldVisitIdentifiers(Arrays.asList(
				FIELD_VISIT_IDENTIFIER_1
				,FIELD_VISIT_IDENTIFIER_2
				,FIELD_VISIT_IDENTIFIER_3));
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(request.getFieldVisitIdentifiers());
		assertNotNull(actualData);
		assertEquals(discreteGroundWaterList, actualData);
	}

	@Test
	public void testNotFound() {
		// try to get data using a bad identifier
		List<DiscreteGroundWater> expectedDiscreteGroundWaterList = new ArrayList();
		request.setFieldVisitIdentifiers(List.of(BAD_FIELD_VISIT_IDENTIFIER));
		List<DiscreteGroundWater> actualData = transformDao.getDiscreteGroundWater(request.getFieldVisitIdentifiers());
		assertEquals(expectedDiscreteGroundWaterList, actualData);
	}
}