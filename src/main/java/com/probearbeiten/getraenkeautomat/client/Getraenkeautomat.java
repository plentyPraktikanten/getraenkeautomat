package com.probearbeiten.getraenkeautomat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.probearbeiten.getraenkeautomat.client.machine.SodaMachine;
import com.probearbeiten.getraenkeautomat.client.machine.setup.SodaMachineSetup;
import com.probearbeiten.getraenkeautomat.client.machine.view.SodaMachineViewImpl;

/**
 * Entry point class.
 */
public class Getraenkeautomat implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        // Underlying model of the soda machine
        SodaMachine sodaMachine = new SodaMachine();

        // Setup view
        SodaMachineViewImpl view = new SodaMachineViewImpl(sodaMachine);

        // Connect view and model
        sodaMachine.setUpdateHandler(view.getUpdateHandler());

        // Add to the web page's DOM
        RootPanel.get().add(view);

        // Setup inventory etc
        SodaMachineSetup.setup(sodaMachine);

        // Start the machine

       sodaMachine.start();

    }
}