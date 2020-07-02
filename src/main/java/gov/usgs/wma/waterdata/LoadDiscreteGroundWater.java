package gov.usgs.wma.waterdata;

import java.util.List;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoadDiscreteGroundWater implements Function<RequestObject, ResultObject> {

	private static final Logger LOG = LoggerFactory.getLogger(LoadDiscreteGroundWater.class);

	public static final String INSERT_SUCCEEDED_MESSAGE = "Successfully inserted gw levels with field visit identifier: %s";
	public static final String INSERT_FAILED_MESSAGE = "Selected row count: %s and inserted row count: %s differ, insert failed for field visit identifier: %s with monitoring location identifier: %s";

	private final TransformDao transformDao;
	private final ObservationDao observationDao;

	@Autowired
	public LoadDiscreteGroundWater(TransformDao transformDao, ObservationDao observationDao) {
		this.transformDao = transformDao;
		this.observationDao = observationDao;
	}

	@Override
	public  ResultObject apply(RequestObject request) {
		return processRequest(request);
	}

	@Transactional
	protected ResultObject processRequest(RequestObject request) {
		List<String> fieldVisitIdentifiers = request.getFieldVisitIdentifiers();
		LOG.debug("the request object: {}", fieldVisitIdentifiers.toString());
		ResultObject result = new ResultObject();

		for (String fieldVisitIdentifier : fieldVisitIdentifiers) {
			// each field visit identifier could have more than one record returned
			List<DiscreteGroundWater> discreteGroundWaterList = transformDao.getDiscreteGroundWater(fieldVisitIdentifier);
			int rowsInserted = loadDiscreteGroundWaterIntoObservationDb(discreteGroundWaterList, fieldVisitIdentifier);
			if (null == result.getCount()) {
				result.setCount(rowsInserted);
			} else {
				result.setCount(result.getCount() + rowsInserted);
			}
		}
		return result;
	}

	@Transactional
	public int loadDiscreteGroundWaterIntoObservationDb (List<DiscreteGroundWater> discreteGroundWaterList, String fieldVisitIdentifier) {
		// first delete existing discrete gw levels from observation db
		observationDao.deleteDiscreteGroundWater(fieldVisitIdentifier);

		// insert discrete gw levels into observation db
		int count = observationDao.insertDiscreteGroundWater(discreteGroundWaterList);

		if (count == discreteGroundWaterList.size()) {
			LOG.debug(String.format(INSERT_SUCCEEDED_MESSAGE, fieldVisitIdentifier));
		} else {
			String failMessageInsertFailed = String.format(
					INSERT_FAILED_MESSAGE,
					discreteGroundWaterList.size(),
					count, fieldVisitIdentifier,
					discreteGroundWaterList.get(0).getMonitoringLocationIdentifier());
			LOG.debug(failMessageInsertFailed);
			throw new RuntimeException(failMessageInsertFailed);
		}
		return count;
	}
}
