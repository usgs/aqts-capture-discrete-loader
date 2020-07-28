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

	public static final String INSERT_SUCCEEDED_MESSAGE = "Successfully inserted gw levels with monitoring location identifier: %s";
	public static final String INSERT_FAILED_MESSAGE = "Selected row count: %s and inserted row count: %s differ, insert failed for monitoring location identifier: %s";

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

		String locationIdentifier = request.getLocationIdentifier();
		LOG.debug("the request object location id: {}", locationIdentifier);

		ResultObject result = new ResultObject();

		// get all the records for this location identifier from the transform db
		List<DiscreteGroundWater> discreteGroundWaterList = transformDao.getDiscreteGroundWater(locationIdentifier);

		int rowsInserted = 0;

		// if we do not get records back from the transform db, don't try to load anything to the observation db
		if (discreteGroundWaterList.size() > 0) {
			rowsInserted = loadDiscreteGroundWaterIntoObservationDb(discreteGroundWaterList);
		}

		result.setCount(rowsInserted);

		return result;
	}

	@Transactional
	public int loadDiscreteGroundWaterIntoObservationDb (List<DiscreteGroundWater> discreteGroundWaterList) {
		// first delete existing discrete gw levels from observation db using the monitoring location identifier
		String monitoringLocationIdentifier = discreteGroundWaterList.get(0).getMonitoringLocationIdentifier();
		observationDao.deleteDiscreteGroundWater(monitoringLocationIdentifier);

		// insert discrete gw levels into observation db
		int count = observationDao.insertDiscreteGroundWater(discreteGroundWaterList);

		if (count == discreteGroundWaterList.size()) {
			LOG.debug(String.format(INSERT_SUCCEEDED_MESSAGE, monitoringLocationIdentifier));
		} else {
			String failMessageInsertFailed = String.format(
					INSERT_FAILED_MESSAGE,
					discreteGroundWaterList.size(),
					count,
					monitoringLocationIdentifier);
			LOG.debug(failMessageInsertFailed);
			throw new RuntimeException(failMessageInsertFailed);
		}
		return count;
	}
}
