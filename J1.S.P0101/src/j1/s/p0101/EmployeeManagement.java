/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0101;

import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;

/**
 *
 * @author phong
 */
class EmployeeManagement {

    public void addEmployee(ArrayList<Employee> employeeList) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();
        int employeeID;
        boolean isEmployeeIdExist;

        /*Check if list of employees is not empty then display the information 
        of employee*/
        if (!employeeList.isEmpty()) {
            display.displayEmployeeList(employeeList);
        }

        System.out.println("Enter employee ID:");
        //Loop until user's employee is not duplicate
        do {
            employeeID = getData.getInt("", "Input cannot less or equal than "
                    + "zero", 1, Integer.MAX_VALUE);
            isEmployeeIdExist = checkEmployeeIdExist(employeeList, employeeID);
            //Check if user's employeeID is exist then re-input
            if (isEmployeeIdExist) {
                System.err.println("Employee ID is duplicate!");
            } else {
                break;
            }
        } while (true);

        /*First name and Last namem
        [a-z]: Input matches a character in the range "a" to "z"
        [A-Z]: Input matches a character in the range "A" to "Z"
        [+]: Matches 1 or more
        [ ]: Input contains a space character
         */
        String firstName = getData.getString("Enter employee's first name:",
                "[a-zA-Z ]+");
        String lastName = getData.getString("Enter employee's last name:",
                "[a-zA-Z ]+");

        /*Phone
        ^0: Matches the beginning (starting at 0) of the line
        [987543]{1}: Input matches one character in [987543]
        [\\d{8}]: Input matches a character in range [0-9] and have 9 characters 
        [+]: Matches 1 or more
        [$]: Matches the end of the line
         */
        String phone = getData.getString("Enter employee's phone number:",
                "^0[987543]{1}\\d{8}$");

        /*Email
        [^]: Matches the beginning (starting at 0) of the line
        [a-zA-Z0-9]: Input matches a characters in the range "a" to "z", 
        "A" to "Z", "0" to "9"
        [@]: Input must have a character "@"
        [.]: Input must have a character "."
        [a-zA-Z]{2,6}: Input matches a characters in the range "a" to "z",
        "A" to "Z" from 2 to 6 times
        [|]: boolean OR for 2 case email
        [+]: Matches 1 or more
        [$]: Matches the end of the line
         */
        String email = getData.getString("Enter employee's email",
                "^[a-zA-Z0-9]+@([a-zA-Z]{2,6}[.][a-zA-Z]{2,6}|[a-zA-Z]{2,6}[.]"
                + "[a-zA-Z]{2,6}[.][a-zA-Z]{2,6})+$");
        String address = getData.getString("Enter employee's address", "[a-zA-Z ]+");

        Date dateOfBirth;
        System.out.println("Enter employee's birthday [dd/MM/yyyy]:");
        /*Loop until user's enter a date of birth with correct date format and 
        before current date*/
        do {
            dateOfBirth = getData.getDate("");
            Date currentTimeNow = new Date();
            //Compare date of birth with today
            if (dateOfBirth.after(currentTimeNow)) {
                System.err.println("Employee's birthday must be in the past");
            } else {
                break;
            }
        } while (true);

        /*Gender
        [fmFM]: Input matches one characters in both upper and lower case
        ("F" or "M", "f" or "m") 
         */
        String sex = getData.getString("Enter employee's gender[F: female | "
                + "M: male]:", "[fmFM]").toUpperCase();
        int salary = getData.getInt("Enter employee's salary", "Salary cannot "
                + "less than zero", 0, Integer.MAX_VALUE);
        String agency = getData.getString("Enter employee's agency", "[a-zA-Z ]+");
        Employee newEmployee = new Employee(employeeID, firstName, lastName, phone,
                email, address, dateOfBirth, sex, salary, agency);
        employeeList.add(newEmployee);
        display.displayEmployeeList(employeeList);
    }

    public void updateEmployee(ArrayList<Employee> employeeList) {
        //Check if the list of employees is empty
        if (employeeList.isEmpty()) {
            System.err.println("The list of employees is empty!");
        } else {
            GetDataInput getData = new GetDataInput();
            Display display = new Display();

            display.displayEmployeeList(employeeList);
            Employee employeeUpdate = null;

            System.out.println("Enter ID of employee you want to update:");
            //Loop until user choose an employee ID that existed
            do {
                int employeeChoice = getData.getInt("", "", 0, Integer.MAX_VALUE);
                //Loop through all employees in list of employees
                for (Employee employee : employeeList) {
                    if (employeeChoice == employee.getEmployeeID()) {
                        employeeUpdate = employee;
                        break;
                    }
                }
                //Check if id of employee that user's input is not found
                if (employeeUpdate == null) {
                    System.err.println("Employee ID does not exist!");
                } else {
                    break;
                }
            } while (true);
            updateEmployeeChoice(employeeList, employeeUpdate);
        }
    }

    public void removedEmployee(ArrayList<Employee> employeeList) {
        //Check if the list of employees is empty
        if (employeeList.isEmpty()) {
            System.err.println("The list of employees is empty!");
        } else {
            GetDataInput getData = new GetDataInput();
            Display display = new Display();

            display.displayEmployeeList(employeeList);
            Employee employeeRemove = null;

            System.out.println("Enter ID of employee you want to remove:");
            //Loop until user choose an employee ID that existed
            do {
                int employeeChoice = getData.getInt("", "", 0, Integer.MAX_VALUE);
                //Loop through all employees in list of employees
                for (Employee employee : employeeList) {
                    if (employeeChoice == employee.getEmployeeID()) {
                        employeeRemove = employee;
                        break;
                    }
                }
                //Check if id of employee that user's input is not found
                if (employeeRemove == null) {
                    System.err.println("Employee ID does not exist!");
                } else {
                    employeeList.remove(employeeRemove);
                    break;
                }
            } while (true);
            display.displayEmployeeList(employeeList);
        }
    }

    public void searchEmployee(ArrayList<Employee> employeeList) {
        //Check if the list of employees is empty
        if (employeeList.isEmpty()) {
            System.err.println("The list of employees is empty!");
        } else {
            GetDataInput getData = new GetDataInput();
            Display display = new Display();
            ArrayList<Employee> listEmployeeSearch = new ArrayList<>();

            display.displayEmployeeList(employeeList);
            System.out.println("Enter employee's name for searching:");
            //Loop until user choose an employee ID that existed
            do {
                /*First name
                [a-z]: Input matches a character in the range "a" to "z"
                [A-Z]: Input matches a character in the range "A" to "Z"
                [+]: Matches 1 or more
                [ ]: Input contains a space character
                 */
                String employeeName = getData.getString("", "[a-zA-Z ]+");
                //Loop through all employees in list of employees
                for (Employee employee : employeeList) {
                    String fullName = employee.getFirstName() + " "
                            + employee.getLastName();
                    //Check if employee's full name contains the user's input name
                    if (fullName.contains(employeeName)) {
                        listEmployeeSearch.add(employee);
                    }
                }
                //Check if user's input employeeName is not exist in list of employee
                if (listEmployeeSearch.isEmpty()) {
                    System.err.println("Employee name does not exist!");
                } else {
                    break;
                }
            } while (true);
            display.displayEmployeeList(listEmployeeSearch);
        }
    }

    public void SortEmployeeBySalary(ArrayList<Employee> employeeList) {
        //Check if list of employee is empty
        if (employeeList.isEmpty()) {
            System.err.println("The list of employee is empty!");
        } else {
            Display display = new Display();
            Collections.sort(employeeList);
            display.displayEmployeeList(employeeList);
        }
    }

    //Update product's information methods
    private void updateEmployeeID(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        System.out.println("Enter employee ID: ");
        /*Loop until user update a employee ID without duplicate current 
        employee ID in list of employees*/
        do {
            int employeeID = getData.getInt("", "", 0, Integer.MAX_VALUE);

            /*Check if user's employeeID is the same ID with employeeUpdate. 
            In case not, then check if user's employee id is duplicate in list 
            of employees*/
            if (!checkEmployeeIdExist(employeeList, employeeID)
                    || employeeUpdate.getEmployeeID() == employeeID) {
                employeeUpdate.setEmployeeID(employeeID);
                break;
            } else {
                System.err.println("Employee ID is duplicate");
            }
        } while (true);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeFirstName(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        /*First name
        [a-z]: Input matches a character in the range "a" to "z"
        [A-Z]: Input matches a character in the range "A" to "Z"
        [+]: Matches 1 or more
        [ ]: Input contains a space character
         */
        String firstName = getData.getString("Enter employee's first name:",
                "[a-zA-Z ]+");
        employeeUpdate.setFirstName(firstName);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeLastName(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        /*Last name
        [a-z]: Input matches a character in the range "a" to "z"
        [A-Z]: Input matches a character in the range "A" to "Z"
        [+]: Matches 1 or more
        [ ]: Input contains a space character
         */
        String lastName = getData.getString("Enter employee's first name:",
                "[a-zA-Z ]+");
        employeeUpdate.setLastName(lastName);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeePhone(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        /*
        [^0]: Matches the beginning (starting at 0) of the line
        [987543]{1}: Input matches one character in [987543]
        [\\d{8}]: Input matches a character in range [0-9] and have 9 characters 
        [+]: Matches 1 or more
        [$]: Matches the end of the line
         */
        String phone = getData.getString("Enter employee's phone number:",
                "^0[987543]{1}\\d{8}$");
        employeeUpdate.setPhone(phone);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeEmail(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        /*Email
        [^]: Matches the beginning (starting at 0) of the line
        [a-zA-Z0-9]: Input matches a characters in the range "a" to "z", 
        "A" to "Z", "0" to "9"
        [@]: Input must have a character "@"
        [.]: Input must have a character "."
        [a-zA-Z]{2,6}: Input matches a characters in the range "a" to "z",
        "A" to "Z" from 2 to 6 times
        [|]: boolean OR for 2 case email
        [+]: Matches 1 or more
        [$]: Matches the end of the line
         */
        String email = getData.getString("Enter employee's email",
                "^[a-zA-Z0-9]+@([a-zA-Z]{2,6}[.][a-zA-Z]{2,6}|[a-zA-Z]{2,6}[.]"
                + "[a-zA-Z]{2,6}[.][a-zA-Z]{2,6})+$");
        employeeUpdate.setEmail(email);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeAddress(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        String address = getData.getString("Enter employee's "
                + "address", "[a-zA-Z ]+");
        employeeUpdate.setAddress(address);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeDateOfBirth(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        System.out.print("Enter employee's date of birth[dd/MM/yyyy]:");
        /*Loop until user enter a date of manufacture with correct 
                    date format and before current date*/
        do {
            Date dateOfBirth = getData.getDate("");
            Date currentTimeNow = new Date();
            //Compare date of maniplulate with today
            if (dateOfBirth.after(currentTimeNow)) {
                System.err.println("Date of birth must be in the past.");
            } else {
                employeeUpdate.setDOB(dateOfBirth);
                break;
            }
        } while (true);
        displayList.displayEmployeeList(employeeList);
    }

    private void updateEmployeeGender(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        /*Gender
        [fmFM]: Input matches one characters in both upper and lower case
        ("F" or "M" and "f" or "m") 
         */
        String sex = getData.getString("Enter employee's gender[F: female | "
                + "M: male]:", "[fmFM]").toUpperCase();
        employeeUpdate.setSex(sex);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeSalary(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        int salary = getData.getInt("Enter salary:", "Price must be a "
                + "positive number", 0, Integer.MAX_VALUE);
        employeeUpdate.setSalary(salary);
        displayList.displayEmployeeList(employeeList);
    }

    private void updateEmployeeAgency(ArrayList<Employee> employeeList,
            Employee employeeUpdate) {
        GetDataInput getData = new GetDataInput();
        Display display = new Display();

        String agency = getData.getString("Enter employee's agency", "[a-zA-Z ]+");
        employeeUpdate.setAddress(agency);
        display.displayEmployeeList(employeeList);
    }

    private void updateEmployeeChoice(ArrayList<Employee> employeeList, Employee employeeUpdate) {
        Display displayList = new Display();
        GetDataInput getData = new GetDataInput();
        int choice;
        //Loop until user select an option
        do {
            //1. Display menu
            displayList.displayUpdateMenu();
            //2. Get choice from user
            choice = getData.getInt("Enter your choice:", "Input is out of range: "
                    + "[" + 1 + "], " + "[" + 11 + "]", 1, 11);
            //3. Perform option base on the user choice
            switch (choice) {
                case 1:
                    //Update employee id
                    updateEmployeeID(employeeList, employeeUpdate);
                    break;
                case 2:
                    //Update employee first name
                    updateEmployeeFirstName(employeeList, employeeUpdate);
                    break;
                case 3:
                    //Update employee last name
                    updateEmployeeLastName(employeeList, employeeUpdate);
                    break;
                case 4:
                    //Update employee phone
                    updateEmployeePhone(employeeList, employeeUpdate);
                    break;
                case 5:
                    //Update employee email
                    updateEmployeeEmail(employeeList, employeeUpdate);
                    break;
                case 6:
                    //Update employee address
                    updateEmployeeAddress(employeeList, employeeUpdate);
                    break;
                case 7:
                    //Update employee date of birth
                    updateEmployeeDateOfBirth(employeeList, employeeUpdate);
                    break;
                case 8:
                    //Update employee gender(sex)
                    updateEmployeeGender(employeeList, employeeUpdate);
                    break;
                case 9:
                    //Update employee salary
                    updateEmployeeSalary(employeeList, employeeUpdate);
                    break;
                case 10:
                    //Update employee agency
                    updateEmployeeAgency(employeeList, employeeUpdate);
                    break;
                case 11:
                    //Exit
                    break;
            }
        } while (choice != 11);
    }

    //Check employee id exist
    private boolean checkEmployeeIdExist(ArrayList<Employee> employeeList, int employeeID) {
        boolean isDuplicate = false;
        //Loop through every elements of employeeList from the first to the last one
        for (Employee employee : employeeList) {
            //Check if user's employee id is exist in list of employee or not
            if (employee.getEmployeeID() == employeeID) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

}
