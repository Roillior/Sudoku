/**
 * This class represent a cell in a sudoku board
 * 
 * 
 * @author Lior Maimon
 * mmn13 , Question 2
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Cell {
	//instance variables
	private int xPos; 
	private int yPos;
	private JTextField cube;
	private Handler handler;
	private Matrix matrix;
	
	/**
	 * construct a new cell
	 * @param x - represent the x position of the cell in the board
	 * @param y - represent the y position of the cell in the board
	 * @param mat - represent the matrix of the current board
	 */
	public Cell(int x, int y , Matrix mat){
		cube = new JTextField(1);
		this.xPos = x;
		this.yPos = y;
		this.matrix = mat;
		handler = new Handler();
		cube.addActionListener(handler);
		//color the blocks
		if(x == 0 || x == 1 || x == 2 || x == 6 || x == 7 || x == 8){
			if(y == 0 || y == 1 || y == 2 || y == 6 || y == 7 || y == 8 ){
				cube.setBackground(Color.lightGray);
			}
		}
		else if(y == 3 || y == 4 || y ==5){
			cube.setBackground(Color.lightGray);
		}
	}
	/**
	 * Return a pointer to the cell
	 * @return a pointer to this cell
	 */
	public JTextField getCell(){
		return this.cube;
	}
	/**
	 * set the editable of the cell
	 * @param val - true for editable false for not editable
	 */
	public void setCellEdit(boolean val){
		this.cube.setEditable(val);
	}
	/**
	 * Return a x position of the cell
	 * @return integer represent the x position
	 */
	public int getXPos(){
		return this.xPos;
	}
	/**
	 * Return a y position of the cell
	 * @return integer represent the y position
	 */
	public int getYPos(){
		return this.yPos;
	}
	/**
	 * method that check the input in the cell
	 * @param event - an object represent the value that entered to the cell
	 * @return boolean value - true for correct Input false otherwise
	 */
	public boolean checkInput(Object event){
		int num;
		try{
			num = Integer.parseInt((String)event);
		}
		catch(NumberFormatException e){
			return false;
		}
		if(!(String.valueOf(num).matches("[1-9]"))){ //check input is number between 1 - 9
			return false;
		}
		matrix.setMatrix(this.xPos, this.yPos, 0); //set our position to 0 for the check
		if(!(matrix.checkLine(this.xPos, num))){
			return false;
		}
		if(!(matrix.checkColumn(this.yPos, num))){
			return false;
		}
		if(!(matrix.checkBlock(this.xPos, this.yPos, num))){
			return false;
		}
		matrix.setMatrix(xPos, yPos, num);//update the matrix in the new value
		return true;
	}
	//private method implements ActionListener - used to obtain enter in a cell from the user
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == cube){
				if(!(checkInput(e.getActionCommand()))){
					cube.setText("");
					JOptionPane.showMessageDialog(null, "Input is not correct, Try again");
					matrix.setMatrix(xPos, yPos, 0);
				}
				else matrix.setMatrix(xPos, yPos, Integer.parseInt((String)e.getActionCommand()));
			}
		}
	}
}
