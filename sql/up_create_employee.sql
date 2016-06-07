use testdb
go

-- drop database object if already exist
if object_id('up_create_employee') is not null
    drop procedure up_create_employee
go

-- create database object
create procedure up_create_employee
-- A stored procedure that will create record in employee table.
    @first_name      varchar(40),
    @last_name       varchar(40),
    @email           varchar(60),
    @start_date      date,
    @sick_days       int,
    @fringe_ratio    numeric(5,2)

as
  declare
    @procedure_name   longsysname,
    @error            int,
    @rowct            int

-- get name of currently executing stored procedure
select
  @procedure_name = (select name from sysobjects where id = @@procid)

begin transaction

insert into dbo.employee
  (first_name, last_name, email, start_date, sick_days, fringe_ratio)
  values(@first_name, @last_name, @email, @start_date, @sick_days, @fringe_ratio)

select
  @error = @@error,
  @rowct = @@rowcount

if ((@error != 0 ) or (@rowct != 1))
begin
  rollback transaction
  raiserror 99999 'Error: Record not saved for %1!, %2! in %3!',
    @last_name, @first_name, @procedure_name
end
else
  commit work

return
go

-- display success/fail message
if object_id('up_create_employee') is not null
  select 'up_create_employee succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'up_create_employee NOT created in ' + @@servername + '.' + db_name()
go
