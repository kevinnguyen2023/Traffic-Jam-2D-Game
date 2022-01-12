import acm.program.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.*;

public class GraphicsGame extends GraphicsProgram {
	/**
	 * Here are all of the constants
	 */
	public static final int PROGRAM_WIDTH = 500;
	public static final int PROGRAM_HEIGHT = 500;
	public static final String lABEL_FONT = "Arial-Bold-22";
	public static final String EXIT_SIGN = "EXIT";
	public static final String IMG_FILENAME_PATH = "images/";
	public static final String IMG_EXTENSION = ".png";
	public static final String VERTICAL_IMG_FILENAME = "_vert";
	private static final String LABEL_FONT = null;

	// TODO declare your instance variables here
	public double lastX;
	public double lastY;
	public double firstX;
	public double firstY; 
	
	private Level level;
	private GImage vehicleImage; 
	private GObject vehicleOnObjects;
	GLabel MovesPerformed = new GLabel("0", 10, 20);
	private ArrayList<Vehicle> boardVehicles;
	Space start;
	Space lastSpace; 
	int movesP = 0;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	public void run() {
		// TODO write this part, which is like your main function
		//String s = null;
		//s.charAt(0);
		level = new Level(6, 6);
		level.settingupLevel(6, 6);
		
		drawLevel();
		addMouseListeners();
	}
	

	private void drawLevel() {
		// TODO write the code to draw the entire level, which should
		// mostly be calls to some of your helper functions.
		drawGridLines();
		drawWinningTile();
		drawCars(boardVehicles); 
		drawMovesPerformed();
	}
	
	private ArrayList<Vehicle> getVehiclesOnBoard() {
		return level.getVehiclesOnLevel();
	}
	
	private void drawMovesPerformed() {
		String moved = Integer.toString(movesP);
		
		MovesPerformed.setLabel(moved);
		MovesPerformed.setFont(LABEL_FONT);
		add(MovesPerformed); 
	}

	/**
	 * This should draw the label EXIT and add it to the space that represents
	 * the winning tile.
	 */
	private void drawWinningTile() {
		GLabel winningTiles = new GLabel(EXIT_SIGN, PROGRAM_WIDTH - (spaceWidth() / 1.3), PROGRAM_HEIGHT - (spaceHeight() * 4.4));
		winningTiles.setFont("Arial-Bold-22");
		winningTiles.setColor(Color.BLUE);
		add(winningTiles);
	}

	/**
	 * draw the lines of the grid. Test this out and make sure you have it
	 * working first. Should draw the number of grids based on the number of
	 * rows and column in Level
	 */
	private void drawGridLines() {
		GLine gridLine; 
		
		for (int i = 1; i < level.getCols(); i++) {
			gridLine = new GLine(spaceWidth() * i, 0, spaceWidth() * i, PROGRAM_WIDTH);
			add(gridLine);
		}
		
		for (int i = 1; i < level.getRows(); i++) {
			gridLine = new GLine(0, spaceHeight() * i, PROGRAM_HEIGHT, spaceHeight() * i);
			add(gridLine);
		}
		
	}

	/**
	 * Maybe given a list of all the cars, you can go through them and call
	 * drawCar on each?
	 * @param boardVehicles 
	 */
	private void drawCars(ArrayList<Vehicle> boardVehicles) {
		boardVehicles = getVehiclesOnBoard();
		
		for (int i = 0; i < boardVehicles.size(); i++) { 
			drawCar(boardVehicles.get(i));
		}
	}


	/**
	 * Given a vehicle object, which we will call v, use the information from
	 * that vehicle to then create a GImage and add it to the screen. Make sure
	 * to use the constants for the image path ("/images"), the extension ".png"
	 * and the additional suffix to the filename if the object is vertical when
	 * creating your GImage. Also make sure to set the images size according to
	 * the size of your spaces
	 * 
	 * @param v
	 *            the Vehicle object to be drawn
	 */
	private void drawCar(Vehicle v) {
		// TODO implement drawCar
		GImage vehiclesimage;
		double startR = v.getStart().getCol() * spaceWidth();
		double startC = v.getStart().getRow() * spaceHeight();
		
		
		// Adding the vehicles to the board
		 if (v.getisVertical()) {		
			vehiclesimage = new GImage(IMG_FILENAME_PATH+v.getVehicleType().toString()+VERTICAL_IMG_FILENAME+IMG_EXTENSION, startR, startC);
			vehiclesimage.setSize(spaceWidth(), spaceHeight() * v.getLength());
		}
		
		else {
			vehiclesimage = new GImage(IMG_FILENAME_PATH+v.getVehicleType().toString()+IMG_EXTENSION, startR, startC);
			vehiclesimage.setSize(spaceWidth() * v.getLength(), spaceHeight());
		}
		add(vehiclesimage);
					
	}

	// TODO implement the mouse listeners here
	@Override
	public void mousePressed(MouseEvent e) {  
		
		lastX = e.getX();
		lastY = e.getY();
		
		firstX = e.getX();
		firstY = e.getY();
		vehicleOnObjects = getElementAt(firstX, firstY);
		start = convertXYToSpace(e.getX(), e.getY()); 
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		Vehicle vehicle = getVehicleFromXY(firstX, firstY);
		
		if (vehicle != null) {
			if (vehicle.getisVertical() == true) {
				vehicleOnObjects.move(0, e.getY() - lastY);
			}
			else {
				vehicleOnObjects.move(e.getX() - lastX, 0);
			}
		
		} 
		else {
			vehicleOnObjects.move(e.getX() - lastX, 0);
		}
		lastX = e.getX();
		lastY = e.getY();
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println(calculateSpacesMoved());
		lastSpace = convertXYToSpace(e.getX(), e.getY()); 
		Vehicle vehicle = getVehicleFromXY(firstX, firstY); 
				
		
		if (vehicle == null) {
			return; 
		}
		if (vehicle.getisVertical() == true) {
			if (level.moveNumSpace(start, (int)((e.getY() - firstY) / spaceHeight()))) {
				vehicleOnObjects.setLocation(vehicle.getStart().getCol() * spaceWidth(), vehicle.getStart().getRow() * spaceHeight());
				movesP += 1; 
				drawMovesPerformed();
				System.out.println(movesP);
			}
		}
		else {
			if (level.moveNumSpace(start, (int)((e.getX() - firstX) / spaceWidth()))) {
				vehicleOnObjects.setLocation(start.getCol() * spaceWidth(), start.getRow() * spaceHeight());
				movesP += 1; 
				drawMovesPerformed();
				System.out.println(movesP);
			}
		}
		
		if (level.passLevel() == true) {
			GLabel congratulate = new GLabel("Congratulations! You win!", PROGRAM_WIDTH / 2, PROGRAM_HEIGHT / 2);
			removeAll();
			congratulate.setFont("Arial-Bold-50");
			add(congratulate); 
		}
		System.out.println(level); 
		
	  }
	

	/**
	 * Given a xy coordinates, return the Vehicle that is currently at those x
	 * and y coordinates, returning null if no Vehicle currently sits at those
	 * coordinates.
	 * 
	 * @param x
	 *            the x coordinate in pixels
	 * @param y
	 *            the y coordinate in pixels
	 * @return the Vehicle object that currently sits at that xy location
	 */
	private Vehicle getVehicleFromXY(double x, double y) {
		// TODO fix this implementation
		return level.getVehicle(convertXYToSpace(x, y));
	}

	/**
	 * This is a useful helper function to help you calculate the number of
	 * spaces that a vehicle moved while dragging so that you can then send that
	 * information over as numSpacesMoved to that particular Vehicle object.
	 * 
	 * @return the number of spaces that were moved
	 */
	private int calculateSpacesMoved() { 
		Space vehicleSpace = start; 
		Vehicle vehicle = getVehicleFromXY(firstX, firstY);
		
		if (vehicle != null) {
			if (vehicle.getisVertical() == true) {
				return Math.abs(lastSpace.getRow() - vehicleSpace.getRow());
			}
			else {
				return Math.abs(lastSpace.getCol() - vehicleSpace.getCol());
			}
		}
		return 0;
	}
	
	private int moveC() {
		int a = 0;
		
		if (start != null && lastSpace != null) {
			if(getVehicleFromXY(start.getCol() * spaceWidth(), start.getRow() * spaceHeight()).getisVertical()) {
				a = lastSpace.getRow() - start.getRow();			
			}
			else {
				a = lastSpace.getCol() - start.getCol();
			}
		}
		return a; 
	}

	/**
	 * Another helper function/method meant to return the space given an x and y
	 * coordinate system. Use this to help you write getVehicleFromXY
	 * 
	 * @param x
	 *            x-coordinate (in pixels)
	 * @param y
	 *            y-coordinate (in pixels)
	 * @return the Space associated with that x and y
	 */
	private Space convertXYToSpace(double x, double y) {
		// TODO write this implementation hint (use helper methods below)
		int col = (int)(x / spaceWidth());
		int row = (int)(y / spaceHeight());
		
		Space s = new Space(row, col);
		return s; 
	}

	/**
	 * 
	 * @return the width (in pixels) of a single space in the grid
	 */
	private double spaceWidth() {
		// TODO fix this method
		return PROGRAM_WIDTH / level.getCols();
	}

	/**
	 * 
	 * @return the height in pixels of a single space in the grid
	 */
	private double spaceHeight() {
		// TODO fix this method
		return PROGRAM_HEIGHT / level.getRows();
	}
	
	public static void main(String[] args) {
		new GraphicsGame().start();
	}
}
