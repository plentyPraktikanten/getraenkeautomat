package com.probearbeiten.getraenkeautomat.client.machine;

import com.probearbeiten.getraenkeautomat.client.machine.bottle.Bottle;

/**
 * Everything that happens at the soda machine and requires an update of the display
 * should be defined as an interface method. This decouples the view from the logic.
 *
 * @author tegberts
 */
public interface SodaMachineUpdateHandler {

    /**
     *
     * @param bottle The bottle that has been ordered.
     */
    public void onOrderUpdate(Bottle bottle);
    /**
     *
     * @param bottle The bottled that has been ordered.
     * @param dueMoney The money that the customer (still) has to pay.
     * @param payedMoney The money the customer has payed until now.
     */
    public void onCoinUpdate(Bottle bottle, double dueMoney, double payedMoney);

    public void  onChaingeUpdate(double dueMoney);
}