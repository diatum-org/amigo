alter table account add column create_timestamp bigint(64);

create index name_index on account(name);
create index handle_index on account(handle);
create index location_index on account(location);
create index description_index on account(description);
create index gps_index on account(gps);
create index gps_longitude_index on account(gps_longitude);
create index gps_latitude_index on account(gps_latitude);
create index gps_altitude_index on account(gps_altitude);
create index gps_timestamp_index on account(gps_timestamp);

