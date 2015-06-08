package com.probearbeiten.getraenkeautomat.client.machine;

import com.probearbeiten.getraenkeautomat.client.machine.bottle.Bottle;
import com.probearbeiten.getraenkeautomat.client.machine.inventory.Inventory;
import com.probearbeiten.getraenkeautomat.client.money.Coin;

import java.util.ArrayList;

/**
 * Class representing the soda machine.
 *
 * @author tegberts
 */
public class SodaMachine {

    /**
     * The inventory for the bottles. It manages the count of bottles in the soda machine.
     */
    private Inventory inventory = new Inventory();

    /**
     * The coins that are currently inserted.
     */
    private ArrayList<Coin> coins = new ArrayList<>();

    /**
     * The bottle that is currently ordered.
     */
    private Bottle bottleOrdered = null;

    /**
     * @return The inventory for the bottles. It manages the count of bottles in the soda machine.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * @param bottle A bottle.
     */
    public void orderBottle(Bottle bottle) {
        this.bottleOrdered = bottle;
    }

    /**
     * @return The bottle that has been ordered.
     */
    public Bottle getBottleOrdered() {
        return this.bottleOrdered;
    }

    /**
     * @param coin A coin.
     */
    public void insertCoin(Coin coin) {
        this.coins.add(coin);
    }

    /**
     * @return The current value of the sum of inserted coins.
     */
    public double getPayedValue() {
        double result = 0.0;

        for (Coin coin : this.coins) {
            result += coin.getValue();
        }

        return result;
    }
}