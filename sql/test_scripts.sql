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

-- test for up_update_employee
declare
    @employee_id     numeric(7,0),
    @first_name      varchar(40),
    @last_name       varchar(40),
    @email           varchar(60),
    @start_date      date,
    @sick_days       int,
    @fringe_ratio    numeric(5,2),
    @department_id   numeric(7,0),
    @rval            int

select 
    @employee_id = 6,
    @first_name = 'Shawn',
    @last_name = 'Bunny',
    @email = 'shawnb@nowhere.org',
    @start_date = '7/15/2016',
    @sick_days = 20,
    @fringe_ratio = 1.45,
    @department_id = 20

execute @rval = up_update_employee 
    @employee_id, 
    @first_name, 
    @last_name, 
    @email,
    @start_date,
    @sick_days,
    @fringe_ratio,
    @department_id

select @rval
go
