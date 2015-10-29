package org.jxmapviewer.animation.example;

import java.io.File;
import java.util.Set;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 * Utility class to build map for example
 */
public final class MapBuilder {

    private MapBuilder() {
    }

    public static final JXMapViewer buildAnimationExampleMap(MyWaypoint start,
	    Set<MyWaypoint> waypoints) {
	// Setup JXMapViewer
	final JXMapViewer mapViewer = new JXMapViewer();
	mapViewer.setBounds(0, 0, 784, 550);

	// Create a TileFactoryInfo for Virtual Earth
	TileFactoryInfo info = new VirtualEarthTileFactoryInfo(
		VirtualEarthTileFactoryInfo.MAP);
	DefaultTileFactory tileFactory = new DefaultTileFactory(info);
	tileFactory.setThreadPoolSize(8);

	mapViewer.setTileFactory(tileFactory);

	// Set the focus
	mapViewer.setZoom(10);

	// Add interactions
	MouseInputListener mia = new PanMouseInputListener(mapViewer);
	mapViewer.addMouseListener(mia);
	mapViewer.addMouseMotionListener(mia);
	mapViewer.addMouseListener(new CenterMapListener(mapViewer));
	mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(
		mapViewer));
	mapViewer.addKeyListener(new PanKeyListener(mapViewer));

	// Setup local file cache
	File cacheDir = new File(System.getProperty("user.home")
		+ File.separator + ".jxmapviewer2");
	LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir,
		false);

	mapViewer.setAddressLocation(start.getPosition());

	// Create a waypoint painter that takes all the waypoints
	WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
	waypointPainter.setWaypoints(waypoints);
	waypointPainter.setRenderer(new MyWayPointRenderer());

	mapViewer.setOverlayPainter(waypointPainter);

	return mapViewer;
    }
}
