package org.jxmapviewer.animation.example.simple;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.jxmapviewer.animation.example.RefreshFrameAdapter;
import org.jxmapviewer.viewer.GeoPosition;

import de.anormalmedia.vividswinganimations.api.Moveable;
import de.anormalmedia.vividswinganimations.bounds.LocationAnimation;
import de.anormalmedia.vividswinganimations.runner.SequentialAnimationRunner;

/**
 * Start animation for {@link Moveable} element to destination
 */
public class StartAnimationAdapter extends MouseAdapter {

    private Moveable element;
    private GeoPosition destination;
    private JFrame frame;

    public StartAnimationAdapter(JFrame frame, Moveable element,
	    GeoPosition destination) {
	this.frame = frame;
	this.element = element;
	this.destination = destination;
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// create an animator
	SequentialAnimationRunner defaultAnimator = new SequentialAnimationRunner();
	// build an animation and set destination
	LocationAnimation locationAnimation = new LocationAnimation(element,
		destination.getLatitude(), destination.getLongitude());
	// add a listener to get some events
	locationAnimation.addAnimationListener(new RefreshFrameAdapter(frame));

	// add animation to animator
	defaultAnimator.addAnimation(locationAnimation);
	// run animator
	defaultAnimator.start();
    }

}
