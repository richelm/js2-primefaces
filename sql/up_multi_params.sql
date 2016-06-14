use testdb
go

-- drop database object if already exist
if object_id('up_multi_params') is not null
    drop procedure up_multi_params
go

-- create database object
create procedure up_multi_params

/* A sample stored procedure that will return two output paramters. One is the
 * return value and the an output parameter. It also includes one input
 * parameter.
 */

	@color       varchar(10),
  @color_idx   int output
as
  declare
    @procedure_name   longsysname,
    @ret_value         int

select
  @procedure_name = (select name from sysobjects where id = @@procid)

select
  @color_idx = 0,
  @ret_value = 0


if (@color = 'Red')
  select @color_idx = 1
if (@color = 'White')
  select @color_idx = 2
if (@color = 'Blue')
  select @color_idx = 3

if (@color_idx = 0)
  select @ret_value = 9

return @ret_value
go

-- display success/fail message
if object_id('up_multi_params') is not null
  select 'up_multi_params succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'up_multi_params NOT created in ' + @@servername + '.' + db_name()
go
