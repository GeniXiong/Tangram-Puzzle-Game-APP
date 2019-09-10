package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import models.Board;
import views.PanelLayout;

public class ShapeMotionListener implements MouseListener{
	private PanelLayout layout;
	private Board board;
	
	public ShapeMotionListener(PanelLayout layout, Board b) {
		this.layout = layout;
		this.board = b;
	}
	
	public void mousePressed(MouseEvent e) {
		this.layout.puzzle.repaint();
    	int index = this.board.clickFromPoint(e.getPoint());
		if (index >= 0) {
			this.layout.puzzle.origin = e.getPoint();
		}
		this.layout.puzzle.repaint();
	    }

    public void mouseReleased(MouseEvent e) {
    	System.out.println("mouse released!");
    	this.layout.puzzle.repaint();
    	Point delta = new Point(e.getX() - this.layout.puzzle.origin.x, e.getY() - this.layout.puzzle.origin.y);
    	this.board.moveAndStore(delta);

    	if (this.board.checkFit()) {
    		System.out.println("success!");
    		this.layout.solved.reset(this.board.shapesetName, this.board.puzzleName);
    		this.layout.jumpTo("solved");
    	}
    	this.layout.puzzle.origin = null;
    	this.layout.puzzle.repaint();
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    	this.layout.puzzle.repaint();
		int index = this.board.clickFromPoint(e.getPoint());
		if (index >= 0) {
			this.layout.puzzle.origin = e.getPoint();
		}
    }

}
