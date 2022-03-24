/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0105;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author phong
 */
public class Manager {

    public void addStoreKeeper(ArrayList<Storekeeper> storeList) {
        GetDataInput getData = new GetDataInput();
        Display displayStore = new Display();
        String storeKeeperName;
        int storeKeeperID;

        //Check if store is not empty then display the information of storeKeeper
        if (!storeList.isEmpty()) {
            displayStore.displayStorekeeper(storeList);
        }

        System.out.println("Enter StoreKeeper name:");
        //Loop until user's input storeName is not duplicate 
        do {
            storeKeeperName = getData.getString("");
            //If storeList is empty then add storeName at the first one
            if (storeList.isEmpty()) {
                storeKeeperID = 1;
                Storekeeper storeKeeper = new Storekeeper(storeKeeperID,
                        storeKeeperName);
                storeList.add(storeKeeper);
            }//Otherwise, add a new store after the last element of store list
            else {
                storeKeeperID = storeList.size() + 1;
                Storekeeper storeKeeper = new Storekeeper(storeKeeperID,
                        storeKeeperName);
                storeList.add(storeKeeper);
            }
            break;
        } while (true);
        displayStore.displayStorekeeper(storeList);
    }

    public void addProduct(ArrayList<Storekeeper> storeList,
            ArrayList<Product> productList) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();
        int productID;
        boolean isProductExist;

        //If list of product is not empty then display list of product
        if (!productList.isEmpty()) {
            displayList.displayProductList(productList);
        }

        System.out.println("Enter ID:");
        //Loop until user enter a productID without duplicate
        do {
            productID = getData.getInt("", "Input cannot less or equal than zero",
                    1, Integer.MAX_VALUE);
            isProductExist = checkProductIdExist(productList, productID);
            //If user's productID is exist then re-input
            if (isProductExist) {
                System.err.println("ProductID was exist!");
            } else {
                break;
            }
        } while (true);

        String productName = getData.getString("Enter ProductName:");

        Storekeeper newStorekeeper = null;
        /*If list of storeKeeper is empty then require user to input a new 
        storeKeeper name*/
        if (storeList.isEmpty()) {
            //Loop until user enter a storekeeper name if storekeeper is empty
            System.out.println("Store list is empty.\n"
                    + "Do you want to add a new Storekeeper(Y/N):");
            do {
                String userChoice = getData.getString("");
                //If user's input is "Y" then add a new storekeeper into product
                if (userChoice.equalsIgnoreCase("y")) {
                    String storeName = getData.getString("Enter Storekeeper name:");
                    newStorekeeper = new Storekeeper(storeList.size() + 1, storeName);
                    storeList.add(newStorekeeper);
                    break;
                }//Otherwise, return to menu 
                else if (userChoice.equalsIgnoreCase("n")) {
                    return;
                }//If user's choice is not Y or N then re-input 
                else {
                    System.err.println("Invalid Choice. Please enter Y or N"
                            + " to continue.");
                }
            } while (true);
        }

        String productLocation = getData.getString("Enter location:");
        int priceProduct = getData.getInt("Enter price:",
                "Price must be a positive number", 0, Integer.MAX_VALUE);

        Date dateOfManu;
        System.out.println("Enter product's manufacture date[dd/MM/yyyy]:");
        /*Loop until user enter a date of manufacture with correct date format 
        and before current date*/
        do {
            dateOfManu = getData.getDate("");
            Date currentTimeNow = new Date();
            //Compare date of maniplulate with today
            if (dateOfManu.after(currentTimeNow)) {
                System.err.println("Date of manufacture must be in the past.");
            } else {
                break;
            }
        } while (true);

        Date expiryDate;
        System.out.println("Enter product's expiry date[dd/MM/yyyy]:");
        /*Loop until user enter a date of expiry with correct date format 
        and after date of manufacture*/
        do {
            expiryDate = getData.getDate("");
            //Compare expiryDate with date of manufacture
            if (expiryDate.before(dateOfManu)) {
                System.err.println("Expiry date must be after date of manufacture.");
            } else {
                break;
            }
        } while (true);

        String category = getData.getString("Enter category:");

        Storekeeper store = null;
        /*If there's currently only newStoreKeeper was add before and add into
        product's storekeeper then didn't need to show the list of storekeeper 
        to choose*/
        if (newStorekeeper != null) {
            store = newStorekeeper;
        } else {
            //Loop until user select an option to choose a storeKeeperID
            do {
                displayList.displayStorekeeper(storeList);
                int storeKeeperChoice = getData.getInt("Choose Storekeeper:",
                        "StoreID is not exist", 1, storeList.size());
                //Loop through all store in list of storeKeeper
                for (Storekeeper storekeeper : storeList) {
                    /*If storeKeeperChoice matches with storeID then add into
                    product*/
                    if (storekeeper.getStorekeeperID() == storeKeeperChoice) {
                        store = new Storekeeper(storekeeper.getStorekeeperID(),
                                storekeeper.getStorekeeperName());
                        break;
                    }
                }
                break;
            } while (true);
        }

        Date receiptDate;
        System.out.println("Enter receipt date[dd/MM/yyyy]:");
        /*Loop until user enter a date of receipt with correct date format 
        and after date of manufacture and before date of expiry*/
        do {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            receiptDate = getData.getDate("");
            String DateOfManu = dateFormat.format(dateOfManu);
            String ExpiryDate = dateFormat.format(expiryDate);
            //Compare receiptDate with dateOfManu and expiryDate
            if (receiptDate.before(dateOfManu) || receiptDate.after(expiryDate)) {
                System.err.println("Receipt date must be from " + DateOfManu
                        + " to " + ExpiryDate);
            } else {
                break;
            }
        } while (true);

        Product newProduct = new Product(productID, productName, productLocation,
                priceProduct, expiryDate, dateOfManu, category, store,
                receiptDate);
        productList.add(newProduct);
        displayList.displayProductList(productList);
    }

    public void updateProduct(ArrayList<Storekeeper> storeList,
            ArrayList<Product> productList) {
        //If the list of product is empty
        if (productList.isEmpty()) {
            System.err.println("The list of product is empty. Cannot update"
                    + " product!");
            return;
        }
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        displayList.displayProductList(productList);
        Product productUpdate = null;
        //Loop until user choose an ID that exist to update
        do {
            int productChoice = getData.getInt("Enter ID of product you want to "
                    + "update:", "Product ID is not exist", 1, Integer.MAX_VALUE);
            //Loop through all product in list of product
            for (Product product : productList) {
                //If productChoice matches with productID in list of product
                if (productChoice == product.getProductID()) {
                    productUpdate = product;
                    break;
                }
            }
            //Check if product id that user input is not found
            if (productUpdate == null) {
                System.err.println("Product ID is not exist");
            } else {
                break;
            }
        } while (true);
        updateProductChoice(productList, storeList, productUpdate);
    }

    private void updateProductChoice(ArrayList<Product> productList,
            ArrayList<Storekeeper> storeList, Product productUpdate) {
        Display displayList = new Display();
        int choice;
        //Loop until user select an option
        do {
            //1. Display menu
            displayList.displayUpdateMenu();
            //2. Get choice from user
            choice = getMenuChoice(1, 10);
            //3. Perform option base on the user choice
            switch (choice) {
                case 1:
                    //Update prorduct ID
                    updateProductID(productList, productUpdate);
                    break;
                case 2:
                    //Update product Name
                    updateProductName(productList, productUpdate);
                    break;
                case 3:
                    //Update product's Location
                    updateProductLocation(productList, productUpdate);
                    break;
                case 4:
                    //Update product's price
                    updateProductPrice(productList, productUpdate);
                    break;
                case 5:
                    //Update product's expiry date
                    updateExpiryDate(productList, productUpdate);
                    break;
                case 6:
                    //Update product's date of manufacture
                    updateManufactureDate(productList, productUpdate);
                    break;
                case 7:
                    //Update product's category
                    updateProductCategory(productList, productUpdate);
                    break;
                case 8:
                    //Update product's storekeeper
                    updateStoreKeeper(storeList, productList, productUpdate);
                    break;
                case 9:
                    //Update product's reciept date
                    updateReciptDate(productList, productUpdate);
                    break;
                case 10:
                    //Exit
                    break;
            }
        } while (choice != 10);
    }

    //Update product's information methods
    private int getMenuChoice(int min, int max) {
        GetDataInput getData = new GetDataInput();
        return getData.getInt("Enter your choice:", "Input is out of range: "
                + "[" + min + "], " + "[" + max + "]", min, max);
    }

    private void updateProductID(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        System.out.println("Enter product ID:");
        /*Loop until user update a product ID without duplicatte
        current productID in list of product or user's product ID is the same 
        with product update*/
        do {
            int productID = getData.getInt("", "Input cannot less or "
                    + "equal than zero", 1, Integer.MAX_VALUE);

            /*Check if user's productID is the same ID with productUpdate. 
            In case not, then check if user's product id is duplicate in list 
            of product*/
            if (!checkProductIdExist(productList, productID)
                    || productUpdate.getProductID()== productID) {
                productUpdate.setProductID(productID);
                break;
            } else {
                System.err.println("Product ID is duplicate");
            }
        } while (true);
        displayList.displayProductList(productList);
    }

    private void updateProductName(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        String productName = getData.getString("Enter product name:");
        productUpdate.setProductName(productName);
        displayList.displayProductList(productList);
    }

    private void updateProductLocation(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        String location = getData.getString("Enter location:");
        productUpdate.setLocation(location);
        displayList.displayProductList(productList);
    }

    private void updateProductPrice(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        int price = getData.getInt("Enter price", "Price must be a "
                + "positive number", 0, Integer.MAX_VALUE);
        productUpdate.setPrice(price);
        displayList.displayProductList(productList);
    }

    private void updateExpiryDate(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        System.out.print("Enter product's expiry date[dd/MM/yyyy]:");
        /*Loop until user enter a date of expiry with correct date format 
                    and after date of manufacture*/
        do {
            Date expiryDate = getData.getDate("");
            Date dateOfManu = productUpdate.getDateOfManufacture();
            //Compare expiryDate with date of manufacture
            if (expiryDate.before(dateOfManu)) {
                System.err.println("Expiry date must be after date "
                        + "of manufacture.");
            } else {
                productUpdate.setExpiryDate(expiryDate);
                break;
            }
        } while (true);
        displayList.displayProductList(productList);
    }

    private void updateManufactureDate(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        System.out.print("Enter product's manufacture date[dd/MM/yyyy]:");
        /*Loop until user enter a date of manufacture with correct 
                    date format and before current date*/
        do {
            Date dateOfManu = getData.getDate("");
            Date currentTimeNow = new Date();
            //Compare date of maniplulate with today
            if (dateOfManu.after(currentTimeNow)) {
                System.err.println("Date of manufacture must be in "
                        + "the past.");
            } else {
                productUpdate.setDateOfManufacture(dateOfManu);
                break;
            }
        } while (true);
        displayList.displayProductList(productList);
    }

    private void updateProductCategory(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        String category = getData.getString("Enter category:");
        productUpdate.setCategory(category);
        displayList.displayProductList(productList);
    }

    private void updateStoreKeeper(ArrayList<Storekeeper> storeList,
            ArrayList<Product> productList, Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayStoreList = new Display();

        displayStoreList.displayStorekeeper(storeList);
        //Loop until user select an option to choose a storeKeeperID
        do {
            int storeKeeperChoice = getData.getInt("Choose Storekeeper:",
                    "StoreID is not exist", 1, storeList.size());
            //Loop through all store in list of storeKeeper
            for (Storekeeper storekeeper : storeList) {
                /*If storeKeeperChoice matches with storeID then add into
                    product*/
                if (storekeeper.getStorekeeperID() == storeKeeperChoice) {
                    productUpdate.setStoreKeeper(storekeeper);
                    break;
                }
            }
            break;
        } while (true);
        displayStoreList.displayProductList(productList);
    }

    private void updateReciptDate(ArrayList<Product> productList,
            Product productUpdate) {
        GetDataInput getData = new GetDataInput();
        Display displayList = new Display();

        System.out.print("Enter receipt date[dd/MM/yyyy]:");
        /*Loop until user enter a date of receipt with correct date 
                    format and after date of manufacture and before date of 
                    expiry*/
        do {
            Date receiptDate = getData.getDate("");
            Date dateOfManu = productUpdate.getDateOfManufacture();
            Date expiryDate = productUpdate.getExpiryDate();
            //Compare receiptDate with dateOfManu and expiryDate
            if (receiptDate.before(dateOfManu)
                    || receiptDate.after(expiryDate)) {
                System.err.println("Receipt date must be from "
                        + dateOfManu + " to " + expiryDate);
            } else {
                break;
            }
        } while (true);
        displayList.displayProductList(productList);
    }

    //Search Methods
    public void searchProduct(ArrayList<Product> productList) {
        //Check if list of product is empty
        if (productList.isEmpty()) {
            System.err.println("List of product is empty. Please enter product"
                    + " to continue searching");
        } else {
            Display dislayList = new Display();

            dislayList.displaySearchMenu();
            int choice = getMenuChoice(1, 4);
            switch (choice) {
                case 1:
                    //Search product by name
                    searchByName(productList);
                    break;
                case 2:
                    //Search product by category
                    searchByCategory(productList);
                    break;
                case 3:
                    //Search product by storekeerp
                    searchByStorekeeper(productList);
                    break;
                case 4:
                    //Search product by receipt date
                    searchByReceiptdate(productList);
                    break;
                case 5:
                    //Exix
                    break;
            }
        }
    }

    //Searching methods
    private void searchByName(ArrayList<Product> productList) {
        GetDataInput getData = new GetDataInput();
        ArrayList<Product> resultList = new ArrayList<>();
        Display displayProductList = new Display();
        displayProductList.displayProductList(productList);

        System.out.println("Enter product name you want to searching:");
        //Loop until user search a product name that exist in list of product
        do {
            String searchProductName = getData.getString("");
            /*Loop through every elements of productList from the first to 
                the last one*/
            for (Product product : productList) {
                //Check if user's productName is exist in list of product or not
                if (searchProductName.equalsIgnoreCase(product.getProductName())) {
                    resultList.add(product);
                }
            }
            /*Check if resultList is empty*/
            if (resultList.isEmpty()) {
                System.err.println("Not existed.");
            } else {
                break;
            }
        } while (true);
        displayProductList.displayProductList(resultList);
    }

    private void searchByCategory(ArrayList<Product> productList) {
        GetDataInput getData = new GetDataInput();
        ArrayList<Product> resultList = new ArrayList<>();
        Display displayProductList = new Display();
        displayProductList.displayProductList(productList);

        System.out.println("Enter product name you want to searching:");
        do {
            String searchCategory = getData.getString("");
            /*Loop through every elements of productList from the first to 
                the last one*/
            for (Product product : productList) {
                //Check if user's productName is exist in list of product
                if (searchCategory.equalsIgnoreCase(product.getCategory())) {
                    resultList.add(product);
                }
            }
            /*Check if resultList is empty*/
            if (resultList.isEmpty()) {
                System.err.println("Not existed.");
            } else {
                displayProductList.displayProductList(resultList);
                break;
            }
        } while (true);
    }

    private void searchByStorekeeper(ArrayList<Product> productList) {
        GetDataInput getData = new GetDataInput();
        ArrayList<Product> resultList = new ArrayList<>();
        Display displayProductList = new Display();
        displayProductList.displayProductList(productList);

        System.out.println("Enter storekeeper name you want to searching:");

        do {
            String storeKeeperName = getData.getString("");
            /*Loop through every elements of productList from the first to 
                the last one*/
            for (Product product : productList) {
                //Check if user's storeKeeperName is exist in list of product
                if (product.getStoreKeeper().getStorekeeperName()
                        .equalsIgnoreCase(storeKeeperName)) {
                    resultList.add(product);
                }
            }

            //Check if resultList is empty
            if (resultList.isEmpty()) {
                System.err.println("No existed.");
            } else {
                displayProductList.displayProductList(resultList);
                break;
            }
        } while (true);
    }

    private void searchByReceiptdate(ArrayList<Product> productList) {
        GetDataInput getData = new GetDataInput();
        ArrayList<Product> resultList = new ArrayList<>();
        Display displayProductList = new Display();
        displayProductList.displayProductList(productList);

        System.err.println("Enter receipt date you want to searching[dd/MM/yyyy]:");

        do {
            Date searchReceiptDate = getData.getDate("");
            /*Loop through every elements of productList from the first to 
                the last one*/
            for (Product product : resultList) {
                //Check if user's receiptDate is exist in list of product
                if (product.getExpiryDate().equals(searchReceiptDate)) {
                    resultList.add(product);
                }
            }
            if (resultList.isEmpty()) {
                System.err.println("Product is not found.");
            } else {
                displayProductList.displayProductList(resultList);
                break;
            }
        } while (true);
    }

    public void sortProduct(ArrayList<Product> productList) {
        //If the list of product is empty
        if (productList.isEmpty()) {
            System.err.println("List of product is empty. Cannot sort product");
        } else {
            Display displayProduct = new Display();
            Collections.sort(productList, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    /*Compare expiry date of o1 with expiry date of o2. 
                    If expiryDate is equals then compare date of manufacture*/
                    if (o1.getExpiryDate().equals(o2.getExpiryDate())) {
                        return o1.getDateOfManufacture().compareTo(o2.getDateOfManufacture());
                    }//Otherwise, compare expiry date 
                    else {
                        return o1.getExpiryDate().compareTo(o2.getExpiryDate());
                    }
                }
            });
            displayProduct.displayProductList(productList);
        }
    }

    private boolean checkProductIdExist(ArrayList<Product> productList,
            int productID) {
        boolean isDuplicate = false;
        //Loop through every elements of productList from the first to the last one
        for (Product product : productList) {
            //Check if user's StoreID is exist in list of product or not
            if (productID == product.getProductID()) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }
}
