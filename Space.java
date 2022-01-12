/**
 * Simple class that represents a row and a column, with simple getters and setters for both
 * @author Osvaldo
 */

public class Space {
	//TODO Put your instance variables here
	
	private int row, column; 
	
	public Space(int row, int col) {
		this.row = row;  // This constructor sets the row and column info
		this.column = col; 
	}
	
	public int getRow() {
		return row; // gets private int row
	}
	
	public int getCol() {
		return column;  // gets private int column
	}
	
	public void setRow(int Row) {
		this.row = Row;   // Setting the private int row
	}
	
	public void setCol(int Col) {
		this.column = Col; 	// Sets private int column
	}
	


	/**
	 * The constructor that will set up the object to store a row and column
	 * 
	 * @param row
	 * @param col
	 */
	
	/*
	public static void main(String[] args) {
		Space one = new Space(3, 4);
		Space two = new Space(1, 6);
		two.setRow(two.getRow()+1);
		two.setCol(two.getCol() - 1);
		System.out.println("one r: " + one.getRow() + ", c: " + one.getCol());
		System.out.println("two r: " + two.getRow() + ", c: " + two.getCol());
	}
	*/

}
