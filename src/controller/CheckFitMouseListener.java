package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import models.Board;
import views.PanelLayout;

public class CheckFitMouseListener implements MouseListener{
	private PanelLayout layout;
	private Board board;
	private boolean fit;
	private Point origin;
	
	public CheckFitMouseListener(PanelLayout layout, Board b) {
		this.board = b;
		this.layout = layout;
		this.fit = false;
		this.origin = null;
	}
	
	public void mouseClicked(MouseEvent e) {  
		int index = this.board.clickFromPoint(e.getPoint());
		if (index >= 0) {
			this.origin = e.getPoint();
		}
    }  
    public void mouseEntered(MouseEvent e) {  
    }  
    public void mouseExited(MouseEvent e) {  
    }  
    public void mousePressed(MouseEvent e) {  
    	int index = this.board.clickFromPoint(e.getPoint());
		if (index >= 0) {
			this.origin = e.getPoint();
		}
    }  
    public void mouseReleased(MouseEvent e) {
//    	this.layout.puzzle.repaint();
//    	Point delta = new Point(e.getX() - this.origin.x, e.getY() - this.origin.y);
//    	this.board.moveAndStore(delta);
//    	this.fit = this.board.checkFit();
//    	if (fit) {
//    		this.layout.jumpTo("solved");
//    	}
    }  

}
