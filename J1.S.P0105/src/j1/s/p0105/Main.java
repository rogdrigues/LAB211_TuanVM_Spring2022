/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0105;

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
        Manager manager = new Manager();
        Display display = new Display();
        ArrayList<Storekeeper> storeKeeperList = new ArrayList<>();
        ArrayList<Product> productList = new ArrayList<>();
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
                    //Add storeKeeper
                    manager.addStoreKeeper(storeKeeperList);
                    break;
                case 2:
                    //Add product
                    manager.addProduct(storeKeeperList, productList);
                    break;
                case 3:
                    //Update product by ID
                    manager.updateProduct(storeKeeperList, productList);
                    break;
                case 4:
                    //Search product by Name, Category, Storekeeper or ReceiptDate
                    manager.searchProduct(productList);
                    break;
                case 5:
                    //Sort product by Expiry date, date of manufacture
                    manager.sortProduct(productList);
                    break;
                case 6:
                    //Exit
                    System.exit(0);
                    break;
            }
        } while (choice != 6);

    }

}
