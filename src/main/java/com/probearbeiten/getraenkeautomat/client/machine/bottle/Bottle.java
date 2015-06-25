package com.probearbeiten.getraenkeautomat.client.machine.bottle;

/**
 * Parent class for all bottles.
 *
 * @author tegberts
 */
public class Bottle {

    private String name = "";
    private double price = 0.00;

    /**
     * The beverage's name, e.g. "Cola". <b>The name has to be unique!</b>
     */
    public String getName(){
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @param name The beverage's name, e.g. "Cola"
     */
    protected void setName(String name){
        this.name = name;
    }
}