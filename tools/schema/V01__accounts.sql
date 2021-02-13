drop table db_node;
alter table config drop column name;

alter table system add column accounts bigint(64) not null default 0;

alter table account add column reset_token varchar(64);
alter table account add constraint reset_UNIQUE unique (reset_token);
alter table account add column reset_issued bigint(64);
alter table account add column reset_expires bigint(64);

alter table account change email_address email_address varchar(128) default null;
alter table account change phone_number phone_number varchar(128) default null;

alter table account add constraint email_UNIQUE unique (email_address);
alter table account add constraint phone_UNIQUE unique (phone_number);

create table pass (id int not null auto_increment, parent_id int not null, child_id int default null, token varchar(64) not null, issued bigint(64) not null, expires bigint(64) not null, primary key (id), unique index id_UNIQUE (id asc), unique index token_UNIQUE (token asc), constraint parent_key foreign key (parent_id) references account (id) on delete no action on update no action, constraint child_key foreign key (child_id) references account (id) on delete no action on update no action);

create table confirm (id int not null auto_increment, account_id int not null, token varchar(64) not null, email varchar(256) default null, phone varchar(256) default null, issued bigint(64) not null, expires bigint(64) not null, primary key (id), unique index id_UNIQUE (id asc), unique index token_UNIQUE (token asc), constraint account_key foreign key (account_id) references account (id) on delete no action on update no action);

create table log (id int not null auto_increment, level varchar(32) not null, message varchar(4096) not null, timestamp bigint(64) not null, primary key (id), unique index id_UNIQUE (id asc));

