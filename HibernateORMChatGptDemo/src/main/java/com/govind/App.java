package com.govind;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee employee = new Employee();
        employee.setName("govind");
        employee.setDepartment("it");

        EmployeeDAO dao = new EmployeeDAO();
        dao.saveEmployee(employee);

        Employee retrievedEmployee = dao.getEmployeeById(1L);


        System.out.println(retrievedEmployee);
    }
}
