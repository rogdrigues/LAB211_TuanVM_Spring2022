/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0105;
import java.util.Date;
/**
 *
 * @author phong
 */
public class Product {
    private int productID;
    private String productName;
    private String location;
    private int price;
    private Date expiryDate;
    private Date dateOfManufacture;
    private String category;
    private Storekeeper storeKeeper;
    private Date receiptDate;

    public Product() {
    }

    public Product(int productID, String productName, String location, int price, 
            Date expiryDate, Date dateOfManufacture, String category, 
            Storekeeper storeKeeper, Date receiptDate) {
        this.productID = productID;
        this.productName = productName;
        this.location = location;
        this.price = price;
        this.expiryDate = expiryDate;
        this.dateOfManufacture = dateOfManufacture;
        this.category = category;
        this.storeKeeper = storeKeeper;
        this.receiptDate = receiptDate;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Storekeeper getStoreKeeper() {
        return storeKeeper;
    }

    public void setStoreKeeper(Storekeeper storeKeeper) {
        this.storeKeeper = storeKeeper;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

}
