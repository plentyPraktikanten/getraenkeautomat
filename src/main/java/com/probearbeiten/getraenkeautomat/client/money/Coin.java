package com.probearbeiten.getraenkeautomat.client.money;

/**
 * Parent class for all coins.
 *
 * @author tegberts
 */
public class Coin {
    private double value = 0.0;

    public double getValue() {
        return value;
    }

    protected void setValue(double value) {
        this.value = value;
    }
}
