insert
into discrete_ground_water_aqts(monitoring_location_id, agency_code, agency, site_identification_number, monitoring_location_identifier,
                                district_cd, site_type_code, site_type, geom, decimal_latitude, decimal_longitude, decimal_latitude_longitude_datum,
                                well_depth, hole_depth, local_aquifer, local_aquifer_type, field_visit_identifier,date_measured_raw,
                                timezone_code, timezone_offset, parameter_code, date_measured,
                                time_measured_utc, display_result,
                                vertical_datum_code, vertical_datum, site_status_code, site_status,
                                measuring_agency_code, measuring_agency, date_time_accuracy_code, date_time_accuracy,
                                level_accuracy_code, level_accuracy, measurement_source_code, measurement_source,
                                measurement_method_code, measurement_method, approval_level, approval_level_description,
                                result_measure_qualifiers)
select null monitoring_location_id,
       sitefile.agency_cd agency_code,
       agency.name agency,
       sitefile.site_no site_identification_number,
       coalesce(sitefile.agency_cd, '') || '-' || coalesce(sitefile.site_no, '') monitoring_location_identifier,
       sitefile.district_cd district_cd,
       sitefile.site_tp_cd site_type_code,
       site_tp.site_tp_nm site_type,
       null geom,
       sitefile.dec_lat_va decimal_latitude,
       sitefile.dec_long_va decimal_longitude,
       decimal_lat_long_datum.description decimal_latitude_longitude_datum,
       case when sitefile.well_depth_va in ('.', '-') then '0' else sitefile.well_depth_va end well_depth,
       case when sitefile.hole_depth_va in ('.', '-') then '0' else sitefile.hole_depth_va end hole_depth,
       aqfr.aqfr_nm local_aquifer,
       aquifer_type.description local_aquifer_type,
       /* TODO everything below comes from AQTS */
       ? field_visit_identifier,
       ? date_measured_raw,
       'UTC' timezone_code, /* Everything from AQTS in UTC */
       null timezone_offset,
       ? parameter_code,
       ?::date date_measured,
       ?::time time_measured_utc,
       ? display_result,
       ? vertical_datum_code,
       null vertical_datum,
       null site_status_code,
       null site_status,
       ? measuring_agency_code,
       null measuring_agency,
       ? date_time_accuracy_code,
       ? date_time_accuracy,
       null level_accuracy_code,
       ? level_accuracy, /* uncertainty */
       /* This will come in through the comments field as something like Source: Reporting Agency */
       null measurement_source_code,
       null measurement_source,
       ? measurement_method_code,
       ? measurement_method,
       ? approval_level,
       ? approval_level_description,
       ?::json result_measure_qualifiers
from sitefile
left join nwis.agency
         on sitefile.agency_cd = agency.code
left join nwis.site_tp
         on sitefile.site_tp_cd = site_tp.site_tp_cd
left join nwis.aqfr
         on sitefile.aqfr_cd = aqfr.aqfr_cd and
            sitefile.country_cd = aqfr.country_cd and
            sitefile.state_cd = aqfr.state_cd
left join nwis.aquifer_type
         on sitefile.aqfr_type_cd = aquifer_type.code
left join nwis.lat_long_datum decimal_lat_long_datum
         on sitefile.dec_coord_datum_cd = decimal_lat_long_datum.code
where agency_cd = ? and site_no = ?
order by agency_cd, site_no;
