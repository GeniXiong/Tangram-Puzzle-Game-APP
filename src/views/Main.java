package views;

import javax.swing.JFrame;

import models.Board;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();

		PanelLayout pane = new PanelLayout(board);
		pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pane.setVisible(true);

	}

}
