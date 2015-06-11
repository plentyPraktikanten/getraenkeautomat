package com.probearbeiten.getraenkeautomat.client.machine.view;

import com.probearbeiten.getraenkeautomat.client.machine.SodaMachineUpdateHandler;

/**
 * Interface that decouples the view from the logic.
 *
 * @author tegberts
 */
public interface SodaMachineView {

    /**
     *
     * @return The handler for updates on the model.
     */
    SodaMachineUpdateHandler getUpdateHandler();
}