package com.probearbeiten.getraenkeautomat.client.machine.bottle;

/**
 * Created by prakikant on 09.06.15.
 */
public class WaterBottle extends Bottle
{
	public static final String NAME = "Water";

	public WaterBottle() {
		this.setName(NAME);
		this.setPrice(0.40);
	}
}