/**
 * This class represent the the Panel 
 * extends JPanel
 * 
 * @author Lior Maimon 
 * mmn13 , Question 2
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.ArrayList;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BoardPanel extends JPanel{
	//instance variables
	Matrix matrix;
	Cell cube;
	Cell[][] cellMatrix;
	JPanel buttonsJPanel;
	JPanel boardJPanel;
	JButton setButton;
	JButton clearButton;
	
	/**
	 * construct a new BoardPanel
	 * 
	 */
	public BoardPanel(){
		setLayout(new BorderLayout());
		
		buttonsJPanel = new JPanel();
		boardJPanel = new JPanel(new GridLayout(9,9));
		add(boardJPanel , BorderLayout.CENTER);
		
		
		matrix = new Matrix();
		cellMatrix = new Cell[9][9];
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				cube = new Cell(i , j , matrix);
				cellMatrix[i][j] = cube;
				boardJPanel.add(cube.getCell());
			}
		}
		
		setButton = new JButton("Set");
		clearButton = new JButton("Clear");
		
	    setButton.addActionListener(new ButtonListener());
	    clearButton.addActionListener(new ButtonListener());
		
		buttonsJPanel.add(setButton);
		buttonsJPanel.add(clearButton);
		add(buttonsJPanel , BorderLayout.SOUTH);
		
	}
	/**
	 * method paintComponent
	 * @param g - represent a graphic component
	 */
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	/**
	  * This is a inner class that represent as a button listener 
	  * implements ActionListener
	 */
	private class ButtonListener implements ActionListener{
		//instance variables
		boolean check = true;
		int flag;
		@Override
		public void actionPerformed(ActionEvent event){
			Object src = event.getSource();
			
			if(src == setButton){ //if click on set button
				flag = 0;
				for(int i = 0; i < 9; i++){ //check all the input of all the cells that have input(if didnt press enter have to check)
					for(int j = 0; j < 9; j++){
						try{
							if(!(cellMatrix[i][j].getCell().getText().equals(""))){
								check = cellMatrix[i][j].checkInput(cellMatrix[i][j].getCell().getText());
								if(check == false){
									flag = 1;
								}
							}
						}
						catch(NullPointerException e){}		
					}
				}
				if(flag == 0){//no error occur set the board
					for(int i = 0; i < 9; i++){
						for(int j = 0; j < 9; j++){
							try{
								if(!(cellMatrix[i][j].getCell().getText().equals(""))){
									cellMatrix[i][j].setCellEdit(false); //cell cannot be edit anymore
									cellMatrix[i][j].getCell().setForeground(Color.BLUE);
								}
							}
							catch(NullPointerException e){}
						}
					}
					setButton.setEnabled(false); //set can occur once a game
				}
				else JOptionPane.showMessageDialog(null, "Please fix the Input and try again");
			}
			
			else if(src == clearButton){ //if click clear button
				for(int i = 0; i < 9; i++){
					for(int j = 0; j < 9; j++){
						cellMatrix[i][j].getCell().setText("");
						matrix.setMatrix(i, j, 0);
						cellMatrix[i][j].setCellEdit(true); //cell will be able to edit 
						cellMatrix[i][j].getCell().setForeground(Color.BLACK);	
					}
				}
				setButton.setEnabled(true);
			}
		}
	}
}
