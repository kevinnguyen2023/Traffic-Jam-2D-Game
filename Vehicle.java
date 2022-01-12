public class Vehicle {
	// TODO You'll need to fill in this entire file
	// Variables
	private int length; 
	private boolean isVertical; 
	private VehicleType type; 
	private Space start; 

	
	
	//Constructor
	public Vehicle(VehicleType vehicleType, int row, int col, int length, boolean isVert) {
		// TODO change this implementation so that you return the vehicles
		// actual type, which should be stored in a variable
		// . Right now it only returns the type mycar
		setType(vehicleType);  //Updating all private values of a vehicle
		start = new Space(row, col);
		//setRow(row);
		//setCol(col);
		setLength(length);
		setisVertical(isVert);
	}
	/**
	 * @return the type associated with this particular vehicle
	 */
	// Get Functions
	public VehicleType getVehicleType() {
		return type; 
	}
	public int getRow() {
		return start.getRow();
	}
	public int getCol() {
		return start.getCol();
	}
	public int getLength() {
		return length; 
	}
	public Space getStart() {
		return start; 
	}
	public boolean getisVertical () {
		return isVertical;
	}
	
	// Set Functions
	public void setType(VehicleType type) {
		this.type = type; 
	}
	public void setRow(int row) {
		start.setRow(row); 
	}
	public void setCol(int col) {
		start.setCol(col); 
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setisVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}

	/**
	 * Provides an array of Spaces that indicate where a particular Vehicle
	 * would be located, based on its current starting space
	 * 
	 * @return the array of Spaces occupied by that particular Vehicles
	 */
	
	public Space[] spacesOccupied() {
		// TODO change this implementation so that you return the correct spaces
		
		Space[] spacesOccupied;
		spacesOccupied = new Space[getLength()];
		
		if (isVertical == true) {		
			for (int i = 0; i < getLength(); i++) {  // If it's vertical, the row length is affected
				spacesOccupied[i] = new Space(getRow() + i, getCol()); 
			}
		}
		else {   // if vehicle is horizontal
			for (int i = 0; i < getLength(); i++) { // the column length will be affected
				spacesOccupied[i] = new Space(getRow(), getCol() + i);
			}
		}
		return spacesOccupied;
		
	}

	
	public void moveVehicle (int numberofSpaces) {
		//if (numberofSpaces >= 0) {		// If move is in positive direction
			if (isVertical == true) {	// Row will change if vertical
				setRow(getRow() + numberofSpaces);
			}
			else {		// if horizontal, column will change
				setCol(getCol() + numberofSpaces); // 
			}
			
		//}
		/*else {		// If move is in negative direction
			if (isVertical == true) { // The row will change if vertical
				setRow(getRow() - numberofSpaces); 
			}
			else {	// column will change if horizontal
				setCol(getCol() - numberofSpaces); 
			}
		}*/
	}
	public Space ifMoveVehicle (int numSpaces)  {
		
		//Vehicle ifMovingSpaces = new Vehicle(getVehicleType(), getRow(), getCol(), getLength(), getisVertical());
		//ifMovingSpaces.moveVehicle(numSpaces);
		//return ifMovingSpaces.spacesOccupied()[0];
		
		Space startNew = new Space(start.getRow(), start.getCol()); 
		
		if (getisVertical() == true) {
			startNew.setRow(start.getRow() + numSpaces);
		}
		
		else {
			startNew.setCol(start.getCol() + numSpaces);
		}
		return startNew; 
		
	}
	
	public void move(int spaces) {
        /*if (spaces < 0) {
        	for (int i = 0; i < Math.abs(spaces); i++) {
        		spacesOccupied()[i] = ifMoveVehicle(spaces + 1);
        		
        }
        if (spaces > 0) {
        	for (int i = 0; i < Math.abs(spaces); i++) {
        		spacesOccupied()[i] = ifMoveVehicle(i + 1); 
        	}
        }
       }
 */
		if (isVertical == true) {	// Row will change if vertical
			setRow(getRow() + spaces);
		}
		else {		// if horizontal, column will change
			setCol(getCol() + spaces); // 
		}
    }
	
	public static void printSpaces (Space[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("r" + arr[i].getRow() + "c" + arr[i].getCol() + "; ");
		}
		System.out.println();
	}
	
	/**
	 * Calculates an array of the spaces that would be travelled if a vehicle
	 * were to move numSpaces
	 * 
	 * @param numSpaces
	 *            The number of spaces to move (can be negative or positive)
	 * @return The array of Spaces that would need to be checked for Vehicles
	 */
	
	
	public Space[] spacesOccupiedOnTrail(int numSpaces) {
		// TODO change this implementation so that you return the correct space
		//int spacesMoved = numSpaces;
		Space[] spacesOccupiedOnTrail = new Space[Math.abs(numSpaces)];
		for(int i = 0; i < Math.abs(numSpaces); i++) {
			if(numSpaces < 0) {
				//spacesMoved = spacesMoved + i;
				//if (isVertical == true) {
					spacesOccupiedOnTrail[i] = ifMoveVehicle(numSpaces + i); //new Space(getRow() + spacesMoved, getCol());
				//}
				//if (isVertical == false) {
					//spacesOccupiedOnTrail[i] = new Space(getRow(), getCol() + spacesMoved);
				//}
				} 
			if (numSpaces > 0) {
				//numSpaces = numSpaces - i;
				//if (isVertical == true) {
					//new Space(getRow() + spacesMoved, getCol());
					spacesOccupiedOnTrail[i] = ifMoveVehicle(i + length); 
				//}
				//if (isVertical == false) {
					//spacesOccupiedOnTrail[i] = new Space(getRow(), getCol() + spacesMoved);
				//}
			}
		}
		return spacesOccupiedOnTrail; 

	}
	
	// Testing the main function
	public static void main(String[] args) {
		
		Vehicle someTruck = new Vehicle(VehicleType.TRUCK, 1, 1, 3, true); 
		Vehicle someAuto = new Vehicle(VehicleType.AUTO, 2, 2, 2, false);
		System.out.println("This next test is for spacesOccupied: ");
		System.out.println("vertical truck at r1c1 should give you r1c1; r2c1; r3c1 as the spaces occupied:does it?");
		printSpaces(someTruck.spacesOccupied());
		System.out.println("horizontal auto at r2c2 should give you r2c2; r2c3 as the spaces occupied:does it?");
		printSpaces(someAuto.spacesOccupied());
		System.out.println("if we were to move horizontal auto -2 it should give you at least r2c0; r2c1; it may also add r2c2; r2c3 to its answer:does it?"); 
		printSpaces(someAuto.spacesOccupiedOnTrail(-2));
			
		
	}

}
