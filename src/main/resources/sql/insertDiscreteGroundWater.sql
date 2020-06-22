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
       /* TODO everything from gw_levels will be coming from the discreteGroundWaterObject/AQTS side rather than the gw_levels table*/
       ? date_measured_raw,
       null timezone_code,
       null timezone_offset,
       ? parameter_code,
       ?::date date_measured, /* 10 character limit in observation db table, needs to be a date, not a timestamp */
       null time_measured_utc, /* 8 character limit and just the time, but we need an offset to calculate it, do we have the offset?*/
       ? level_feet_below_land_surface, /* This is coming in as a json string (numeric: blah, display: blah) - need to parse out just the display value - also we don't have a datum to make this calc... */ */
       ? level_feet_above_vertical_datum,  /* This is coming in as a json string (numeric: blah, display: blah) - need to parse out just the display value - also we don't have a datum to make this calc... */
       null vertical_datum_code,
       null vertical_datum,
       null site_status_code,
       null site_status,
       null measuring_agency_code,
       null measuring_agency,
       null date_time_accuracy_code,
       null date_time_accuracy,
       null level_accuracy_code,
       ? level_accuracy, /* This is coming in as a json string (numeric: blah, display: blah) - need to parse out just the display value */
       null measurement_source_code,
       null measurement_source,
       null measurement_method_code,
       ? measurement_method,
       null approval_status_code,
       null approval_status
from monitoring_location;
