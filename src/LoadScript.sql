create table App.tbl_picklist(id int not null generated always as identity constraint option_pk primary key,
option_name varchar(150) not null,
option_value varchar(150),
section varchar(100) not null);


CREATE TABLE APP.TBL_TASK (
		ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
		PROJECT_NAME VARCHAR(50) NOT NULL,
		DATE DATE DEFAULT current_date NOT NULL,
		PHASE VARCHAR(100),
		MODULE VARCHAR(100),
		ACTIVITY VARCHAR(100),
		WORK_PRODUCT VARCHAR(150),
		HOUR_SPENT VARCHAR(10),
		REMARKS VARCHAR(200),
		USER_ID VARCHAR(50)
	);


CREATE TABLE APP.TBL_USER (
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
	USER_NAME VARCHAR(100) NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL
)


