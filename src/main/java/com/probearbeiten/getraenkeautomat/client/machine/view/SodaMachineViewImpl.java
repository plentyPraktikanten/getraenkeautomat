package com.probearbeiten.getraenkeautomat.client.machine.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.probearbeiten.getraenkeautomat.client.machine.SodaMachine;
import com.probearbeiten.getraenkeautomat.client.machine.SodaMachineUpdateHandler;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.Bottle;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.ColaBottle;
import com.probearbeiten.getraenkeautomat.client.money.TenCentCoin;
import com.probearbeiten.getraenkeautomat.client.money.TwentyCentCoin;

/**
 * Implementation of {@link SodaMachineView}.
 *
 * @author tegberts
 */
public class SodaMachineViewImpl extends Composite implements SodaMachineView {

    private SodaMachine sodaMachine;
    private SodaMachineUpdateHandler updateHandler;

    private Label dueMoneyLabel;
    private Label orderLabel;

    public SodaMachineViewImpl(SodaMachine sodaMachine) {

        assert sodaMachine != null : "Soda machine has to be initialized!";
        this.sodaMachine = sodaMachine;

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



        // Set widget for this composite

        this.initWidget(mainPanel);


        // Init update handler that reacts to model changes

        this.updateHandler = new SodaMachineUpdateHandler() {

            @Override
            public void onOrderUpdate(Bottle bottle) {
                if (bottle != null) {
                    orderLabel.setText(    "Getränk ausgewählt: " + bottle.getName() +
                            " (" + bottle.getPrice() + " €)");
                } else {
                    orderLabel.setText("Getränk: Bitte wählen …");
                }
            }

            @Override
            public void onCoinUpdate(Bottle bottle, double dueMoney, double payedMoney) {
                dueMoneyLabel.setText("Restbetrag: " + String.valueOf(dueMoney) + " €");
            }
        };
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

    @Override
    public SodaMachineUpdateHandler getUpdateHandler() {
        return this.updateHandler;
    }
}
