package utils;

import java.awt.Polygon;

public class Moves {
	public static Polygon rotate(Polygon p, int radian) {
		float t, v;
		int xp, yp;
		double r = Math.toRadians(radian);
		xp = p.xpoints[0];
		yp = p.ypoints[0];
		Polygon changed = new Polygon();
		changed.addPoint(xp, yp);
		for (int i = 1; i < p.npoints - 1; i++) {
			int newx, newy;
			t = p.xpoints[i] - xp;
			v = p.ypoints[i] - yp;
			newx = (int)(xp + t * Math.cos(r) - v * Math.sin(r));
			newy = (int)(yp + v * Math.cos(r) + t * Math.sin(r));
			changed.addPoint(newx, newy);
		}
		changed.addPoint(xp, yp);
		return changed;
	}
	
	public static Polygon hFlip(Polygon p) {
		int x = p.xpoints[0];
		int y = p.ypoints[0];
		Polygon changed = new Polygon();
		changed.addPoint(x, y);
		for (int i = 1; i < p.npoints; i++) {
			changed.addPoint(2 * x - p.xpoints[i], p.ypoints[i]);
		}
		return changed;
	}
	
	public static Polygon vFlip(Polygon p) {
		int x = p.xpoints[0];
		int y = p.ypoints[0];
		Polygon changed = new Polygon();
		changed.addPoint(x, y);
		for (int i = 1; i < p.npoints; i++) {
			changed.addPoint(p.xpoints[i], 2 * y - p.ypoints[i]);
		}
		return changed;
	}

}
