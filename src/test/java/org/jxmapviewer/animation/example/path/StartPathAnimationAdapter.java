package org.jxmapviewer.animation.example.path;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;

import org.jxmapviewer.animation.example.RefreshFrameAdapter;
import org.jxmapviewer.viewer.GeoPosition;

import de.anormalmedia.vividswinganimations.api.Moveable;
import de.anormalmedia.vividswinganimations.bounds.LocationAnimation;
import de.anormalmedia.vividswinganimations.runner.SequentialAnimationRunner;

/**
 * Move {@link Moveable} element following a list of {@link GeoPosition}
 */
public class StartPathAnimationAdapter extends MouseAdapter {

    private Moveable element;
    private JFrame frame;
    private List<GeoPosition> path;

    public StartPathAnimationAdapter(JFrame frame, Moveable element,
	    List<GeoPosition> path) {
	this.frame = frame;
	this.element = element;
	this.path = path;
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// create an animator
	SequentialAnimationRunner defaultAnimator = new SequentialAnimationRunner();
	// build an animation and set destination
	int t = 0;
	for (GeoPosition gp : path) {
	    LocationAnimation locationAnimation = new LocationAnimation(
		    element, gp.getLatitude(), gp.getLongitude());
	    locationAnimation.setDuration(t);
	    // add a listener to get some events
	    locationAnimation.addAnimationListener(new RefreshFrameAdapter(
		    frame));

	    // add animation to animator
	    defaultAnimator.addAnimation(locationAnimation);

	    t += 500;
	}
	// run animator
	defaultAnimator.start();
    }

}
