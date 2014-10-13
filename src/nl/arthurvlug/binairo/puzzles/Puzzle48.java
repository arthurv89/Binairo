package nl.arthurvlug.binairo.puzzles;
import nl.arthurvlug.binairo.board.Board;
import nl.arthurvlug.binairo.board.Field;


public class Puzzle48 extends AbstractPuzzle {
	public Puzzle48() {
		super(createBoard());
	}

	private static Board createBoard() {
		Board board = new Board();
		
		board.set(0, 7, Field.ONE);
		
		board.set(1, 5, Field.ONE);
		board.set(1, 10, Field.ZERO);
		board.set(1, 11, Field.ZERO);
		
		board.set(2, 5, Field.ONE);
		board.set(2, 7, Field.ZERO);
		board.set(2, 11, Field.ZERO);
		
		board.set(3, 0, Field.ZERO);
		board.set(3, 3, Field.ONE);
		board.set(3, 9, Field.ZERO);
		
		board.set(5, 7, Field.ONE);
		
		board.set(6, 10, Field.ZERO);
		board.set(6, 11, Field.ZERO);
		
		board.set(7, 3, Field.ZERO);
		board.set(7, 7, Field.ZERO);
		
		board.set(8, 6, Field.ZERO);
		board.set(8, 7, Field.ZERO);
		board.set(8, 10, Field.ZERO);
		board.set(8, 11, Field.ONE);
		
		board.set(9, 3, Field.ZERO);
		board.set(9, 5, Field.ZERO);
		board.set(9, 11, Field.ONE);

		board.set(10, 2, Field.ZERO);
		board.set(10, 3, Field.ZERO);
		board.set(10, 6, Field.ONE);
		board.set(10, 8, Field.ONE);
		board.set(10, 10, Field.ZERO);

		board.set(11, 6, Field.ONE);
		board.set(11, 9, Field.ZERO);
		
		return board;
	}

	@Override
	public int getSize() {
		return 12;
	}

}
