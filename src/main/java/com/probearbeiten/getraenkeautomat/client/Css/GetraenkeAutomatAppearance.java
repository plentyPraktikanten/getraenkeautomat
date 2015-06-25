package com.probearbeiten.getraenkeautomat.client.Css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

/**
 * Created by prakikant on 18.06.15.
 */
public class GetraenkeAutomatAppearance
{
	static
	{
		GetraenkeAutomatResource.INSTANCE.css().ensureInjected();
	}

	public interface GetraenkeautomatCss extends CssResource
	{
		String change();
		String buttonGetraenke();
		String buttonEjectTake();
		String buttonMoney();
		String displayPanel();
		String marginMain();
		String orderMoney();

		String primaryPanel();

	}

	interface GetraenkeAutomatResource extends ClientBundle
	{
		public static final GetraenkeAutomatResource INSTANCE = GWT.create(GetraenkeAutomatResource.class);

		@ClientBundle.Source("Getraenkeautomat.css")
		GetraenkeautomatCss css();
	}

	public GetraenkeautomatCss getCss()
	{
		return GetraenkeAutomatResource.INSTANCE.css();
	}
}
