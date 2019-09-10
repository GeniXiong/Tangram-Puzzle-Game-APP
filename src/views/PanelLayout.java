
package views;

import java.awt.*;
import javax.swing.*;

import models.Board;

/**
 * Create the Class Application.
 * The containing Frame for the application. This class is just a
 * GUI shell, and the real logic happens in the Board.
 * @author genixiong
 *
 */
public class PanelLayout extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8039677059382492521L;
	public Board board;
//	panel: a container to contain all the switching panels
	public JPanel pane;
//	other panels to switch
	public ShapeSetPanel home;
	public PuzzleSetPanel puzzles;
	public PuzzleSolvingPanel puzzle;
	public PuzzleSolvedPanel solved;
//	unchangeable height and width
	private int height = 700;
	private int width = 1000;
//	layout of cards/panels to switch
	private CardLayout card;
	
	public PanelLayout(Board board) {
		super ("Puzzle Game");
		
		card = new CardLayout();
		this.board = board;
		this.pane = new JPanel(card);
//		choose between traditional and non-traditional
		this.home = new ShapeSetPanel(this, this.board);
//		display all the puzzles of the shape to choose
		this.puzzles = new PuzzleSetPanel(this, this.board);
//		display a certain puzzle for the player to solve
		this.puzzle = new PuzzleSolvingPanel(this, this.board);
//		display puzzle solved information to the player
		this.solved = new PuzzleSolvedPanel(this, this.board);
		
		pane.add(this.home, "home");
		pane.add(this.puzzles, "puzzles");
		pane.add(this.puzzle, "puzzle");
		pane.add(this.solved, "solved");
		
		this.getContentPane().add(this.pane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(this.width, this.height);
		this.setResizable(false);
	}
	
	public ShapeSetPanel home() {
		return this.home;
	}
	
	public void home(ShapeSetPanel p) {
		this.home = p;
	}
	
	public PuzzleSetPanel puzzles() {
		return this.puzzles;
	}
	
	public void puzzles(PuzzleSetPanel p) {
		this.puzzles = p;
	}

	public PuzzleSolvingPanel puzzle() {
		return this.puzzle;
	}
	
	public void puzzle(PuzzleSolvingPanel p) {
		this.puzzle = p;
	}
	
	public PuzzleSolvedPanel solved() {
		return this.solved;
	}
	
	public void solved(PuzzleSolvedPanel p) {
		this.solved = p;
	}
	
	public JPanel getPanel() {
		return this.pane;
	}
	
	public void jumpTo(String panelName) {
		System.out.println("jumped to " + panelName);
		card.show(pane, panelName);
	}

}
