select
    field_visit_identifier,
	(regexp_match(location_identifier, '(\d*)-*(.*)'))[1] location_identifier, /*  This is the numeric portion of the aqts location identifier */
	coalesce(nullif((regexp_match(location_identifier, '(\d*)-*(.*)'))[2], ''), 'USGS') agency_code, /* If there is a hyphen followed by any other text at the end of the identifier number, use it or default to USGS  */
    start_time,
    end_time,
    party,
    remarks,
    weather,
    is_valid_header_info,
    completed_work,
    last_modified,
    parameter,
    parm_cd,
    monitoring_method,
    field_visit_value,
    unit,
    uncertainty,
    reading_type,
    manufacturer,
    model,
    serial_number,
    field_visit_time,
    field_visit_comments,
    publish,
    is_valid_readings,
    reference_point_unique_id,
    use_location_datum_as_reference,
    reading_qualifier,
    reading_qualifiers,
    ground_water_measurement
from
    discrete_ground_water
where
    field_visit_identifier in (:ids)