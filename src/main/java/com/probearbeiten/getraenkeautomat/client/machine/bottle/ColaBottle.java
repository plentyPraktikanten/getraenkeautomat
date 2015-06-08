package com.probearbeiten.getraenkeautomat.client.machine.bottle;

/**
 * @author tegberts
 */
public class ColaBottle extends Bottle
{
    public static final String NAME = "Cola";

    public ColaBottle() {
        this.setName(NAME);
        this.setPrice(0.80);
    }
}
