use testdb
go

-- drop database object if already exist
if object_id('up_retrieve_employee') is not null
    drop procedure up_retrieve_employee
go

-- create database object
create procedure up_retrieve_employee
-- A sample stored procedure that will return a result set and a return value.

  @employee_id   int
as
  declare
    @procedure_name   longsysname,
    @error            int,
    @ret_value        int

select
  @procedure_name = (select name from sysobjects where id = @@procid)

select
  @ret_value = @employee_id

select
  employee_id,
  first_name,
  last_name,
  email,
  start_date,
  sick_days,
  salary
from
  employee
where
  employee_id = @employee_id

select
  @error = @@error

  if (@error != 0)
    select @ret_value = @error


return @ret_value
go

-- display success/fail message
if object_id('up_retrieve_employee') is not null
  select 'up_retrieve_employee succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'up_retrieve_employee NOT created in ' + @@servername + '.' + db_name()
go
