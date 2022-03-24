/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0063;

/**
 *
 * @author phong
 */
public class Main {

    public static void main(String[] args) {
        Person[] persons = new Person[3];
        //1. User input person information
        inputPersonInfo(persons);
        //2. Sort persons array by salary ascending
        sortPersonBySalary(persons);
        //3. Display persons information
        displayPersonInfo(persons);
    }

    public static void inputPersonInfo(Person[] persons) {
        System.out.println("=====Management Person programer=====");

        GetDataInput getData = new GetDataInput();

        //Loop through to add person to persons of array
        for (int i = 0; i < persons.length; i++) {
            System.out.println("Input Information of Person");
            /*Regex
            [a-zA-Z ]: Input matches a character in range "a" to "z", "A" to "Z"
            and must have a space character
            [+]: Matches 1 or more
             */
            String name = getData.getString("Please input name:", "Input cannot be "
                    + "a number or special character", "Input is empty!", "[a-zA-Z ]+");
            String address = getData.getString("Please input address:", "Input "
                    + "cannot be a number or special character", "Input is empty",
                    "[a-zA-Z ]+");
            Person person = null;
            do {
                String inputSalary = getData.getString("Please input salary:", "", "", "");
                try {
                    person = inputPersonInfo(name, address, inputSalary);
                    break;
                } catch (NumberFormatException stringException) {
                    System.err.println("You must input digit.");
                } catch (Error emptyInput) {
                    System.err.println("You must input Salary");
                } catch (Exception outOfRange) {
                    System.err.println("Salary is greater than zero");
                }
            } while (true);
            persons[i] = person;
        }
    }

    public static void sortPersonBySalary(Person[] person) {
        try {
            person = sortBySalary(person);
        } catch (Exception sortException) {
            System.err.println("Can't sort Person");
        }
    }

    public static Person[] sortBySalary(Person[] person) throws Exception {
        int arrayLength = person.length;

        /*.First loop use to accessed from first element to the element 
        before the last element from an person array. After each loop, one 
        person is sorted.*/
        for (int i = 0; i < arrayLength - 1; i++) {
            /*Second loop use to accessed first unsorted element to the element 
            stand from an person array before the last unsorted element*/
            for (int j = 0; j < arrayLength - i - 1; j++) {
                /*If current person's salary greater than next person's salary
                then swap these two person*/
                if (person[j].getSalary() > person[j + 1].getSalary()) {
                    Person temp = person[j];
                    person[j] = person[j + 1];
                    person[j + 1] = temp;
                }
            }
        }
        return person;
    }

    public static Person inputPersonInfo(String name, String address, String sSalary) throws Exception {
        Person person;
        double salary;

        //Check if user didn't enter a salary from keyboard
        if (sSalary.isEmpty()) {
            throw new Error();
        } else {
            salary = Double.valueOf(sSalary);
            //Check if salary contains a String or character value
            if (sSalary.equals(String.valueOf(salary))) {
                throw new NumberFormatException();
            }//Check if salary was less than zero
            else if (salary < 0) {
                throw new Exception();
            }//Otherwise, return a person object 
            else {
                person = new Person(name, address, salary);
                return person;
            }
        }
    }

    private static void displayPersonInfo(Person[] persons) {
        System.out.println("\nInformation of Person you have entered:");
        
        //Loop through persons of array from the first to the last element
        for (Person person : persons) {
            System.out.println("Name:" + person.getName() + "\nAddress:"
                    + person.getAddress() + "\nSalary:"
                    + person.getSalary() + "\n");
        }
    }
}
