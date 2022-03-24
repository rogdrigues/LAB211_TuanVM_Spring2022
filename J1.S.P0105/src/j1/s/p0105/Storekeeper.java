/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package j1.s.p0105;

/**
 *
 * @author phong
 */
public class Storekeeper {
    private int storeKeeperID;
    private String storeKeeperName;

    public Storekeeper() {
    }

    public Storekeeper(int StorekeeperID, String StorekeeperName) {
        this.storeKeeperID = StorekeeperID;
        this.storeKeeperName = StorekeeperName;
    }

    public int getStorekeeperID() {
        return storeKeeperID;
    }

    public void setStorekeeperID(int StorekeeperID) {
        this.storeKeeperID = StorekeeperID;
    }

    public String getStorekeeperName() {
        return storeKeeperName;
    }

    public void setStorekeeperName(String StorekeeperName) {
        this.storeKeeperName = StorekeeperName;
    }

    @Override
    public String toString() {
        return storeKeeperName;
    }
    
}
