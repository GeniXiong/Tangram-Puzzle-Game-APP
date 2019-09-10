package models;


import utils.FilePath;

import java.awt.Point;
import java.awt.Polygon;

import utils.BuildShapes;

/**
 * This class holds the standard solution for this puzzle
 * @author genixiong
 *
 */
public class Shapeset {
	private String shapesetName;
	private String puzzleName;
	private PlayShape[] shapes;
	private boolean canChange;
	
	public Shapeset(String shapesetName, String puzzleName, boolean canChange) {
		this.shapesetName = shapesetName;
		this.puzzleName = puzzleName;
		this.canChange = canChange;
		if (canChange) {
			this.initShapes();
		}
		else {
			this.initSolution();
		}		
	}
	
	/**
	 * initiate the solution space of 7 shapes with color set to gray
	 */
	private void initSolution() {
		String solutionPath = FilePath.getPuzzlePath(this.shapesetName, this.puzzleName);
		System.out.println("solutionPath = " + solutionPath);
		this.shapes = BuildShapes.buildPlayShapes(solutionPath);
	}
	
	private void initShapes() {
		String shapesetPath = FilePath.getShapesetPath(this.shapesetName);
		System.out.println("shapesetPath = " + shapesetPath);
		this.shapes = BuildShapes.buildPlayShapes(shapesetPath);
	}
	
	public String shapesetName() {
		return this.shapesetName;
	}
	
	public String puzzleName() {
		return this.puzzleName;
	}
	
	public PlayShape[] shapes() {
		return this.shapes;
	}
	
	public void setShapes(PlayShape[] polys) {
		this.shapes = polys;
	}
	
	public boolean solutionOrNot() {
		if (this.canChange) {
			return false;
		}
		return true;
	}
	
	public boolean checkShapesetFit(Shapeset otherShapeset) {
		if (!this.shapesetName().equals(otherShapeset.shapesetName()) || 
				!this.puzzleName().equals(otherShapeset.puzzleName())) {
			return false;
		}
		for (PlayShape shape: this.shapes) {
			if (!otherShapeset.containsShape(shape)) {
				return false;
			}
		}
		
		for (PlayShape shape: otherShapeset.shapes) {
			if (!this.containsShape(shape)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean containsShape(PlayShape p) {
		for (PlayShape shape: this.shapes) {
			if (shape.equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	public PlayShape getShape(int index) {
		return this.shapes[index];
	}
	
	public int clickFromPoint(Point p) {
		for (int i = 0; i < this.shapes.length; i++) {
			if (this.shapes[i].shape().contains(p)) {
				return i;
			}
		}
		return -1;
	}
	
	public Polygon rightRotate(int index) {
		return this.shapes[index].rightRotate();
	}
	
	public Polygon leftRotate(int index) {
		return this.shapes[index].leftRotate();
	}
	
	public Polygon hFlip(int index) {
		return this.shapes[index].hFlip();
	}
	
	public Polygon vFlip(int index) {
		return this.shapes[index].vFlip();
	}
	
	public Polygon moveToPosition(int index, Point p) {
		return this.shapes[index].moveToPosition(p.x, p.y);
	}
	
	public Polygon changeShape(int index, Polygon p) {
		return this.shapes[index].changeTo(p);
	}
	
}
