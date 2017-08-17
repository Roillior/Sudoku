/**
 * This class represent the main method of the program 
 * 
 * 
 * @author Lior Maimon  
 * mmn13 , Question 2
 */
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BoardTest {
	//the main method
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){}
		
		JFrame frame = new JFrame("Sudoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,500);
		frame.add(new BoardPanel());
		frame.setVisible(true);
	}
}