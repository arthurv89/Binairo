package nl.arthurvlug.binairo.puzzles;

import nl.arthurvlug.binairo.board.Board;

public abstract class AbstractPuzzle {
	private Board board;
	
	public AbstractPuzzle(Board board) {
		this.board = board;
	}

	public abstract int getSize();

	public Board getBoard() {
		return board;
	}
}
