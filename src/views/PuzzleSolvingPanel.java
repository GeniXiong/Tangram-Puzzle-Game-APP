package views;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ShapeMotionListener;
import controller.ShapeMouseMotionListener;
import models.Board;
import models.PlayShape;


public class PuzzleSolvingPanel extends JPanel{
	
	private PanelLayout layout;
	private Board board;
	private String shapesetName;
	private String puzzleName;
	private int height, width, buttonAreaHeight;
	public Point origin;

	/**
	 * Create the application.
	 */
	public PuzzleSolvingPanel(PanelLayout layout, Board board) {
		this.layout = layout;
		this.board = board;
		this.shapesetName = "";
		this.puzzleName = "";
		this.height = 700;
		this.width = 1000;
		this.buttonAreaHeight = 300;
		this.origin = null;
		setLayout(null);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(Board b) {
		this.removeAll();
		this.board = b;
		setLayout(null);
		
//		this.addMouseMotionListener(new ShapeMotionMouseAdapter(this.layout, this.board));
		this.addMouseListener(new ShapeMotionListener(this.layout, this.board));
		this.addMouseMotionListener(new ShapeMouseMotionListener(this.layout, this.board));
		setExitButton();
		setRightRotate();
		setLeftRotate();
		setHorizontalFlip();
		setVerticalFlip();
		setUndo();
		setRedo();
		setReset();
		this.repaint();
	}
	
	public void setExitButton() {
		/** Add button to exit to jump page. */
		JButton buttonExit = new JButton("Exit");
		buttonExit.setBounds(this.width - 100, this.height - 80, 80, 30);
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				layout.jumpTo("puzzles");
			}
		});
		this.add(buttonExit);
	}
	
	public void setLeftRotate() {
		/** Add button for left rotate and call AbstractAction leftRotateControl */
		JButton buttonLeftRotation = new JButton("L Rotate");
		buttonLeftRotation.setBounds(this.width - 200, this.height - 80, 90, 30);
		buttonLeftRotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.leftRotate();
				repaint();
			}
		});
		this.add(buttonLeftRotation);
	}

	public void setRightRotate() {
		/** Add button for right rotate and call AbstractAction rightRotateControl */
		JButton buttonRightRotation = new JButton("R Rotate");
		buttonRightRotation.setBounds(this.width - 300, this.height - 80, 90, 30);
		buttonRightRotation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.rightRotate();
				repaint();
			}
		});
		this.add(buttonRightRotation);
	}
	
	public void setHorizontalFlip() {
		/** Add button for horizontal flip and call AbstractAction hFlipControl */
		JButton buttonHorizonFlipped = new JButton("H Flip");
		buttonHorizonFlipped.setBounds(this.width - 400, this.height - 80, 90, 30);
		buttonHorizonFlipped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.hFlip();
				repaint();
			}
		});
		this.add(buttonHorizonFlipped);
	}
	
	public void setVerticalFlip() {
		/** Add button for vertical flip and call AbstractAction vFlipControl */
		JButton buttonVerticalFlipped = new JButton("V Flip");
		buttonVerticalFlipped.setBounds(this.width - 500, this.height - 80, 90, 30);
		buttonVerticalFlipped.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.vFlip();
				repaint();
			}
		});
		this.add(buttonVerticalFlipped);
	}
	
	public void setUndo() {
		/** Add button for undo and call UndoController undoControl */
		JButton buttonUndo= new JButton("undo");
		buttonUndo.setBounds(this.width - 600, this.height - 80, 90, 30);
		buttonUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.undo();
				repaint();
			}
		});
		this.add(buttonUndo);
	}
	
	public void setRedo() {
		/** Add button for redo and call RedoController redoControl */
		JButton buttonRedo= new JButton("redo");
		buttonRedo.setBounds(this.width - 700, this.height - 80, 90, 30);
		buttonRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.redo();
				repaint();
			}
		});
		this.add(buttonRedo);
	}

	public void setReset() {
		/**
		 * Add button for reset and call SolutionResetController resetControl 
		 * All the shapes that in the solution plate will be return to palette
		 */
		JButton buttonReset= new JButton("reset");
		buttonReset.setBounds(this.width - 800, this.height - 80, 90, 30);
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				board.reset();
				repaint();
			}
		});
		this.add(buttonReset);
	}

	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
//	    System.out.println("start draw!");
	    PlayShape[] playShapes = this.board.playShapeset.shapes();
	    PlayShape[] solutionShapes = this.board.solutionShapeset.shapes();
	    
	    for (PlayShape p: solutionShapes) {
	    	g.setColor(p.color());
	    	g.fillPolygon(p.shape());
	    }
	    int topIndex = this.board.selectedIndex;
	    for(int i = 0; i < playShapes.length; i++) {
	    	if (i != topIndex) {
	    		g.setColor(playShapes[i].color());
		    	g.fillPolygon(playShapes[i].shape());
	    	}
	    	g.setColor(playShapes[topIndex].color());
	    	g.fillPolygon(playShapes[topIndex].shape());
	    }

	  }

}
