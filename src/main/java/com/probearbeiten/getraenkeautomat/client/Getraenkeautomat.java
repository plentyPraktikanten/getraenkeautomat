package com.probearbeiten.getraenkeautomat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.probearbeiten.getraenkeautomat.client.machine.SodaMachine;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.ColaBottle;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.FantaBottle;
import com.probearbeiten.getraenkeautomat.client.money.TenCentCoin;
import com.probearbeiten.getraenkeautomat.client.money.TwentyCentCoin;

/**
 * Entry point class.
 */
public class Getraenkeautomat implements EntryPoint {

    /**
     * Underlying logic model for the soda machine
     */
    SodaMachine sodaMachine = new SodaMachine();

    private Label dueMoneyLabel;
    private Label orderLabel;

    /**
     * Setup for the soda machine model
     */
    private void setupSodaMachine() {
        this.sodaMachine.getInventory().setInventory(ColaBottle.NAME, 8);
        this.sodaMachine.getInventory().setInventory(FantaBottle.NAME, 5);
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        RootPanel.get().add(this.getMainPanel());
    }

    private IsWidget getMainPanel() {

        VerticalPanel mainPanel = new VerticalPanel();

        // Show some data displays for the customers

        VerticalPanel displayPanel = new VerticalPanel();

        this.orderLabel = new Label();
        this.dueMoneyLabel = new Label();

        displayPanel.add(this.orderLabel);
        displayPanel.add(this.dueMoneyLabel);
        borderize(displayPanel);
        mainPanel.add(displayPanel);

        // Display bottles that can be selected by the customer

        VerticalPanel bottlePanel = new VerticalPanel();
        bottlePanel.add(this.createColaButton());
        bottlePanel.add(this.createFantaButton());
        borderize(bottlePanel);
        mainPanel.add(bottlePanel);

        // Show some buttons for money insertion etc

        VerticalPanel controlPanel = new VerticalPanel();
        controlPanel.add(this.createEjectMoneyButton());
        borderize(controlPanel);
        mainPanel.add(controlPanel);

        // For the money

        HorizontalPanel moneyPanel = new HorizontalPanel();
        moneyPanel.add(this.createTenCentCoinButton());
        moneyPanel.add(this.createTwentyCentCoinButton());
        borderize(moneyPanel);
        mainPanel.add(moneyPanel);

        // Update the displays

        this.updateOrderDisplay();
        this.updateDueMoneyDisplay();

        // Setup inventory etc

        this.setupSodaMachine();

        return mainPanel;
    }

    /**
     *
     * @return Buttonf or Cola
     */
    private IsWidget createColaButton() {

        Button button = new Button("Cola");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                // TODO check if there is a bottle left
                // sodaMachine.getInventory()...

                sodaMachine.orderBottle(new ColaBottle());
                updateOrderDisplay();
                updateDueMoneyDisplay();

            }
        });

        return button;
    }

    /**
     *
     * @return Button for Fanta
     */
    private IsWidget createFantaButton() {
        Button button = new Button("Fanta");

        // TODO

        return button;
    }

    /**
     *
     * @return Button for money ejection
     */
    private IsWidget createEjectMoneyButton() {
        Button button = new Button("Abbrechen, Geld ausgeben");

        // TODO

        return button;
    }

    /**
     *
     * @return Ten cents button
     */
    private IsWidget createTenCentCoinButton() {

        Button button = new Button(TenCentCoin.VALUE + " €");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                sodaMachine.insertCoin(new TenCentCoin());
                updateDueMoneyDisplay();
            }
        });

        return button;
    }

    /**
     *
     * @return Twenty cents button
     */
    private IsWidget createTwentyCentCoinButton() {

        Button button = new Button(TwentyCentCoin.VALUE + " €");

        // TODO

        return button;
    }

    /**
     * Update the display of what is ordered
     */
    private void updateOrderDisplay() {

        if (this.sodaMachine.getBottleOrdered() != null) {
            this.orderLabel.setText(    "Getränk ausgewählt: " + this.sodaMachine.getBottleOrdered().getName() +
                                        " (" + this.sodaMachine.getBottleOrdered().getPrice() + " €)");
        } else {
            this.orderLabel.setText("Getränk: Bitte wählen …");
        }

    }

    /**
     * Update the money to be paid
     */
    private void updateDueMoneyDisplay() {

        double dueMoney = 0.0;

        if (this.sodaMachine.getBottleOrdered() != null) {
            dueMoney = this.sodaMachine.getBottleOrdered().getPrice() - this.sodaMachine.getPayedValue();
        }

        this.dueMoneyLabel.setText("Restbetrag: " + String.valueOf(dueMoney) + " €");
    }

    /**
     * Styling
     *
     * @param widget
     */
    private static void borderize(IsWidget widget) {
        widget.asWidget().getElement().getStyle().setBorderWidth(3, Style.Unit.PX);
        widget.asWidget().getElement().getStyle().setMargin(6, Style.Unit.PX);
    }
}
