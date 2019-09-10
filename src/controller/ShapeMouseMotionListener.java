package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import models.Board;
import views.PanelLayout;

public class ShapeMouseMotionListener implements MouseMotionListener{
	private PanelLayout layout;
	private Board board;
//	private Point origin;
	
	public ShapeMouseMotionListener(PanelLayout layout, Board b) {
		this.layout = layout;
		this.board = b;
//		this.origin = origin;
	}
	public void mouseMoved(MouseEvent e) {
		this.layout.puzzle.repaint();
		int index = this.board.clickFromPoint(e.getPoint());
		if (index >= 0) {
			this.layout.puzzle.origin = e.getPoint();
		}
		this.layout.puzzle.repaint();
	}

    public void mouseDragged(MouseEvent e) {
		if (this.layout.puzzle.origin != null) {
			Point delta = new Point(e.getX() - this.layout.puzzle.origin.x, e.getY() - this.layout.puzzle.origin.y);
			this.board.moveToPosition(delta);
			
		}
		this.layout.puzzle.origin = e.getPoint();
		this.layout.puzzle.repaint();
    }

}
