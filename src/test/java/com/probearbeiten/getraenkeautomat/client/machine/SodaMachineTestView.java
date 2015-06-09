package com.probearbeiten.getraenkeautomat.client.machine;

import com.probearbeiten.getraenkeautomat.client.machine.view.SodaMachineView;

/**
 * @author tegberts
 */
public class SodaMachineTestView implements SodaMachineView {

    private SodaMachineUpdateHandler sodaMachineUpdateHandler;

    public SodaMachineTestView(SodaMachine sodaMachine, SodaMachineUpdateHandler sodaMachineUpdateHandler) {
        this.sodaMachineUpdateHandler = sodaMachineUpdateHandler;
    }

    @Override
    public SodaMachineUpdateHandler getUpdateHandler() {
        return this.sodaMachineUpdateHandler;
    }
}
