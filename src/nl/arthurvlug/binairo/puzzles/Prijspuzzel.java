package nl.arthurvlug.binairo.puzzles;
import nl.arthurvlug.binairo.board.Board;
import nl.arthurvlug.binairo.board.Field;


public class Prijspuzzel extends AbstractPuzzle {
	public Prijspuzzel() {
		super(createBoard());
	}
	
	private static Board createBoard() {
		Board board = new Board();
		board.set(0, 0, Field.ONE);
		board.set(3, 0, Field.ONE);
		board.set(5, 0, Field.ONE);
		board.set(7, 0, Field.ZERO);
		
		board.set(3, 1, Field.ZERO);
	
		board.set(2, 2, Field.ONE);
		board.set(6, 2, Field.ONE);
		board.set(8, 2, Field.ONE);
		board.set(10, 2, Field.ZERO);
		board.set(11, 2, Field.ZERO);
	
		board.set(4, 3, Field.ONE);
	
		board.set(2, 4, Field.ONE);
		board.set(6, 4, Field.ONE);
		board.set(7, 4, Field.ONE);
		board.set(10, 4, Field.ZERO);
		board.set(11, 4, Field.ZERO);
	
		board.set(4, 5, Field.ONE);
	
		board.set(2, 6, Field.ONE);
		board.set(4, 6, Field.ONE);
		board.set(7, 6, Field.ONE);
		board.set(8, 6, Field.ONE);
	
		board.set(0, 7, Field.ZERO);
		board.set(5, 7, Field.ONE);
		board.set(10, 7, Field.ZERO);
	
		board.set(2, 8, Field.ZERO);
		board.set(3, 8, Field.ZERO);
		board.set(7, 8, Field.ONE);
		board.set(11, 8, Field.ONE);
		
		board.set(5, 9, Field.ONE);
		board.set(8, 9, Field.ZERO);
		board.set(10, 9, Field.ONE);
		board.set(11, 9, Field.ONE);
	
		board.set(2, 10, Field.ONE);
		board.set(5, 10, Field.ONE);
		board.set(6, 10, Field.ONE);
		board.set(10, 10, Field.ONE);
		
		board.set(1, 11, Field.ZERO);
		board.set(4, 11, Field.ZERO);
		board.set(7, 11, Field.ONE);
		board.set(11, 11, Field.ONE);
		
		return board;
	}

	public int getSize() {
		return 12;
	}
}
