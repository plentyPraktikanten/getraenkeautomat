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
    String leer();
    String wählen();
    String gewählt(String name ,String money);
    String rest(String money);
    String coin(String money);
}
