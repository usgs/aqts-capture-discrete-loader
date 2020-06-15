package gov.usgs.wma.waterdata;

import java.sql.Date;

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

	public static final String FIELD_VISIT_IDENTIFIER = "17f83e62b06e4dc29e78d96b4426a255";
	public static final String BAD_FIELD_VISIT_IDENTIFIER = "badDiscreteGroundWaterUniqueId";
	public RequestObject request;
	public DiscreteGroundWater discreteGroundWater1;
	public DiscreteGroundWater discreteGroundWater2;
	public DiscreteGroundWater discreteGroundWater3;

	@BeforeEach
	public void setup() {
		request = new RequestObject();
		request.setId(FIELD_VISIT_IDENTIFIER);

//		String approvals = "[\"Approved\"]";
//		String grades = "[\"50\"]";
//		String qualifiers = null;

		discreteGroundWater1 = new DiscreteGroundWater();
//		discreteGroundWater1.setGroundwaterDailyValueIdentifier("USGS-132624144452771-17f83e62b06e4dc29e78d96b4426a255");
//		discreteGroundWater1.setDiscreteGroundWaterUniqueId("17f83e62b06e4dc29e78d96b4426a255");
//		discreteGroundWater1.setMonitoringLocationIdentifier("USGS-132624144452771");
//		discreteGroundWater1.setObservedPropertyId("62610");
//		discreteGroundWater1.setStatisticId("00001");
//		discreteGroundWater1.setTimeStep(Date.valueOf("2008-06-03"));
//		discreteGroundWater1.setUnitOfMeasure("ft");
//		discreteGroundWater1.setResult("36.02");
//		discreteGroundWater1.setApprovals(approvals);
//		discreteGroundWater1.setQualifiers(qualifiers);
//		discreteGroundWater1.setGrades(grades);

		discreteGroundWater2 = new DiscreteGroundWater();
//		discreteGroundWater2.setGroundwaterDailyValueIdentifier("USGS-132624144452771-17f83e62b06e4dc29e78d96b4426a255");
//		discreteGroundWater2.setDiscreteGroundWaterUniqueId("17f83e62b06e4dc29e78d96b4426a255");
//		discreteGroundWater2.setMonitoringLocationIdentifier("USGS-132624144452771");
//		discreteGroundWater2.setObservedPropertyId("62610");
//		discreteGroundWater2.setStatisticId("00001");
//		discreteGroundWater2.setTimeStep(Date.valueOf("2008-06-04"));
//		discreteGroundWater2.setUnitOfMeasure("ft");
//		discreteGroundWater2.setResult("35.96");
//		discreteGroundWater2.setApprovals(approvals);
//		discreteGroundWater2.setQualifiers(qualifiers);
//		discreteGroundWater2.setGrades(grades);

		discreteGroundWater3 =  new DiscreteGroundWater();
//		discreteGroundWater3.setGroundwaterDailyValueIdentifier("USGS-132624144452771-17f83e62b06e4dc29e78d96b4426a255");
//		discreteGroundWater3.setDiscreteGroundWaterUniqueId("17f83e62b06e4dc29e78d96b4426a255");
//		discreteGroundWater3.setMonitoringLocationIdentifier("USGS-132624144452771");
//		discreteGroundWater3.setObservedPropertyId("62610");
//		discreteGroundWater3.setStatisticId("00001");
//		discreteGroundWater3.setTimeStep(Date.valueOf("2008-06-05"));
//		discreteGroundWater3.setUnitOfMeasure("ft");
//		discreteGroundWater3.setResult("35.91");
//		discreteGroundWater3.setApprovals(approvals);
//		discreteGroundWater3.setQualifiers(qualifiers);
//		discreteGroundWater3.setGrades(grades);
	}
}
