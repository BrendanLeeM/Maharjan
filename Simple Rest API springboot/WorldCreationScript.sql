drop table worlds;
create table worlds (
ID int (11) not null auto_increment,
wname varchar (250),
species varchar (250),
resources varchar (250),
wowner varchar (250),
Primary key (ID));