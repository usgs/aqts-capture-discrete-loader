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

		if (locationIdentifier == null) {
			throw new IllegalArgumentException("The locationIdentifier cannot be null");
		}

		// get all the records for this location identifier from the transform db
		List<DiscreteGroundWater> discreteGroundWaterList = transformDao.getDiscreteGroundWater(locationIdentifier);

		ResultObject result;

		//TODO:  IOW-574 - if we do not get records back from the transform db, we won't know the
		// monitoringLocationIdentifier, so we cannot attempt to do the delete portion of the update.
		if (discreteGroundWaterList.size() > 0) {
			result = updateDiscreteGroundWaterIntoObservationDb(discreteGroundWaterList);
		} else {
			result = new ResultObject();
		}

		return result;
	}

	@Transactional
	public ResultObject updateDiscreteGroundWaterIntoObservationDb(List<DiscreteGroundWater> discreteGroundWaterList) {

		ResultObject result = new ResultObject();

		// first delete existing discrete gw levels from observation db using the monitoring location identifier
		String monitoringLocationIdentifier = discreteGroundWaterList.get(0).getMonitoringLocationIdentifier();
		result.setDeleteCount(
				observationDao.deleteDiscreteGroundWater(monitoringLocationIdentifier)
		);

		// insert discrete gw levels into observation db
		result.setInsertCount(
				observationDao.insertDiscreteGroundWater(discreteGroundWaterList)
		);

		if (result.getInsertCount() == discreteGroundWaterList.size()) {
			LOG.debug(String.format(INSERT_SUCCEEDED_MESSAGE, monitoringLocationIdentifier));
		} else {
			String failMessageInsertFailed = String.format(
					INSERT_FAILED_MESSAGE,
					discreteGroundWaterList.size(),
					result.getInsertCount(),
					monitoringLocationIdentifier);
			throw new RuntimeException(failMessageInsertFailed);
		}

		return result;
	}
}
