package com.probearbeiten.getraenkeautomat.client.machine.view.constants;

import com.google.gwt.dev.shell.BrowserChannel;
import com.google.gwt.i18n.client.Messages;

/**
 * Created by praktikant on 18.06.15.
 */
public interface SodaMachineText extends Messages

{
    String abbrechen();
    String nehmen();
    String wechselgeld(String wechselmoney);
    String nurwechselgeld();
    String leer();
    String wählen();
    String gewählt();
    String rest(String money);
    String nurrest();
    String keinrest();
    String keinwechselgeld();
    String zehn();
    String zwanzig();
    String fünfzig();
    String ein();
    String zwei();
}
