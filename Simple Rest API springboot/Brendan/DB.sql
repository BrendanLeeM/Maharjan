#drop table log_in;
create table log_in (
ID int(11) NOT NULL AUTO_INCREMENT, 
Username varchar (250) not null,
Password varchar (250) not null,
Primary key (ID));