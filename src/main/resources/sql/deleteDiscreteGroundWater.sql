delete from
    discrete_ground_water_aqts
where
    /* TODO not sure what the delete key should be on the observations db */
	monitoring_location_identifier = ?
	and date_measured_raw =  ?
  /*
	and date_time_accuracy_code = ?
	and timezone_code = ?
*/
