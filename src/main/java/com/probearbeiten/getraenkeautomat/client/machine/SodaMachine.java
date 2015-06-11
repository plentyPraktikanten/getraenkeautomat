package com.probearbeiten.getraenkeautomat.client.machine;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.thirdparty.javascript.jscomp.parsing.parser.util.Timer;
import com.google.gwt.user.client.Window;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.Bottle;
import com.probearbeiten.getraenkeautomat.client.machine.inventory.Inventory;
import com.probearbeiten.getraenkeautomat.client.money.Coin;

import java.text.DecimalFormat;
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
     * Used to update the view.
     */
    private SodaMachineUpdateHandler updateHandler;

    /**
     * Starts the soda machine.
     */
    public void start() {

        this.updateOrder();
        this.updateDueMoney();

    }

    /**
     *
     * @param updateHandler Used to update the view.
     */
    public void setUpdateHandler(SodaMachineUpdateHandler updateHandler) {
        this.updateHandler = updateHandler;
    }

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
        this.updateOrder();
        this.updateDueMoney();
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
        this.updateDueMoney();
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

    private void updateDueMoney() {

        Bottle bottleOrdered = this.getBottleOrdered();

        double dueMoney = 0;


        if(bottleOrdered != null) {
            double x = dueMoney;
            dueMoney = bottleOrdered.getPrice() - this.getPayedValue();
        }

        /*if(dueMoney < 0){
            dueMoney -= dueMoney;
        }*/

        String value = NumberFormat.getFormat("00.00").format(dueMoney);


        this.updateHandler.onCoinUpdate(this.getBottleOrdered(), Double.valueOf(value), this.getPayedValue());

        this.updateHandler.onChaingeUpdate(dueMoney);
    }

    private void updateOrder() {
        this.updateHandler.onOrderUpdate(this.getBottleOrdered());
    }




    public void reset() {
        this.coins.clear();
        this.bottleOrdered = null;
        this.start();
    }

    }








