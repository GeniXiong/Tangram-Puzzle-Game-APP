package views;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.SwingConstants;

import controller.SelectShapeSetActionListener;
import models.Board;
import utils.FilePath;

import javax.swing.JLabel;

public class ShapeSetPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8729069533243581919L;
	
	private PanelLayout layout;
	private Board board;

	/**
	 * Create the application.
	 */
	public ShapeSetPanel(PanelLayout layout, Board board) {
		this.layout = layout;
		this.board = board;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Puzzel Game");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(400, 50, 200, 30);
		add(lblNewLabel);

		initialize();
	}
	
	/**
	 * Initialize the contents of the panel.
	 */
	private void initialize() {
		setSelection("traditional", 40);
		setSelection("nontraditional", 520);
	}

	private void setSelection(String shapeName, int x) {
		JButton btnTraditional = new JButton(shapeName);

		btnTraditional.setIcon(new ImageIcon(FilePath.getShapesetIconPath(shapeName)));
		btnTraditional.setBounds(x, 100, 440, 440);
		add(btnTraditional);
		btnTraditional.addActionListener(new SelectShapeSetActionListener(this.layout, shapeName));
	}

}
