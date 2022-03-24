/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0052;
import java.util.ArrayList;
/**
 *
 * @author phong
 */
public class Main {

    public static int getMenuChoice() {
        GetDataInput getData = new GetDataInput();
        return getData.getInt("Enter your choice: ", 1, 5);
    }

    public static void main(String[] args) {
        ManageEastAsiaCountries manager = new ManageEastAsiaCountries();
        ArrayList<EastAsiaCountries> countryList = new ArrayList<>();
        int choice;
        
        //Loop until user select an option
        do {
            //Step 1: Display Menu
            manager.displayMenu();
            //Step 2: Get choice from user
            choice = getMenuChoice();
            //Step 3: Perform option base on the user choice
            switch (choice) {
                case 1:
                    //Add information of countries
                    manager.addCountryInformation(countryList);
                    break;
                case 2:
                    //Get recently information of country user has been inputed
                    manager.getRecentylyEnteredInformation(countryList);
                    break;
                case 3:
                    //Search information of countries by user-entered name
                    manager.searchInformationByName(countryList);
                    break;
                case 4:
                    //Display information of countries asecending by name
                    manager.sortInformationByAscendingOrder(countryList);
                    break;
                case 5:
                    //Exit
                    System.exit(0);
                    break;
            }
        } while (choice != 5);
    }
}
