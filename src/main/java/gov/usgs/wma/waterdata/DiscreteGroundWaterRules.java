package gov.usgs.wma.waterdata;

/**
 * Applies business rules to a domain object.
 * It is assumed that this object can be modified.
 */
public class DiscreteGroundWaterRules {

	/**
	 * Apply business rules to a DiscreteGroundWater, modifying it in place.
	 * @param domObj
	 */
	public void apply(DiscreteGroundWater domObj) {

		//Rule:  The DateTime accuracy when unspecified is MINUTE.
		//Ref:  https://internal.cida.usgs.gov/jira/browse/IOW-652
		if(! DateTimeAccuracy.parseCode(domObj.getDateTimeAccuracyCode()).isReal()) {
			DateTimeAccuracy defDTA = DateTimeAccuracy.MINUTE;

			domObj.setDateTimeAccuracyCode(defDTA.getCode());
			domObj.setDateTimeAccuracyText(defDTA.getText());
		}
	}
}
