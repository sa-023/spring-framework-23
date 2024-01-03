-- When we create a table with Hibernate, we can still insert data into the table using the data.sql file.
-- We just need to change the name of the schema.sql file, so Spring doesn't understand or read this file. Because we already have a table, we just insert the data into it.

-- insert into EMPLOYEES(id,name)
-- VALUES (1,'Mike Smith');

insert into student(first_name,last_name,email)
VALUES ('Mike','Smith','mike@company.com');