/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPBank;

/**
 *
 * @author phong
 */
public class Main {

    public static int getMenuChoice() {
        GetDataInput getData = new GetDataInput();
        return getData.getInt("Enter your choice: ", "Input is out of range: "
                + "[" + 1 + "], " + "[" + 3 + "]", "Input is empty", 1, 3);
    }

    public static void main(String[] args) {
        Display display = new Display();
        EBank eBank = new EBank();
        int choice;

        do {
            //1. Display menu
            display.displayMenu();
            //2. Get choice from user
            choice = getMenuChoice();
            //3. Perform option base on the user choice
            switch (choice) {
                case 1:
                    //Login with Vietnamese language
                    eBank.VietnameseLoginSystem();
                    break;
                case 2:
                    //Login with English language
                    eBank.EnglishLoginSystem();
                    break;
                case 3:
                    //Exit
                    System.exit(0);
            }
        } while (choice != 3);
    }

}
