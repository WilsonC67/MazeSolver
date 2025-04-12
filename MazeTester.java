// Wilson Chen (wilson.chen@quinnipiac.edu)
// CSC111_02_25SP 
// Project 2: Maze Read and Navigation

public class MazeTester {

	public static void main(String[] args) {

		// creates a new maze 
		Maze maze = new Maze("maze.txt");
		
		// prints a new maze
		maze.printMaze();
		
		// creates a new solver with the new maze
		MazeSolver solver = new MazeSolver(maze);
		
		// if the solver successfully solves the maze, then say so. otherwise, print otherwise
		if (solver.solveMaze(5, 12)) {
			System.out.println("The maze was traversed successfully.");
		} else {
			System.out.println("There was no possible path.");
		}
		
		// prints the traversed maze
		maze.printMaze();
		
	}

}
