package models;

import java.awt.Polygon;

public class Memo extends Polygon{
	private int index;
	
	public Memo(Polygon p, int index) {
		super(p.xpoints, p.ypoints, p.npoints);
		this.index = index;
	}
	
	public Polygon polygon() {
		return this;
	}
	
	public int shapeIndex() {
		return this.index;
	}
}
