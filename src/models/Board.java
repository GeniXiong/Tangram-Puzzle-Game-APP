package models;

import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;

/**
 * The Class Board
 * Entry Point into the Entity domain space.
 * @author weixiong
 */
public class Board {
	
	
	/** The shapeset. */
	public String shapesetName;
	public String puzzleName;
	public Shapeset solutionShapeset;
	public Shapeset playShapeset;
	private ArrayList<Memo> memo;
	private int memoIndex;
	public int selectedIndex;


	/**
	 * Instantiates a new board.
	 */
	public Board() {
		this.memo = new ArrayList<Memo>();
		this.memoIndex = -1;
		this.selectedIndex = 0;
	}
	
	public Board(String shapesetName, String puzzleName) {
		this.shapesetName = shapesetName;
		this.puzzleName = puzzleName;
		this.playShapeset = new Shapeset(shapesetName, puzzleName, true);
		this.solutionShapeset = new Shapeset(shapesetName, puzzleName, false);
		this.memo = new ArrayList<Memo>();
		this.memoIndex = -1;
		this.selectedIndex = 0;
	}
	
	public void setPuzzle(String shapesetName, String puzzleName) {
		this.shapesetName = shapesetName;
		this.puzzleName = puzzleName;
		this.playShapeset = new Shapeset(shapesetName, puzzleName, true);
		this.solutionShapeset = new Shapeset(shapesetName, puzzleName, false);
		this.memo = new ArrayList<Memo>();
		this.memoIndex = -1;
		this.selectedIndex = 0;
	}
	
	/**
	 * Undo the last move by user
	 */
	public void undo() {
		if (this.memoIndex > 0) {
			this.memoIndex -= 1;
			Memo m = memo.get(this.memoIndex);
			this.playShapeset.changeShape(m.shapeIndex(), m.polygon());
		}
	}
	
	/**
	 * Redo the last undo move by user
	 */
	public void redo() {
		if (this.memoIndex < this.memo.size() - 1) {
			this.memoIndex += 1;
			Memo m = memo.get(this.memoIndex);
			this.playShapeset.changeShape(m.shapeIndex(), m.polygon());
		}
	}
	
	public PlayShape selectedShape() {
		if (this.selectedIndex >= 0) {
			return this.playShapeset.getShape(this.selectedIndex);
		}
		return this.playShapeset.getShape(0);
	}
	
	public void rightRotate() {
		PlayShape shape = this.selectedShape();
		Polygon move = shape.rightRotate();
		this.playShapeset.changeShape(this.selectedIndex, move);
		updateMemo(move, this.selectedIndex);
	}
	
	public void leftRotate() {
		PlayShape shape = this.selectedShape();
		Polygon move = shape.leftRotate();
		this.playShapeset.changeShape(this.selectedIndex, move);
		updateMemo(move, this.selectedIndex);
	}
	public void hFlip() {
		PlayShape shape = this.selectedShape();
		Polygon move = shape.hFlip();
		this.playShapeset.changeShape(this.selectedIndex, move);
		updateMemo(move, this.selectedIndex);	
	}
	public void vFlip() {
		PlayShape shape = this.selectedShape();
		Polygon move = shape.vFlip();
		this.playShapeset.changeShape(this.selectedIndex, move);
		updateMemo(move, this.selectedIndex);	
	}
	
	public void updateMemo(Polygon p, int index) {
		if (this.memoIndex < this.memo.size() - 1) {
			this.memo.subList(this.memoIndex + 1, this.memo.size()).clear();
		}
		this.memo.add(new Memo(p, index));
		this.memoIndex = this.memo.size() - 1;
		
	}
	
	public void reset() {
		this.setPuzzle(shapesetName, puzzleName);
	}
	
	public boolean checkFit() {
		return this.playShapeset.checkShapesetFit(this.solutionShapeset) && this.solutionShapeset.checkShapesetFit(this.playShapeset);
	}
	
	public boolean check() {
		System.out.println("start checking!");
		if (this.solutionShapeset.containsShape(this.playShapeset.getShape(6))) {
			System.out.println("success 6");
		}
		return this.solutionShapeset.containsShape(this.playShapeset.getShape(6));
	}
	
	public int clickFromPoint(Point p) {
		if (this.playShapeset.clickFromPoint(p) >= 0) {	
			this.selectedIndex = this.playShapeset.clickFromPoint(p);
		}
		return this.selectedIndex;
	}

	public void moveToPosition(Point p) {
		this.playShapeset.moveToPosition(this.selectedIndex, p);
	}
	
	public void moveAndStore(Point p) {
		Polygon finalPoly = this.playShapeset.moveToPosition(this.selectedIndex, p);
		this.updateMemo(finalPoly, this.selectedIndex);
	}
}
