package com.probearbeiten.getraenkeautomat.client.machine.setup;

import com.probearbeiten.getraenkeautomat.client.machine.SodaMachine;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.ColaBottle;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.FantaBottle;

/**
 * Use class to setup the soda machine.
 *
 * @author tegberts
 */
public class SodaMachineSetup {

    /**
     * Setup for the soda machine model
     */
    public static void setup(SodaMachine sodaMachine) {
        sodaMachine.getInventory().setInventory(ColaBottle.NAME, 8);
        sodaMachine.getInventory().setInventory(FantaBottle.NAME, 5);
    }
}
