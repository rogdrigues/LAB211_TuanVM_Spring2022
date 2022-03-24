/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0071;

/**
 *
 * @author phong
 */
public class Main {

    public static int getMenuChoice() {
        GetDataInput getData = new GetDataInput();
        return getData.getInt("Enter your choice: ", "Input is out of range: "
                + "[" + 1 + "], " + "[" + 4 + "]", 1, 4);
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
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
                    //Add a new task
                    taskManager.addTask();
                    break;
                case 2:
                    //Delete a task
                    taskManager.deleteTask();
                    break;
                case 3:
                    //Show all tasks
                    taskManager.getDataTasks();
                    break;
                case 4:
                    //Exit
                    System.exit(0);
                    break;
            }
        } while (choice != 6);
    }

}
