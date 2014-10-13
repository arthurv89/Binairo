package nl.arthurvlug.binairo;
import nl.arthurvlug.binairo.puzzles.AbstractPuzzle;
import nl.arthurvlug.binairo.solver.Solver;


public class Binairo {
	public static void main(String[] args) {
		new Binairo();
	}
	
	public Binairo() {
//		AbstractPuzzle puzzle = new Prijspuzzel();
//		AbstractPuzzle puzzle = new Puzzle48();
		AbstractPuzzle puzzle = new Puzzle49();
		puzzle.getBoard().print();

		Solver solver = new Solver(puzzle);
		solver.solve();
	}
}
