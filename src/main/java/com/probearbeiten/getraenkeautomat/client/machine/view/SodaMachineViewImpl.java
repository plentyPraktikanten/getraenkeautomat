package com.probearbeiten.getraenkeautomat.client.machine.view;

import com.google.gwt.dev.shell.BrowserChannel;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import com.probearbeiten.getraenkeautomat.client.machine.SodaMachine;
import com.probearbeiten.getraenkeautomat.client.machine.SodaMachineUpdateHandler;
import com.probearbeiten.getraenkeautomat.client.machine.bottle.*;
import com.probearbeiten.getraenkeautomat.client.machine.inventory.Inventory;
import com.probearbeiten.getraenkeautomat.client.money.*;

import java.util.Iterator;

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
    private Label changeLabel;

    //constructor
    public SodaMachineViewImpl(final SodaMachine sodaMachine) {

        assert sodaMachine != null : "Soda machine has to be initialized!";
        this.sodaMachine = sodaMachine;

        // create vertical panel for all other panels
        VerticalPanel mainPanel = new VerticalPanel();

        // Show some data displays for the customers

        // create vertical panel for order and dueMoney labels
        VerticalPanel displayPanel = new VerticalPanel();

        //init labels
        this.orderLabel = new Label();    // getränk wählen
        this.dueMoneyLabel = new Label(); // restbetrag
        this.changeLabel = new Label();   // wechsel geld

        //add order and due money labels to display panel
        displayPanel.add(this.orderLabel);
        displayPanel.add(this.dueMoneyLabel);
        displayPanel.add(this.changeLabel);

        //add border to display panel
        borderize(displayPanel);

        //WICHTIG: display panel wird dem mainpanel geadded
        mainPanel.add(displayPanel);

        //Create VerticalPanel for take button
        VerticalPanel takePanel = new VerticalPanel();

        //create and add take button
        takePanel.add(this.createTakeButton());

        borderize(takePanel);

        //WICHTIG: takePanel wird dem mainpanel geadded
        mainPanel.add(takePanel);

        // Display bottles that can be selected by the customer

        //create panel for bottles
        VerticalPanel bottlePanel = new VerticalPanel();

        //create and add cola and fanta button to bottle panel
        bottlePanel.add(this.createColaButton());
        bottlePanel.add(this.createFantaButton());
        bottlePanel.add(this.createMezzoMixButton());
        bottlePanel.add(this.createSpriteButton());
        bottlePanel.add(this.createWaterButton());

        //add border
        borderize(bottlePanel);

        //WICHTIG bottle panel dem main panel adden
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
        moneyPanel.add(this.createFiftyCentCoinButton());
        moneyPanel.add(this.createOneEuroCoinButton());
        moneyPanel.add(this.createTwoEuroCoinButton());
        borderize(moneyPanel);
        mainPanel.add(moneyPanel);


        // Set widget for this composite

        this.initWidget(mainPanel);

        // Init update handler that reacts to model changes

        this.updateHandler = new SodaMachineUpdateHandler() {

            @Override
            public void onOrderUpdate(Bottle bottle) {
                if (bottle != null) {
                    orderLabel.setText("Getränk ausgewählt: " + bottle.getName() +
                            " (" + bottle.getPrice() + " €)");
                } else {
                    orderLabel.setText("Getränk: Bitte wählen …");
                }
            }

            @Override
            public void onCoinUpdate(Bottle bottle, double dueMoney, double payedMoney) {

                dueMoneyLabel.setText("Restbetrag: " + String.valueOf(dueMoney) + " €");

            }

            @Override
            public void onChangeUpdate(double dueMoney) {

                if (dueMoney < 0) {

                    double value = Math.round((sodaMachine.getPayedValue() - sodaMachine.getBottleOrdered().getPrice()) * 100.0) / 100.0;

                    changeLabel.setText("Wechselgeld: " + String.valueOf(value) + " €");
                    dueMoneyLabel.setText("Restbetrag: 0 €");

                } else {


                    changeLabel.setText("Wechselgeld: 0 €");

                }
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

    private IsWidget createTakeButton() {
        //create take button
        Button button = new Button("Getränk und Wechselgeld nehmen");

        //add click handler to button
        button.addClickHandler(
            new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    if (sodaMachine.getPayedValue() >= sodaMachine.getBottleOrdered().getPrice()){

                        sodaMachine.getInventory().decreaseInventory(sodaMachine.getBottleOrdered().getName());

                        sodaMachine.reset();

                    }
                }
            }
        );

        return button;
    }

    /**
     * @return Button for Cola
     */
    private IsWidget createColaButton() {

        //create cola button
        Button button = new Button("Cola");

        //add click handler to button
        button.addClickHandler(
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        if (sodaMachine.getInventory().getInventory("Cola") == 0)
                        {
                            orderLabel.setText("Ist leer");
                        } else {
                            sodaMachine.orderBottle(new ColaBottle());
                        }
//                        sodaMachine.reset();

                    }
                });

        return button;
    }


    private void updateAvailable() {
    }

    /**
     * @return Button for Fanta
     */
    private IsWidget createFantaButton() {

        Button button = new Button("Fanta");

        button.addClickHandler(
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        if (sodaMachine.getInventory().getInventory("Fanta") == 0)
                        {
                            orderLabel.setText("Ist leer");
                        } else {
                            sodaMachine.orderBottle(new FantaBottle());
                        }

                    }
                });

        return button;
    }

    private IsWidget createMezzoMixButton() {
        Button button = new Button("MezzoMix");


        button.addClickHandler(
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        if (sodaMachine.getInventory().getInventory("MezzoMix") == 0)
                        {
                            orderLabel.setText("Ist leer");
                        } else {
                            sodaMachine.orderBottle(new MezzoMixBottle());
                        }

                    }
                });

        return button;
    }

    private IsWidget createSpriteButton() {
        Button button = new Button("Sprite");

        button.addClickHandler(
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        if (sodaMachine.getInventory().getInventory("Sprite") == 0)
                        {
                            orderLabel.setText("Ist leer");
                        } else {
                            sodaMachine.orderBottle(new SpriteBottle());
                        }

                    }
                });

        return button;
    }

    private IsWidget createWaterButton() {
        Button button = new Button("Wasser");

        button.addClickHandler(
                new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {

                        if (sodaMachine.getInventory().getInventory("Sprite") == 0)
                        {
                            orderLabel.setText("Ist leer");
                        } else {
                            sodaMachine.orderBottle(new WaterBottle());
                        }

                    }
                });

        return button;
    }

    /**
     * @return Button for money ejection
     */
    private IsWidget createEjectMoneyButton() {
        Button button = new Button("Abbrechen, Geld ausgeben");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                sodaMachine.reset();

            }
        });

        return button;
    }


    /**
     * @return Ten cents button
     */
    private IsWidget createTenCentCoinButton() {

        Button button = new Button(TenCentCoin.VALUE + " €");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {

                if (sodaMachine.getBottleOrdered() == null) {} else {

                    sodaMachine.insertCoin(new TenCentCoin());

                }
            }
        });

        return button;
    }

    /**
     * @return Twenty cents button
     */
    private IsWidget createTwentyCentCoinButton() {

        Button button = new Button(TwentyCentCoin.VALUE + " €");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (sodaMachine.getBottleOrdered() == null) {} else {
                    sodaMachine.insertCoin(new TwentyCentCoin());
                }
            }
        });

        return button;
    }

    private IsWidget createFiftyCentCoinButton() {

        Button button = new Button(FiftyCentCoin.VALUE + " €");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (sodaMachine.getBottleOrdered() == null) {} else {
                    sodaMachine.insertCoin(new FiftyCentCoin());
                }
            }
        });

        return button;
    }

    private IsWidget createOneEuroCoinButton() {
        Button button = new Button(OneEuroCoin.VALUE + " €");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (sodaMachine.getBottleOrdered() != null)
                {
                    sodaMachine.insertCoin(new OneEuroCoin());
                }
            }
        });

        return button;
    }

    private IsWidget createTwoEuroCoinButton() {
        Button button = new Button(TwoEuroCoin.VALUE + " €");

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                if (sodaMachine.getBottleOrdered() == null) {} else {
                    sodaMachine.insertCoin(new TwoEuroCoin());
                }
            }
        });

        return button;
    }

    @Override
    public SodaMachineUpdateHandler getUpdateHandler() {
        return this.updateHandler;
    }
}