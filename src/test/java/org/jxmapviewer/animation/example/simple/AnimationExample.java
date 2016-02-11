package org.jxmapviewer.animation.example.simple;

import java.awt.Color;
import java.awt.Window.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.animation.example.MapBuilder;
import org.jxmapviewer.animation.example.MyWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import de.anormalmedia.vividswinganimations.api.Moveable;

/**
 * Animation example for jxMapViewer using VividSwingAnimations, move a
 * {@link Moveable} element from a point to another
 */
public class AnimationExample implements Runnable {
    private static JFrame frame;

    private MyWaypoint wayPointToMove;

    public AnimationExample() {
	// Build Swing window
	frame = new JFrame("JXMapviewer animation example");
	frame.setResizable(false);
	frame.setType(Type.UTILITY);
	frame.setSize(790, 640);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel mainPanel = new JPanel();
	mainPanel.setSize(800, 600);
	mainPanel.setLayout(null);

	JScrollPane scrollPanel = new JScrollPane(mainPanel);
	scrollPanel.setSize(800, 800);
	frame.getContentPane().add(scrollPanel);

	// Build map
	GeoPosition frankfurt = new GeoPosition(50, 7, 0, 8, 41, 0);
	GeoPosition wiesbaden = new GeoPosition(50, 5, 0, 8, 14, 0);
	GeoPosition mainz = new GeoPosition(50, 0, 0, 8, 16, 0);
	GeoPosition darmstadt = new GeoPosition(49, 52, 0, 8, 39, 0);
	GeoPosition offenbach = new GeoPosition(50, 6, 0, 8, 46, 0);

	// Create waypoints from the geo-positions
	wayPointToMove = new MyWaypoint("F", Color.ORANGE, frankfurt);
	Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(
		Arrays.asList(wayPointToMove));

	JXMapViewer mapViewer = MapBuilder.buildAnimationExampleMap(
		wayPointToMove, waypoints);

	mainPanel.add(mapViewer);

	// Build button and add mouse listener
	JButton button = new JButton("Animate");
	button.setBounds(671, 566, 101, 32);
	button.addMouseListener(new StartAnimationAdapter(frame,
		wayPointToMove, mainz));
	mainPanel.add(button);
    }

    @Override
    public void run() {
	// display frame
	frame.setVisible(true);
    }

    /**
     * @param args
     *            the program args (ignored)
     */
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new AnimationExample());
    }
}
