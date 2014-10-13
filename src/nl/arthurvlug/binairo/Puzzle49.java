package nl.arthurvlug.binairo;

import nl.arthurvlug.binairo.board.Board;
import nl.arthurvlug.binairo.board.Field;
import nl.arthurvlug.binairo.puzzles.AbstractPuzzle;

public class Puzzle49 extends AbstractPuzzle {
	public Puzzle49() {
		super(createBoard());
	}

	private static Board createBoard() {
		Board board = new Board();
		board.set(0, 4, Field.ZERO);
		
		board.set(1, 11, Field.ZERO);

		board.set(2, 4, Field.ZERO);
		board.set(2, 7, Field.ONE);
		board.set(2, 9, Field.ZERO);
		board.set(2, 10, Field.ZERO);
		
		board.set(3, 9, Field.ZERO);

		board.set(4, 5, Field.ONE);
		board.set(4, 8, Field.ONE);
		board.set(4, 11, Field.ONE);

		board.set(5, 2, Field.ONE);
		board.set(5, 4, Field.ONE);
		board.set(5, 8, Field.ONE);

		board.set(6, 0, Field.ONE);
		board.set(6, 11, Field.ZERO);

		board.set(7, 6, Field.ONE);
		board.set(7, 10, Field.ONE);

		board.set(8, 5, Field.ZERO);
		board.set(8, 11, Field.ZERO);

		board.set(9, 6, Field.ONE);
		board.set(9, 7, Field.ONE);
		board.set(9, 11, Field.ZERO);

		board.set(10, 1, Field.ONE);
		board.set(10, 3, Field.ZERO);
		board.set(10, 8, Field.ZERO);

		board.set(11, 4, Field.ONE);
		board.set(11, 5, Field.ONE);
		board.set(11, 7, Field.ONE);
		
		return board;
	}

	@Override
	public int getSize() {
		return 12;
	}

}
