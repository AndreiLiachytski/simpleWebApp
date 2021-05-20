package com.chitts.dao.query;

public class EmployeeSqlQuery {

    //    Data Definition
    public static final String CREATE_TABLE =
            "DROP TABLE IF EXISTS employees;" +
            "DROP TYPE IF EXISTS gender;"+
                    "CREATE TYPE gender AS ENUM ('MALE','FEMALE');" +
                    "CREATE TABLE employees(employee_id serial primary key,\n" +
                    "                        first_name varchar NOT NULL,\n" +
                    "                        last_name varchar NOT NULL,\n" +
                    "                        department_id int,\n" +
                    "                        job_title varchar,\n" +
                    "                        gender varchar,\n" +
                    "                        date_of_birth Date DEFAULT '01/01/1900' CHECK(date_of_birth < current_date ));";


    public static final String INSERT =
            "INSERT into employees(first_name,last_name,department_id,job_title,gender,date_of_birth)" +
                    " values ('Bob','Marley',2,'singer','MALE','10/10/1990');" +
             "INSERT into employees(first_name,last_name,department_id,job_title,gender,date_of_birth)" +
                    " values ('Viktor','Ivanov',2,'painter','MALE','09/22/1981');" +
             "INSERT into employees(first_name,last_name,department_id,job_title,gender,date_of_birth)" +
                    " values ('Kate','Petrova',2,'teacher','FEMALE','10/25/1988');";

    public static final String DROP_TABLE =
            "DROP TABLE employees;" +
            "DROP TYPE gender;";


    //    Data Manipulation
    public static final String GET_ALL = "SELECT * FROM employees ORDER BY employee_id";
    public static final String GET_BY_ID = "SELECT * FROM employees WHERE employee_id =?";
    public static final String DELETE = "DELETE FROM employees WHERE employee_id =?";
    public static final String UPDATE = "UPDATE employees SET" +
            " first_name=?,last_name=?,department_id=?,job_title=?," +
            "gender = CAST(? as gender),date_of_birth =? WHERE employee_id =?";
    public static final String SAVE = "INSERT into employees " +
            "(first_name, last_name, department_id, job_title, gender, date_of_birth)" +
            " values(?,?,?,?,CAST(? as gender),?)";

}