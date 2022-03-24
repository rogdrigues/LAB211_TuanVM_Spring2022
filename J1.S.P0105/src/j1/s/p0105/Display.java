/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0105;

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
        System.out.println("1. Add Storekeeper");
        System.out.println("2. Add product");
        System.out.println("3. Update product");
        System.out.println("4. Search product by Name, Category, Storekeeper, ReceiptDate");
        System.out.println("5. Sort product by Expiry date, Date of manufacture");
        System.out.println("6. Exit");
        System.out.println("=====================================================================================");
    }

    void displayStorekeeper(ArrayList<Storekeeper> storeList) {
        //Check if the list of storeKeeper is empty
        if (storeList.isEmpty()) {
            System.out.println("Store list is empty!");
        } else {
            System.out.println("=========Storekeeper List===========");
            System.out.format("%-5s%-20s\n", "ID", "Name");
            //loop use to access each element of arraylist from begining to the end
            for (Storekeeper storekeeper : storeList) {
                System.out.format("%-5d%-20s\n", storekeeper.getStorekeeperID(),
                        storekeeper.getStorekeeperName());
            }
        }
    }

    void displayProductList(ArrayList<Product> productList) {
        //Check if the list of product is empty
        if (productList.isEmpty()) {
            System.out.println("Product list is empty!");
        } else {
            System.out.println("==========Product List===========");
            System.out.format("%-3s%-15s%-10s%-10s%-20s%-20s%-15s%-20s%-15s\n",
                    "ID", "Name", "Location", "Price", "Manufacture date",
                    "Expiry date", "Category", "Storekeeper", "Receipt date");
            //Loop use to access each element of arraylist from begining to the end
            for (Product product : productList) {
                displayProduct(product);
            }
        }
    }

    void displayProduct(Product product) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String expiryDate = dateFormat.format(product.getExpiryDate());
        String dateOfManu = dateFormat.format(product.getDateOfManufacture());
        String receiptDate = dateFormat.format(product.getReceiptDate());

        System.out.format("%-3d%-15s%-10s%-10d%-20s%-20s%-15s%-20s%-15s\n",
                product.getProductID(), product.getProductName(), product.getLocation(),
                product.getPrice(), dateOfManu, expiryDate, product.getCategory(),
                product.getStoreKeeper(), receiptDate);
    }

    void displayUpdateMenu() {
        System.out.println();
        System.out.println("                                        UPDATE MENU                                         ");
        System.out.println("=====================================================================================");
        System.out.println("1. Update product ID");
        System.out.println("2. Update product name");
        System.out.println("3. Update location");
        System.out.println("4. Update price");
        System.out.println("5. Update expiry date");
        System.out.println("6. Update date of manufacture");
        System.out.println("7. Update category");
        System.out.println("8. Update storekeeper");
        System.out.println("9. Update recipt date");
        System.out.println("10. Exit");
        System.out.println("=====================================================================================");
    }

    void displaySearchMenu() {
        System.out.println("=================SEARCH MENU==================");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by category");
        System.out.println("3. Search by storekeeper");
        System.out.println("4. Search by receipt date");
    }
}
