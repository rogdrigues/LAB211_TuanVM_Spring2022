/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0101;

import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class Main {

    public static int getMenuChoice() {
        GetDataInput getData = new GetDataInput();
        return getData.getInt("Enter your choice:", "Input is out of range: "
                + "[" + 1 + "], " + "[" + 6 + "]", 1, 6);
    }

    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        ArrayList<Employee> employeeList = new ArrayList<>();
        Display display = new Display();
        int choice;

        //Loop until user select an option
        do {
            //1. Display Menu
            display.displayMenu();
            //2. Get choice from user
            choice = getMenuChoice();
            //3. Perform option base on the user choice
            switch (choice) {
                case 1:
                    //Add an employee
                    employeeManagement.addEmployee(employeeList);
                    break;
                case 2:
                    //Update an employee
                    employeeManagement.updateEmployee(employeeList);
                    break;
                case 3:
                    //Remove an employee by ID
                    employeeManagement.removedEmployee(employeeList);
                    break;
                case 4:
                    //Search an employee by name (first name or last name)
                    employeeManagement.searchEmployee(employeeList);
                    break;
                case 5:
                    //Sort employees by salary ascending
                    employeeManagement.SortEmployeeBySalary(employeeList);
                    break;
                case 6:
                    //Exit
                    System.exit(0);
                    break;
            }
        } while (choice != 6);
    }
}
