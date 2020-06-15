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

	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_FAIL = "fail";
	public static final String STATUS_SUCCESS_MESSAGE = "Successfully inserted gw levels with id: %s";
	public static final String MESSAGE_NO_RECORDS = "No records found for gw levels id: %s";
	public static final String FAIL_MESSAGE_NULL_UNIQUE_ID = "gw levels id was null";
	public static final String FAIL_MESSAGE_INSERT_FAILED = "Selected row count: %s and inserted row count: %s differ, insert failed for gw levels id: %s";

	private TransformDao transformDao;
	private ObservationDao observationDao;

	@Autowired
	public LoadDiscreteGroundWater(TransformDao transformDao, ObservationDao observationDao) {
		this.transformDao = transformDao;
		this.observationDao = observationDao;
	}

	@Override
	public  ResultObject apply(RequestObject request) {
		ResultObject result = processRequest(request);
		if (STATUS_FAIL.equalsIgnoreCase(result.getStatus())) {
			throw new RuntimeException(result.getFailMessage());
		} else {
			return result;
		}
	}

	protected ResultObject processRequest(RequestObject request) {

		// TODO again not sure which id this should be yet
		String fieldVisitIdentifier = request.getId();
		ResultObject result = new ResultObject();

		// don't interact with the databases if the incoming id null
		if (null != fieldVisitIdentifier) {

			// get gw levels from transform db
			List<DiscreteGroundWater> discreteGroundWaterList = transformDao.getDiscreteGroundWater(fieldVisitIdentifier);

			if (0 == discreteGroundWaterList.size()) {
				// do not try to delete or insert rows if no data is returned from the get
				// This is not an error situation - there are no gw levels associated with this identifier yet
				result.setStatus(STATUS_SUCCESS);
				LOG.info(String.format(MESSAGE_NO_RECORDS, fieldVisitIdentifier));
			} else {
				// otherwise, try to insert new gw levels or replace existing ones
				loadDiscreteGroundWaterIntoObservationDb(discreteGroundWaterList, result, fieldVisitIdentifier);
			}
		} else {
			result.setStatus(STATUS_FAIL);
			result.setFailMessage(FAIL_MESSAGE_NULL_UNIQUE_ID);
			LOG.debug(FAIL_MESSAGE_NULL_UNIQUE_ID);
		}
		return result;
	}

	@Transactional
	public void loadDiscreteGroundWaterIntoObservationDb (List<DiscreteGroundWater> discreteGroundWaterList, ResultObject result, String discreteGroundWaterUniqueId) {
		// first delete existing discrete gw levels from observation db
		observationDao.deleteDiscreteGroundWater(discreteGroundWaterUniqueId);

		// insert discrete gw levels into observation db
		int count = observationDao.insertDiscreteGroundWater(discreteGroundWaterUniqueId, discreteGroundWaterList);
		result.setCount(count);

		if (count == discreteGroundWaterList.size() && count != 0) {
			result.setStatus(STATUS_SUCCESS);
			LOG.debug(String.format(STATUS_SUCCESS_MESSAGE, discreteGroundWaterUniqueId));
		} else {
			result.setStatus(STATUS_FAIL);
			String failMessageInsertFailed = String.format(FAIL_MESSAGE_INSERT_FAILED, discreteGroundWaterList.size(), count, discreteGroundWaterUniqueId);
			result.setFailMessage(failMessageInsertFailed);
			LOG.debug(failMessageInsertFailed);
		}
	}
}
