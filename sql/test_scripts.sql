use testdb

select * from employee


select * from department

-- test for up_retrieve_employee
declare 
    @employee_id    numeric(7,0),
    @rval           int

select @employee_id = 55
execute @rval = up_retrieve_employee @employee_id = @employee_id
select 
    @rval as 'return_value'
