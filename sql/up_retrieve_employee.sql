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
    @rowcount         int,
    @ret_value        int

select
  @procedure_name = (select name from sysobjects where id = @@procid)

select
  @ret_value = 0

select
  employee_id,
  first_name,
  last_name,
  email,
  start_date,
  sick_days,
  fringe_ratio,
  department_id
from
  employee
where
  employee_id = @employee_id

select
  @error = @@error,
  @rowcount = @@rowcount

  if (@error != 0)
  begin
    rollback transaction
    select @ret_value = @error
    raiserror 99999 'Error: Could not retrieve employee ID: %1!', @employee_id
  end

  if (@rowcount != 1)
  begin
    rollback transaction
    select @ret_value = -100
    raiserror 99999 'Warning: Could find record with employee ID: %1!', @employee_id
  end
  
return @ret_value
go

-- display success/fail message
if object_id('up_retrieve_employee') is not null
  select 'up_retrieve_employee succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'up_retrieve_employee NOT created in ' + @@servername + '.' + db_name()
go
