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

		//Rule:  The DateTime accuracy is read from a standardized format in the field_visit_comment.
		//If the unrecognized or unspecified, it is assumed to be MINUTE.
		//Ref:  https://internal.cida.usgs.gov/jira/browse/IOW-558 (read from comment)
		//Ref:  https://internal.cida.usgs.gov/jira/browse/IOW-652 (default to MINUTE)
		{
			DateTimeAccuracy dta = DateTimeAccuracy.parse(domObj.getFieldVisitComments());

			if (dta.isReal()) {

				domObj.setDateTimeAccuracyCode(dta.getCode());
				domObj.setDateTimeAccuracyText(dta.getText());
			} else {

				dta = DateTimeAccuracy.MINUTE;
				domObj.setDateTimeAccuracyCode(dta.getCode());
				domObj.setDateTimeAccuracyText(dta.getText());
			}
		}
	}
}
