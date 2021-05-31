package com.chitts.dao.query;

public class EmployeeQuery {

    public static final String GET_ALL = "SELECT * FROM employees ORDER BY employee_id";

    public static final String GET_BY_ID = "SELECT * FROM employees WHERE employee_id =?";

    public static final String DELETE = "DELETE FROM employees WHERE employee_id =?";

    public static final String UPDATE =
            "UPDATE employees SET " +
                    "first_name=?," +
                    "last_name=?," +
                    "department_id=?," +
                    "job_title=?," +
                    "gender = CAST(? as gender)," +
                    "date_of_birth =?" +
                        "WHERE employee_id =?";

    public static final String SAVE =
            "INSERT into employees(" +
                    "first_name," +
                    "last_name," +
                    "department_id," +
                    "job_title," +
                    "gender," +
                    "date_of_birth)" +
                        "values(?,?,?,?,CAST(? as gender),?)";
}