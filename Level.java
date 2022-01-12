import java.util.*;


public class Level {	
	private Board board;
	private int numofRows;
	private int numofCols;
	private int numofMoves;
	Vehicle vehicles[];
	Space winSpace;
	Vehicle[][] grid; 
	ArrayList<Vehicle> boardVehicles = new ArrayList<Vehicle>(6);
	
	//TODO fill out this class with a Level constructor
	//all the other methods necessary and any other instance variables needed
	public Level(int numofRows, int numofCols) {
		this.numofRows = numofRows;
		this.numofCols = numofCols;
		grid = new Vehicle[numofRows][numofCols]; 
	}
	
	/**
	 * @return the number of columns on the board
	 */
	public int getCols() {
		//TODO: have this return the number of columns in the level
		return numofCols; 
	}
	
	public int getRows() {
		return numofRows; 
	}
	
	public Space getGoalSpace() {
		return winSpace;
	}
	
	public int getNumMoves() {
		return numofMoves;
	}
	
	public void incrementingMoves() {
		numofMoves++;
	}
	
	public Vehicle getVehicle(Space space) {
		return grid[space.getRow()][space.getCol()];
	}
	
	
	public void settingupLevel(int maxRows, int maxCols) {
		board = new Board(maxRows, maxCols);
		numofMoves = 0;
		Space s;
		Vehicle at;
		
		board.addVehicle(VehicleType.MYCAR, 2, 0, 2, false);
		board.addVehicle(VehicleType.TRUCK, 0, 2, 3, true);
		board.addVehicle(VehicleType.AUTO, 4, 4, 2, false);
		board.addVehicle(VehicleType.AUTO, 3, 3, 2, true);
		board.addVehicle(VehicleType.AUTO, 0, 4, 2, true);
		board.addVehicle(VehicleType.AUTO, 0, 5, 2, true);
		
		
		at = new Vehicle(VehicleType.MYCAR, 2, 0, 2, false);
		boardVehicles.add(at);
		at = new Vehicle(VehicleType.TRUCK, 0, 2, 3, true);
		boardVehicles.add(at);
		at = new Vehicle(VehicleType.AUTO, 4, 4, 2, false);
		boardVehicles.add(at);
		at = new Vehicle(VehicleType.AUTO, 3, 3, 2, true);
		boardVehicles.add(at);
		at = new Vehicle(VehicleType.AUTO, 0, 4, 2, true);
		boardVehicles.add(at);
		at = new Vehicle(VehicleType.AUTO, 0, 5, 2, true);
		boardVehicles.add(at);
		
		//vehicles[0] = board.getVehicle(new Space (2,0));
		//vehicles[1] = board.getVehicle(new Space (0,2));
		//vehicles[2] = board.getVehicle(new Space (4,4));
		//vehicles[3] = board.getVehicle(new Space (3,3));
		//vehicles[4] = board.getVehicle(new Space (0,4));
		//vehicles[5] = board.getVehicle(new Space (0,5)); 	
		
		for (int i = 0; i < getRows(); i++) {
			for (int k = 0; k < getCols(); k++) {
				s = new Space(i, k);
				if (board.isVehicleOnSpace(s) == true && board.getVehicle(s).getVehicleType() == VehicleType.MYCAR) {
					at = board.getVehicle(s);
					if (at.getisVertical()) {
						winSpace = new Space(getRows() - 1, at.getStart().getCol());
					}
					else {
						winSpace = new Space(at.getStart().getRow(), getCols() - 1);
					}
					break;
				}
			}
		}
	}
	
	public boolean moveNumSpace(Space space, int numSpaces) {
		if (board.moveNumSpaces(space, numSpaces) == true) {
			incrementingMoves();
		}
		else {
			System.out.println("sorry but that vehicle can't be moved to where you want, please try again");
		}
		return false; 
		
		
	}
	
	public boolean passLevel() {
		Space s; 
		Vehicle at;
		
		for (int i = 0; i < getRows(); i++) {
			for (int k = 0; k < getCols(); k++) {
				s = new Space(i, k);
				if (board.isVehicleOnSpace(s) && board.getVehicle(s).getVehicleType() == VehicleType.MYCAR) {
					for (int j = 0; j < getRows() * getCols(); j++) {
						at = board.getVehicle(s);
						s = at.spacesOccupied()[at.getLength() - 1];
						if (s == winSpace) {
							return true; 
						}
					}
				}
			}
		}
		return false; 
	}
	
	//Methods already defined for you
	/**
	 * generates the string representation of the level, including the row and column headers to make it look like
	 * a table
	 * 
	 * @return the string representation
	 */
	public String toString() {
		String result = generateColHeader(getCols());
		result+=addRowHeader(board.toString());
		return result;
	}
	
	/**
	 * This method will add the row information
	 * needed to the board and is used by the toString method
	 * 
	 * @param origBoard the original board without the header information
	 * @return the board with the header information
	 */
	private String addRowHeader(String origBoard) {
		String result = "";
		String[] elems = origBoard.split("\n");
		for(int i = 0; i < elems.length; i++) {
			result += (char)('A' + i) + "|" + elems[i] + "\n"; 
		}
		return result;
	}
	
	/**
	 * This one is responsible for making the row of column numbers at the top and is used by the toString method
	 * 
	 * @param cols the number of columns in the board
	 * @return if the # of columns is five then it would return "12345\n-----\n"
	 */
	private String generateColHeader(int cols) {
		String result = "  ";
		for(int i = 1; i <= cols; i++) {
			result+=i;
		}
		result+="\n  ";
		for(int i = 0; i < cols; i++) {
			result+="-";
		}
		result+="\n";
		return result;
	}

	public ArrayList<Vehicle> getVehiclesOnLevel() {
		return boardVehicles;
	}

	
}
