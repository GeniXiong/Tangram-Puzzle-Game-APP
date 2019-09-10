package views;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.SelectPuzzleActionListener;
import models.Board;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import utils.FilePath;
import utils.NameConfig;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PuzzleSetPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7657661002532247450L;
	public ArrayList<String> puzzles;
	public PanelLayout layout;
	private Board board;

	public PuzzleSetPanel (PanelLayout layout, Board board) {
		this.layout = layout;
		this.puzzles = new ArrayList<String>();
		setLayout(null);
			
		System.out.println("initalized!");

	}

	/**
	 * Initialize the contents of the puzzles frame.
	 */
	public void resetPanel(String shapesetName) {
//		System.out.println(shapesetName + " has received!");
		this.removeAll();
		this.setLabel(shapesetName);
		this.puzzles = NameConfig.getInstance().getPuzzles(shapesetName);
		this.setButtons(shapesetName);
		this.setBackButton();
		
	}
	
	private void setLabel(String shapesetName) {
		JLabel lblNewLabel = new JLabel("choose a " + shapesetName + " puzzle");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 50, 1000, 30);
		add(lblNewLabel);
	}
	
	public void setButtons(String shapesetName) {
		int x = 120;
		int y = 130;
		int dy = 50;
		for(int i = 0; i < puzzles.size(); i++) {
			
			int row = i / 4;
			int col = i % 4;
			this.setButton(col * (100 + x) + x, row * (100 + dy) + y, shapesetName, puzzles.get(i));
		}
		
		this.setBackButton();
	}
	
	private void setButton(int x, int y, String shapesetName, String puzzleName) {
		JButton puzzle = new JButton(puzzleName);
		puzzle.setIcon(new ImageIcon(FilePath.getPuzzleIconPath(shapesetName, puzzleName)));
		puzzle.setBounds(x, y, 100, 100);
		add(puzzle);
//		puzzle.addActionListener(null);
		puzzle.addActionListener(new SelectPuzzleActionListener(this.layout, this.board, shapesetName, puzzleName));
	}
	
	private void setBackButton() {
		JButton backButton = new JButton("Back to Shapeset");
		backButton.setBounds(560, 430, 320, 100);
		add(backButton);
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				layout.jumpTo("home");
			}
		});
	}

}
