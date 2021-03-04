alter table account add column create_timestamp bigint(64);

alter table account drop column password;
alter table account drop column salt;
alter table account drop column phone_number;
alter table account drop column confirmed_phone;
alter table account drop column email_address;
alter table account drop column confirmed_email;
alter table account drop column reset_token;
alter table account drop column reset_issued;
alter table account drop column reset_expires;

drop table pass;

create index name_index on account(name);
create index handle_index on account(handle);
create index location_index on account(location);
create index description_index on account(description);
create index gps_index on account(gps);
create index gps_longitude_index on account(gps_longitude);
create index gps_latitude_index on account(gps_latitude);
create index gps_altitude_index on account(gps_altitude);
create index gps_timestamp_index on account(gps_timestamp);

