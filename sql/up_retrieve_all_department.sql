use testdb
go

-- drop database object if already exist
if object_id('up_retrieve_all_department') is not null
    drop procedure up_retrieve_all_department
go

-- create database object
create procedure up_retrieve_all_department
-- A sample stored procedure that will return a result set and a return value.

as
  declare
    @procedure_name   longsysname,
    @error            int,
    @ret_value        int

select
  @procedure_name = (select name from sysobjects where id = @@procid)

select
  department_id,
  department_code,
  department_name

from
  department
order by
  department_name

select
  @error = @@error,
  @ret_value = 0

  if (@error != 0)
    select @ret_value = @error

return @ret_value
go

-- display success/fail message
if object_id('up_retrieve_all_department') is not null
  select 'up_retrieve_all_department succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'up_retrieve_all_department NOT created in ' + @@servername + '.' + db_name()
go
