// Wilson Chen (wilson.chen@quinnipiac.edu)
// CSC111_02_25SP 
// Project 2: Maze Read and Navigation

// to access the file
import java.io.File;

// in case of a FileNotFound exception
import java.io.FileNotFoundException;

// to scan the file
import java.util.Scanner;

public class Maze {
	// to indicate the status of any and all cells
	private static final char OPEN = ' ';
	private static final char TRIED = 'T';
	private static final char PATH = 'P';
	private static final char START = 'S';
	private static final char FINISH = 'F';
	
	// these will be determined later by scanning the file
	private int numberOfRows, numberOfColumns;
	private int startRow, startColumn;
	private int endRow, endColumn;
	
	// declares a 2D array to contain the maze without any dimensions currently
	private char[][] maze;
	
	// creates a maze by using the readMaze method
	public Maze(String filename) {
		readMazeFromFile(filename);
	}
	
	// combines a try-catch block, scanner, and String to read the file
	private void readMazeFromFile(String filename) {
		
		try (Scanner scanner = new Scanner(new File(filename))) {
			// to read the lines
			String readLine;
			
			// to scan the number of rows and columns
			numberOfColumns = scanner.nextInt();
			numberOfRows = scanner.nextInt();
			
			// goes to the next line
			scanner.nextLine();
			
			// scans the end row and end column
			endColumn = scanner.nextInt();
			endRow = scanner.nextInt();
			
			// goes to the next line
			scanner.nextLine();

			// scans the start row and start column
			startColumn = scanner.nextInt();
			startRow = scanner.nextInt();

			// goes to the next line; now time to scan the actual maze
			scanner.nextLine();

			// creates a new char array with scanned rows and columns 
			maze = new char[numberOfRows][numberOfColumns];
					
			// iterates through the components length and sets the value at index i
			// to the charAt of the scanned maze line
			for (int i = 0; i < numberOfRows; i++) {
				readLine = scanner.nextLine();
				for (int j = 0; j < numberOfColumns; j++)
				maze[i][j] = readLine.charAt(j);				
			}
			
			// sets the starting and ending cells to their respective characters
			maze[startRow][startColumn] = START;
			maze[endRow][endColumn] = FINISH;
			
		  // if there is no file found, print the stack trace
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// tries a cell 
	public void tryCell(int row, int column) {
		maze[row][column] = TRIED;
	}
	
	// returns true if the current row and column correspond to the ending cell
	public boolean solvedMaze(int row, int column) {
		return (row == endRow && column == endColumn);
	}
	
	// gets the number of rows
	public int getRows() {
		return maze.length;
	}
	
	// gets the number of columns by accessing the length of the first row
	public int getColumns() {
		return maze[0].length;
	}
	
	// if the maze is solved, it will go back to the previously tried cells
	// and mark them as part of the path
	public void markPath(int row, int column) {
		if (solvedMaze(row, column)) {
			maze[row][column] = FINISH;
		} else {
			maze[row][column] = PATH;	
		}
	}
	
	// checks if the cell is available, by first confirming that it is within bounds and then
	// checking to see if its either an open cell or finish cell
	public boolean validCell(int row, int column) {
		boolean open = false;
		if (row >= 0 && row < maze.length && column >= 0 && column < maze[row].length) {
			if(maze[row][column] == OPEN) {
				open = true;
			} else if (maze[row][column] == FINISH) {
				open = true;
			}
		}
		
		return open;
	}
	
	// prints the current location in rows and columns 
	// and the character stored in the row and column of the array
	public void printCurrentLocation(int rows, int columns) {
		printCellStatus(rows, columns);
		System.out.println(String.format("%d, %d", rows, columns));
	}
	
	// prints the character stored in the row and column of the array
	public void printCellStatus(int rows, int columns) {
		System.out.println(maze[rows][columns]);
	}
	
	// prints the maze using an enhanced for-loop
	public void printMaze() {
		for (char[] rows : maze) {
			System.out.println(rows);
		}
		System.out.println();
	}
	
}
