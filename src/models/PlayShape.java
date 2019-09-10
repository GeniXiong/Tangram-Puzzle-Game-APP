package models;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

import utils.Moves;

/**
 * The PlayShape holds the current status of the shape
 * and all the history status of the shape, so it can
 * operate undo, redo, rotate, flip
 * @author genixiong
 *
 */
public class PlayShape {
	private Polygon polygon;
	private Color color;
	private boolean canChange;
	
	public PlayShape(Polygon p, Color c) {
		this.polygon = p;
		this.color = c;
		this.canChange = true;
	}
	
	public PlayShape(Polygon p) {
		this.polygon = p;
		this.color = Color.gray;
		this.canChange = false;
	}
	
	public Color color() {
		return this.color;
	}
	
	public void color(Color c) {
		this.color = c;
	}
	
	public Polygon rightRotate() {
		if (this.canChange) {
			return this.rotate(15);
		}
		return this.polygon;
	}
	
	public Polygon leftRotate() {
		if (this.canChange) {
			return this.rotate(-15);
		}
		return this.polygon;
	}
	
	public Polygon rotate(int radian) {
		if (this.canChange) {
			Polygon prev = this.polygon;
			this.polygon = Moves.rotate(prev, radian);
		}
		return this.polygon;
		
	}
	
	public Polygon hFlip() {
		if (this.canChange) {
			Polygon prev = this.polygon;
			this.polygon = Moves.hFlip(prev);
		}
		return this.polygon;
	}
	
	public Polygon vFlip() {
		if (this.canChange) {
			Polygon prev = this.polygon;
			this.polygon = Moves.vFlip(prev);
		}
		return this.polygon;
	}
	
	public Polygon moveToPosition(int x, int y) {
		this.polygon.translate(x, y);
		return this.polygon;
	}
	
	public Polygon shape() {
		return this.polygon;
	}
	
	public Polygon changeTo(Polygon p) {
		this.polygon = p;
		return this.polygon;
	}

	/**
	 * 
	 * @param p
	 * @return
	 */
	public boolean equals(PlayShape p) {
		if (this.polygon.npoints != p.shape().npoints) {
			return false;
		}
		for (int i = 0; i < p.polygon.npoints; i++) {
			Point thisP = new Point(p.polygon.xpoints[i], p.polygon.ypoints[i]);
			if (!this.containsPoint(thisP)) {
				return false;
			}
		}
		
		for (int i = 0; i < p.polygon.npoints; i++) {
			Point thisP = new Point(this.polygon.xpoints[i], this.polygon.ypoints[i]);
			if (!p.containsPoint(thisP)) {
				return false;
			}
		}
		System.out.println("shape equal!");
		return true;
	}
	
	private boolean containsPoint(Point p) {
		for (int i = 0; i < this.polygon.npoints; i++) {
			Point check = new Point(this.polygon.xpoints[i], this.polygon.ypoints[i]);
			if (pointEquals(p, check)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean pointEquals(Point p, Point q) {
		if (p.x >= q.x - 10 && p.x <= q.x + 10) {
			if (p.y >= q.y - 10 && p.y <= q.y + 10) {
				return true;
			}
		}
		return false;
	}

	
	
}
