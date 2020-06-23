insert
into discrete_ground_water_aqts(monitoring_location_id, agency_code, agency, site_identification_number, monitoring_location_identifier,
                                site_type_code, site_type, geom, decimal_latitude, decimal_longitude, decimal_latitude_longitude_datum,
                                well_depth, hole_depth, local_aquifer, local_aquifer_type, date_measured_raw,
                                timezone_code, timezone_offset, parameter_code, date_measured,
                                time_measured_utc, level_feet_below_land_surface, level_feet_above_vertical_datum,
                                vertical_datum_code, vertical_datum, site_status_code, site_status,
                                measuring_agency_code, measuring_agency, date_time_accuracy_code, date_time_accuracy,
                                level_accuracy_code, level_accuracy, measurement_source_code, measurement_source,
                                measurement_method_code, measurement_method, approval_status_code, approval_status)
select monitoring_location.monitoring_location_id,
       monitoring_location.agency_cd agency_code,
       monitoring_location.agency,
       monitoring_location.site_identification_number,
       monitoring_location.monitoring_location_identifier,
       monitoring_location.site_tp_cd site_type_code,
       monitoring_location.site_type,
       monitoring_location.geom,
       monitoring_location.decimal_latitude,
       monitoring_location.decimal_longitude,
       monitoring_location.decimal_latitude_longitude_datum,
       monitoring_location.well_depth,
       monitoring_location.hole_depth,
       monitoring_location.local_aquifer,
       monitoring_location.local_aquifer_type,
       /* TODO everything below comes from AQTS */
       ? date_measured_raw,
       'UTC' timezone_code, /* this can't be null but isn't explicity defined. Is everything from AQTS in utc? */
       null timezone_offset, /* this may not come in with many of the measurement times */
       ? parameter_code,
       ?::date date_measured,
       ?::time time_measured_utc, /* 8 character limit and just the time, but we need an offset to calculate it, do we have the offset?*/
       null level_feet_below_land_surface,
       ? level_feet_above_vertical_datum,  /* right now param code 61055 is our only parm, which is above datum */
       null vertical_datum_code,
       null vertical_datum,
       null site_status_code,
       null site_status,
       ? measuring_agency_code,
       null measuring_agency,
       null date_time_accuracy_code,
       null date_time_accuracy,
       null level_accuracy_code,
       ? level_accuracy, /* uncertainty */
       null measurement_source_code,
       null measurement_source,
       null measurement_method_code,
       ? measurement_method,
       null approval_status_code,
       null approval_status
from monitoring_location
	where monitoring_location.monitoring_location_identifier = ?;
