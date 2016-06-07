/*
 * employee table
 */
use testdb
go

-- drop database object if already exist
if object_id('employee') is not null
  drop table employee
go

-- create the table
create table employee (
  employee_id     numeric(7,0) identity,
  first_name      varchar(40),
  last_name       varchar(40),
  email           varchar(60),
  start_date      date,
  sick_days       int,
  fringe_ratio    numeric(5,2)
)
go

-- load with inital values for testing
insert into employee values("Buggs","Bunny","bbunny@nowhere","7/19/1995",27,1.75)
insert into employee values("Daffy","Duck","duck@nowhere","12/15/2001",35,1.75)
insert into employee values("Elmer","Fudd","fudd@nowhere","8/28/1988",50,2.35)
insert into employee values("Yosemite","Sam","samy@nowhere","3/15/2003",11,1.25)
insert into employee values("Foghorn","Leghorn","rooster@nowhere","3/15/2003",23,1.75)
go

-- display success/fail message
if object_id('employee') is not null
  select 'employee succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'employee NOT created in ' + @@servername + '.' + db_name()
go
