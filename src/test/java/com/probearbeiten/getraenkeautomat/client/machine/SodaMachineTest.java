package com.probearbeiten.getraenkeautomat.client.machine;

import com.probearbeiten.getraenkeautomat.client.machine.bottle.Bottle;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.ColaBottle;
import com.probearbeiten.getraenkeautomat.client.machine.setup.SodaMachineSetup;
import com.probearbeiten.getraenkeautomat.client.money.TenCentCoin;
import junit.framework.TestCase;

/**
 * @author tegberts
 */
public class SodaMachineTest extends TestCase {

    SodaMachine sodaMachine;

    public void setUp() throws Exception {
        super.setUp();

        // Underlying model of the soda machine
        this.sodaMachine = new SodaMachine();

        SodaMachineUpdateHandler updateHandler = new SodaMachineUpdateHandler() {
            @Override
            public void onOrderUpdate(Bottle bottle) {

            }

            @Override
            public void onCoinUpdate(Bottle bottle, double dueMoney, double payedMoney) {

            }

            @Override
            public void onChaingeUpdate(double dueMoney) {

            }
        };

        // Setup view
        SodaMachineTestView view = new SodaMachineTestView(sodaMachine, updateHandler);

        // Connect view and model
        sodaMachine.setUpdateHandler(view.getUpdateHandler());

        // Setup inventory etc
        SodaMachineSetup.setup(sodaMachine);

    }

    public void tearDown() throws Exception {
        this.sodaMachine = null;
    }

    public void testSetup() throws Exception {
        // Setup inventory etc
        SodaMachineSetup.setup(this.sodaMachine);
    }

    public void testStart() throws Exception {
        this.sodaMachine.start();
    }

    public void testGetInventory() throws Exception {

    }

    public void testOrderBottle() throws Exception {
        ColaBottle bottle = new ColaBottle();
        this.sodaMachine.orderBottle(bottle);
        assertTrue(this.sodaMachine.getBottleOrdered().equals(bottle));
    }

    public void testInsertCoin() throws Exception {
        TenCentCoin coin = new TenCentCoin();
        this.sodaMachine.insertCoin(coin);
        assertTrue(this.sodaMachine.getPayedValue() == coin.getValue());
    }
}