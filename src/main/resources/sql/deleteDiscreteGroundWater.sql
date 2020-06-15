delete from
    discrete_ground_water_aqts
where
    /* TODO not sure what the delete key should be on the observations db */
    identifier = ?
