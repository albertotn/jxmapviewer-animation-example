package org.jxmapviewer.animation.example;

import java.awt.Color;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import de.anormalmedia.vividswinganimations.api.Moveable;

public class MyWaypoint extends DefaultWaypoint implements Moveable {
    private final String label;
    private final Color color;

    /**
     * @param label
     *            the text
     * @param color
     *            the color
     * @param coord
     *            the coordinate
     */
    public MyWaypoint(String label, Color color, GeoPosition coord) {
	super(coord);
	this.label = label;
	this.color = color;
    }

    /**
     * @return the label text
     */
    public String getLabel() {
	return label;
    }

    /**
     * @return the color
     */
    public Color getColor() {
	return color;
    }

    public double getLocationX() {
	return getPosition().getLatitude();
    }

    public void setLocationX(double x) {
	GeoPosition p = getPosition();
	p = new GeoPosition(x, p.getLongitude());
	setPosition(p);
    }

    public double getLocationY() {
	return getPosition().getLongitude();
    }

    public void setLocationY(double y) {
	GeoPosition p = getPosition();
	p = new GeoPosition(p.getLatitude(), y);
	setPosition(p);
    }

}
