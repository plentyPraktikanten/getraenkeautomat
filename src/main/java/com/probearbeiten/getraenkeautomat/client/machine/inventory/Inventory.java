package com.probearbeiten.getraenkeautomat.client.machine.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the soda machines inventory. It manages the count of bottles in the soda machine.
 *
 * @author tegberts
 */
public class Inventory {
    private HashMap<String, Integer> inventoryList = new HashMap<>();

    public void setInventory(String bottleName, Integer count) {

        Map.Entry<String, Integer> entry = this.getEntry(bottleName);

        if (entry == null) {
            this.inventoryList.put(bottleName, count);
        } else {
            entry.setValue(count);
        }

    }

    /**
     *
     * @param bottleName The bottle's name.
     * @return Count of bottles in the inventory for the given bottle name.
     */
    public Integer getInventory(String bottleName){
        Map.Entry<String, Integer> entry = this.getEntry(bottleName);

        if (entry == null) {
            return 0;
        } else {
            return entry.getValue();
        }
    }

    /**
     * @param bottleName Increases bottle inventory by one.
     */
    public void increaseInventory(String bottleName) {
        this.setInventory(bottleName, this.getInventory(bottleName) + 1);
    }

    /**
     * @param bottleName Decreases bottle inventory by one.
     */
    public void decreaseInventory(String bottleName) {
        this.setInventory(bottleName, this.getInventory(bottleName) - 1);
    }

    private Map.Entry<String, Integer> getEntry(String bottleName) {
        Map.Entry<String, Integer> entry = null;

        for (Map.Entry<String, Integer> tempEntry : this.inventoryList.entrySet()) {
            if (tempEntry.equals(bottleName)) {
                return tempEntry;
            }
        }

        return null;
    }
}
