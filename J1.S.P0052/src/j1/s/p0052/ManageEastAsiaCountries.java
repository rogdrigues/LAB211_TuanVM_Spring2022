/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0052;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author phong
 */
public class ManageEastAsiaCountries {

    public void displayMenu() {
        System.out.println();
        System.out.println("                                        MENU                                         ");
        System.out.println("=====================================================================================");
        System.out.println("1. Input the information of 11 countries in East Asia");
        System.out.println("2. Display the information of country you've just input");
        System.out.println("3. Search the information of country by user-entered name");
        System.out.println("4. Display the information of countries sorted name in ascending order");
        System.out.println("5. Exit");
        System.out.println("=====================================================================================");
    }

    public void addCountryInformation(ArrayList<EastAsiaCountries> countryList) {
        GetDataInput getData = new GetDataInput();
        String countryCode, countryName, terrain;
        float totalArea;
        int numberOfCountry = 0;

        // Loop until user enter enough information for 3 countries
        while (numberOfCountry < 3) {
            //Loop until user enter a countryCode without duplicate
            do {
                countryCode = getData.getString("Enter code of country: ");
                //If countryCode's user input exist then re-input
                if (countryExist(countryList, countryCode)) {
                    System.err.println("Duplicated, please enter another code");
                } else {
                    break;
                }
            } while (true);

            countryName = getData.getString("Enter name of country: ");

            //Loop until user enter a totalArea greater than zero.
            do {
                totalArea = getData.getFloat("Enter total Area: ");
                //User enter totalArea greater than zero.
                if (totalArea < 0) {
                    System.err.println("Total area cannot less than zero");
                } else {
                    break;
                }
            } while (true);
            terrain = getData.getString("Enter terrain of country: ");

            countryList.add(new EastAsiaCountries(countryCode, countryName,
                    totalArea, terrain));
            System.out.println("Country has been added.");
            numberOfCountry++;
        }
        System.out.println(numberOfCountry + " countries has been added\n");
    }

    public void getRecentylyEnteredInformation(ArrayList<EastAsiaCountries> countryList) {
        //Check if user didn't enter any information to list of country
        if (listCountryEmpty(countryList)) {
            System.err.println("You didn't enter any country. Cannot get "
                    + "recentyly entered information");
        } else {
            displayHeader();
            //Loop use to display all information of countries
            for (EastAsiaCountries countries : countryList) {
                countries.Display();
            }
        }
    }

    public void searchInformationByName(ArrayList<EastAsiaCountries> countryList) {
        //Check if user didn't enter any information to list of country
        if (listCountryEmpty(countryList)) {
            System.err.println("You didn't enter any country. Cannot search "
                    + "country.");
            return;
        }
        GetDataInput getData = new GetDataInput();
        int numberOfCountryFound = 0;

        String countryName = getData.getString("Enter the name you want "
                + "to search for:");

        // Loop through all countries in list of countries
        for (EastAsiaCountries countries : countryList) {
            //Check if user input contains with any information of countryName
            if (countries.getCountryName().contains(countryName)) {
                /*If user input contains with any information of countryName 
                then display header.*/
                if (numberOfCountryFound == 0) {
                    displayHeader();
                }
                countries.Display();
                numberOfCountryFound++;
            }
        }
        //Check if there isn't any country found with user input
        if (numberOfCountryFound == 0) {
            System.err.println(countryName + " is not existing");
        }
    }

    public void sortInformationByAscendingOrder(ArrayList<EastAsiaCountries> countryList) {
        //Check if user didn't enter any information to list of country
        if (listCountryEmpty(countryList)) {
            System.err.println("You didn't enter any country. Cannot display"
                    + " information of country.");
        } else {
            //Sort information of countries by ascending countryName
            Collections.sort(countryList);
            displayHeader();
            //Loop use to display all information of countries
            for (EastAsiaCountries countries : countryList) {
                countries.Display();
            }
        }
    }

    private void displayHeader() {
        System.out.printf("%-20s%-20s%-20s%-20s\n", "ID", "Name", "Total Area",
                "Terrain");
    }

    private boolean countryExist(ArrayList<EastAsiaCountries> countryList,
            String countryCode) {
        // Loop through all countries in list
        for (EastAsiaCountries countries : countryList) {
            // Check if user input's countryCode is exist
            if (countries.getCountryCode().equalsIgnoreCase(countryCode)) {
                return true;
            }
        }
        return false;
    }

    private boolean listCountryEmpty(ArrayList<EastAsiaCountries> countryList) {
        return countryList.isEmpty();
    }
}
