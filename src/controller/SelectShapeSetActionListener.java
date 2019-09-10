package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import models.Board;
import views.PanelLayout;

public class SelectShapeSetActionListener implements ActionListener{
	public PanelLayout layout;
	public String shapesetName;
	
	public SelectShapeSetActionListener(PanelLayout layout, String shapesetName) {
		this.layout = layout;
		this.shapesetName = shapesetName;
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
		System.out.println(this.shapesetName + " has been clicked!");
		this.layout.puzzles.resetPanel(this.shapesetName);
		this.layout.jumpTo("puzzles");
	}


}
