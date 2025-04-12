// Wilson Chen (wilson.chen@quinnipiac.edu)
// CSC111_02_25SP 
// Project 2: Maze Read and Navigation

public class MazeSolver {
	// creates a new maze object
	private Maze maze;
	
	// constructor that passes a maze object to solve it
	public MazeSolver(Maze maze) {
		this.maze = maze;
	}
	
	// recursive method that uses row and column parameters
	public boolean solveMaze(int row, int column) {
		boolean solved = false;
		
		// the cell is available, then we can try the cell and print the current location
		if (maze.validCell(row, column)) {
			maze.tryCell(row, column);
			maze.printCurrentLocation(row, column);
			
			// if the maze is solved, set solved to true
			if (maze.solvedMaze(row, column)) {
				solved = true;
			} else {
				// else try the other directional methods by calling them recursively
				if(!solved) {
					// north
					solved = solveMaze(row - 1, column);
				}
				
				if(!solved) {
					// west
					solved = solveMaze(row, column - 1);
				}
				if(!solved) {
					// south
					solved = solveMaze(row + 1, column);
				}
				if(!solved) {
					// east
					solved = solveMaze(row, column + 1);
				}
			}
			
			// if the maze is solved, set the tried cells to the path cells
			if(solved) {
				maze.markPath(row, column);
			}
			
		}
		
		return solved;
	}
}