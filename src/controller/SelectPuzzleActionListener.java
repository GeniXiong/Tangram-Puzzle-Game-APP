package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Board;
import views.PanelLayout;

public class SelectPuzzleActionListener implements ActionListener{
	private Board board;
	private String shapesetName;
	private String puzzleName;
	private PanelLayout layout;
	
	
	public SelectPuzzleActionListener(PanelLayout layout, Board board, String shapesetName, String puzzleName) {
		this.board = board;
		this.layout = layout;
		this.shapesetName = shapesetName;
		this.puzzleName = puzzleName;
	}
	
	/**
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 *
	 * Selection event occurred; if activate is true, then selection starting, otherwise selection ending
	 * 
	 * ActionEvent which element from the model was selected
	 * activate whether selecting or un-selecting
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.board = new Board(this.shapesetName, this.puzzleName);
		this.layout.puzzle.initialize(this.board);
//		this.layout.puzzle.add(new DrawShapeset(this.board));
		this.layout.jumpTo("puzzle");
	}
}
