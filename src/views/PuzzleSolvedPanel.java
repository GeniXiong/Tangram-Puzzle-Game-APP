package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.Board;

public class PuzzleSolvedPanel extends JPanel{

	private PanelLayout layout;
	private Board board;


	/**
	 * Create the application.
	 */
	public PuzzleSolvedPanel(PanelLayout layout, Board board) {
		this.layout = layout;
		this.board = board;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(null);
		setLabel(this.board.shapesetName, this.board.puzzleName);
		setBackButton(this.board.shapesetName);
	}
	
	public void reset(String shapesetName, String puzzleName) {
		this.removeAll();
		setLayout(null);
		setLabel(shapesetName, puzzleName);
		setBackButton(shapesetName);
	}
	
	private void setLabel(String shapesetName, String puzzleName) {
		JLabel lblNewLabel = new JLabel("You've solved! " + shapesetName + "puzzle: " + puzzleName);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(0, 50, 1000, 30);
		add(lblNewLabel);
	}
	
	private void setBackButton(String shapesetName) {
		JButton button_1 = new JButton("Back to Shapeset");
		button_1.setBounds(400, 400, 200, 100);
		add(button_1);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				layout.jumpTo("home");
			}
		});
		
		JButton button_2 = new JButton("Back to Puzzles");
		button_2.setBounds(400, 550, 200, 100);
		add(button_2);
		button_2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				layout.puzzles.resetPanel(shapesetName);
				layout.jumpTo("puzzles");
			}
		});
	}

}
