/*
 * department table
 */
use testdb
go

-- drop database object if already exist
if object_id('department') is not null
  drop table department
go

-- create the table
create table department (
  department_id     numeric(7,0) identity
    constraint pk_department_id primary key nonclustered,
  department_code   char(8),
  department_name   varchar(60),

)
go

-- load with inital values for testing
insert into department values('RASP','Research And Advanced Study Programs')
insert into department values('RAD','Radiology')
insert into department values('PSL','Physiology')
insert into department values('PSL/HP','Physiology/HP')
insert into department values('PSC','Psychiatry')
insert into department values('PMR','Physical Medicine And Rehabilitation')
insert into department values('PHM','Pharmacology & Toxicology')
insert into department values('PED','Pediatrics')
insert into department values('OSS','Osteopathic Surgical Specialties')
insert into department values('OMS','Osteopathic Medical Specialties')
insert into department values('OMM','Osteopathic Manipulative Medicine')
insert into department values('NURO','Neuroscience')
insert into department values('NOPT','Neurology and Ophthalmology')
insert into department values('MUC','Macomb University Center')
insert into department values('MMG','Microbiology & Molecular Genetics')
insert into department values('MCI','Institute for Health Care Studies')
insert into department values('LAC','Learning and Assessment Center')
insert into department values('IIH','Institute Of International Health')
insert into department values('FCM','Family And Community Medicine')
insert into department values('DO','Deans Office')
insert into department values('DMC','Detroit Medical Center')
insert into department values('BMB','Biochemistry & Molecular Biology')
go

-- display success/fail message
if object_id('department') is not null
  select 'department succsessfully created in ' + @@servername + '.' + db_name()
else
  select 'department NOT created in ' + @@servername + '.' + db_name()
go
