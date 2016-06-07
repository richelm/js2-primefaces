use testdb
go

-- drop database object if already exist
if object_id('up_raise_error') is not null
    drop procedure up_raise_error
go

-- create database object
create procedure up_raise_error

/* A sample stored procedure that you can force to raise and error. Use with
 * Java/Groovy example programs to catch the raised error.
 */

	@my_num     int

as
  declare
    @procedure_name   longsysname

select
  @procedure_name = (select name from sysobjects where id = @@procid)

if (@my_num = 7)
begin
  raiserror 99999 'Error occurred in %1!', @procedure_name
  return 100
end

return @my_num
go

-- display success/fail message
if object_id('up_raise_error') is not null
  select 'up_raise_error succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'up_raise_error NOT created in ' + @@servername + '.' + db_name()
go
