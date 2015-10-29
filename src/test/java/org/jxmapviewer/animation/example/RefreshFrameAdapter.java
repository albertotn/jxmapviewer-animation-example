package org.jxmapviewer.animation.example;

import javax.swing.JFrame;

import de.anormalmedia.vividswinganimations.listener.AnimationAdapter;
import de.anormalmedia.vividswinganimations.listener.AnimationListener;

/**
 * Repaint current thread when animation is finished
 * 
 * @see AnimationAdapter
 * @see AnimationListener
 */
public class RefreshFrameAdapter extends AnimationAdapter {

    private JFrame frame;

    public RefreshFrameAdapter(JFrame frame) {
	this.frame = frame;
    }

    @Override
    public void animationFinished() {
	frame.repaint();
    }

}
