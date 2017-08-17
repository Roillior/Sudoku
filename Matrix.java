/**
 * This class represent an int Matrix 
 * 
 * 
 * @author Lior Maimon 
 * mmn13 , Question 2
 */
public class Matrix {
	//instance variables
	private int[][] matrix ;
	/**
	 * construct a new Matrix in size 9x9 that contain in all cells '0'
	 *
	 */
	public Matrix(){
		matrix = new int[9][9];
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				matrix[i][j] = 0;
			}
		}
	}
	/**
	 * Return a pointer to the matrix
	 * @return a pointer to the matrix
	 */
	public int[][] getMatrix(){ 
		return this.matrix;
	}
	/**
	 * set a value in the matrix (we use pointer so not must use this but that more organize way)
	 * @param x - represent the line in the matrix
	 * @param y - represent the column in the matrix
	 * @param value - the value we want to input
	 */
	public void setMatrix(int x, int y, int value){
		matrix[x][y] = value;
	}
	/**
	 * method that check that the line rule in the sudoku
	 * @param line - represent the line we want to check
	 * @param value - represent the value we want to check
	 * @return boolean value - true if the check is good, false otherwise
	 */
	public boolean checkLine(int line, int value){
		for(int i = 0; i < matrix.length; i++){
			if(matrix[line][i] == value){
				return false;
			}
		}
		return true;	
	}
	/**
	 * method that check that the column rule in the sudoku
	 * @param column - represent the column we want to check
	 * @param value - represent the value we want to check
	 * @return boolean value - true if the check is good, false otherwise
	 */
	public boolean checkColumn(int column, int value){
		for(int i = 0; i < matrix.length; i++){
			if(matrix[i][column] == value){
				return false;
			}
		}
		return true;
	}
	/**
	 * method that check the block rule in the sudoku
	 * @param line - represent the line we want to check
	 * @param column - represent the column we want to check
	 * @param value - represent the value we want to check
	 * @return boolean value - true if the check is good, false otherwise
	 */
	public boolean checkBlock(int line, int column, int value){
		for(int i = 0; i < 3; i++){ //run on the block check the numbers
			for(int j = 0; j < 3; j++){
				if(matrix[(line - (line % 3) + i)][column - (column % 3) + j] == value){ //the math calc put us in the upper left corner of the wanted block
					return false;
				}
			}
		}
		return true;
	}
}
