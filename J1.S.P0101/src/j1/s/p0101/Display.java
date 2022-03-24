/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0101;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author phong
 */
public class Display {

    void displayMenu() {
        System.out.println();
        System.out.println("                                        MENU                                         ");
        System.out.println("=====================================================================================");
        System.out.println("1. Add employees");
        System.out.println("2. Update employees by ID");
        System.out.println("3. Remove employees by ID");
        System.out.println("4. Search employees by Name (first name or last name)");
        System.out.println("5. Sort employees by salary");
        System.out.println("6. Exit");
        System.out.println("=====================================================================================");
    }

    void displayEmployeeList(ArrayList<Employee> EmployeeList) {
        //Check if the list of employee is empty
        if (EmployeeList.isEmpty()) {
            System.err.println("The list of employee is empty");
        } else {
            System.out.println("==========Employee List===========");
            System.out.format("%-5s%-15s%-15s%-20s%-35s%-15s%-15s%-10s%-15s%-15s\n", "ID",
                    "First Name", "Last Name", "Phone Number", "Email",
                    "Address", "Birthday", "Gender", "Salary", "Agency");
            //loop use to access each element of arraylist from begining to the end
            for (int i = 0; i < EmployeeList.size(); i++) {
                displayEmployee(EmployeeList, i);
            }
        }
    }

    void displayEmployee(ArrayList<Employee> EmployeeList, int index) {
        //Check if the list of employee is empty
        if (EmployeeList.isEmpty()) {
            System.err.println("The list of employee is empty");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String DayofBirth = dateFormat.format(EmployeeList.get(index).getDOB());
            System.out.format("%-5s%-15s%-15s%-20s%-35s%-15s%-15s%-10s%-15s%-15s\n",
                    EmployeeList.get(index).getEmployeeID(), EmployeeList.get(index)
                    .getFirstName(), EmployeeList.get(index).getLastName(),
                    EmployeeList.get(index).getPhone(), EmployeeList.get(index)
                    .getEmail(), EmployeeList.get(index).getAddress(),
                    DayofBirth, EmployeeList.get(index).getSex(),
                    EmployeeList.get(index).getSalary(),
                    EmployeeList.get(index).getAgency());
        }
    }

    void displayUpdateMenu() {
        System.out.println();
        System.out.println("                                        UPDATE MENU                                         ");
        System.out.println("=====================================================================================");
        System.out.println("1. Update employee ID");
        System.out.println("2. Update employee's first name");
        System.out.println("3. Update employee's last name");
        System.out.println("4. Update employee's phone");
        System.out.println("5. Update employee's email");
        System.out.println("6. Update employee's address");
        System.out.println("7. Update employee's date of birth(DOB)");
        System.out.println("8. Update employee's gender");
        System.out.println("9. Update employee's salary");
        System.out.println("10. Update employee's agency");
        System.out.println("11. Exit");
        System.out.println("=====================================================================================");
    }
}
