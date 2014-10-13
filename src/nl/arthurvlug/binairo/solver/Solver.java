package nl.arthurvlug.binairo.solver;
import java.awt.Point;

import nl.arthurvlug.binairo.Utils;
import nl.arthurvlug.binairo.board.Board;
import nl.arthurvlug.binairo.board.Field;
import nl.arthurvlug.binairo.puzzles.AbstractPuzzle;


public class Solver {
	private Pointer pointer;
	private Board board;
	
	public Solver(AbstractPuzzle puzzle) {
		this.board = puzzle.getBoard();
		pointer = new Pointer(puzzle.getSize());
	}
	
	public void solve() {
		for(int i=board.getEmptyFieldCount(); i>0; i--) {
			doStep();
			
			board.print();
			
			Utils.stepSleep();
			
			System.out.println(board.getLastChange());
			System.out.println(i);
		}
		System.out.println("Finished");
		board.print();
	}

	private void doStep() {
		boolean success = doLocalStep();
		if(!success) {
			success = doLineStep();
		}
		
		if(!success) {
			throw new RuntimeException("Could not solve");
		}
	}

	private boolean doLocalStep() {
		Point startingPoint = pointer.getCurrent();
		
		do {
			if(board.get(pointer) == Field.EMPTY) {
				if(nextToDoubleValues()) {
					return true;
				}
				if(inBetweenSameValues()) {
					return true;
				}
			}
			
			pointer.next();
		} while(!(startingPoint.equals(pointer.getCurrent())));
		
		return false;
	}

	private boolean inBetweenSameValues() {
		try {
			if(board.get(pointer.left()) != Field.EMPTY && board.get(pointer.left()) == board.get(pointer.right())) {
				board.set(pointer, board.get(pointer.left()).invert());
				return true;
			}
		} catch(UnknownFieldException e) {
//			e.printStackTrace();
		}
		try {
			if(board.get(pointer.up()) != Field.EMPTY && board.get(pointer.up()) == board.get(pointer.down())) {
				board.set(pointer, board.get(pointer.up()).invert());
				return true;
			}
		} catch(UnknownFieldException e) {
//			e.printStackTrace();
		}
		return false;
	}

	private boolean nextToDoubleValues() {
		try {
			if(board.get(pointer.left()) != Field.EMPTY && board.get(pointer.left()) == board.get(pointer.left().left())) {
				board.set(pointer, board.get(pointer.left()).invert());
				return true;
			}
		} catch(UnknownFieldException e) {
//			e.printStackTrace();
		}
		try {
			if(board.get(pointer.right()) != Field.EMPTY && board.get(pointer.right()) == board.get(pointer.right().right())) {
				board.set(pointer, board.get(pointer.right()).invert());
				return true;
			}
		} catch(UnknownFieldException e) {
//			e.printStackTrace();
		}
		try {
			if(board.get(pointer.up()) != Field.EMPTY && board.get(pointer.up()) == board.get(pointer.up().up())) {
				board.set(pointer, board.get(pointer.up()).invert());
				return true;
			}
		} catch(UnknownFieldException e) {
//			e.printStackTrace();
		}
		try {
			if(board.get(pointer.down()) != Field.EMPTY && board.get(pointer.down()) == board.get(pointer.down().down())) {
				board.set(pointer, board.get(pointer.down()).invert());
				return true;
			}
		} catch(UnknownFieldException e) {
//			e.printStackTrace();
		}
		return false;
	}

	private boolean doLineStep() {
		boolean success;
		for(int i=0; i<board.getSize(); i++) {
			success = doRowStep(i);
			if(success) {
				return true;
			}
			
			success = doColumnStep(i);
			if(success) {
				return true;
			}
		}
		
		return false;
	}

	private boolean doColumnStep(int x) {
		int colOnes = yCount(x, Field.ONE);
		int colZeros = yCount(x, Field.ZERO);
		boolean b = board.getLastChange().equals(new Pointer(board.getSize(), new Point(0,99)));
		
		if(colOnes + colZeros == board.getSize()) {
			// This col is already finished
			return false;
		}
		
		// We've done all ones. Fill the rest with zeroes
		boolean colOnesFull = colOnes == board.getSize()/2;
		if(colOnesFull) {
			if(yFull(x, Field.ZERO)) {
				return true;
			}
		}

		// We've done all zeroes. Fill the rest with ones
		boolean colZerosFull = colZeros == board.getSize()/2;
		if(colZerosFull) {
			if(yFull(x, Field.ONE)) {
				return true;
			}
		}
		
		boolean colOnesMin1Full = colOnes == board.getSize()/2-1;
		boolean colZeroesMin1Full = colZeros == board.getSize()/2-1;

		if(colOnesMin1Full || colZeroesMin1Full) {
			Field maxFieldValue = colOnesMin1Full ? Field.ONE : Field.ZERO;
			
			Integer yTripletStart = yFindStartOfTriplet(x, Field.EMPTY);
			if(yFillNonTriplet(yTripletStart, x, maxFieldValue.invert())) {
				return true;
			}
			
			yTripletStart = yFindStartOfTriplet(x, maxFieldValue.invert());
			if(yFillNonTriplet(yTripletStart, x, maxFieldValue.invert())) {
				return true;
			}
		}
		
		return false;
	}

	private boolean doRowStep(int y) {
		boolean b = board.getLastChange().equals(new Pointer(board.getSize(), new Point(2,1)));
		int rowOnes = xCount(y, Field.ONE);
		int rowZeros = xCount(y, Field.ZERO);
		
		if(rowOnes + rowZeros == board.getSize()) {
			// This row is already finished
			return false;
		}
		
		// We've done all ones. Fill the rest with zeroes
		boolean rowOnesFull = rowOnes == board.getSize()/2;
		if(rowOnesFull) {
			if(xFull(y, Field.ZERO)) {
				return true;
			}
		}

		// We've done all zeroes. Fill the rest with ones
		boolean rowZerosFull = rowZeros == board.getSize()/2;
		if(rowZerosFull) {
			if(xFull(y, Field.ONE)) {
				return true;
			}
		}
		
		boolean rowOnesMin1Full = rowOnes == board.getSize()/2-1;
		boolean rowZeroesMin1Full = rowZeros == board.getSize()/2-1;
		
		if(rowOnesMin1Full || rowZeroesMin1Full) {
			Field maxFieldValue = rowOnesMin1Full ? Field.ONE : Field.ZERO;

			Integer xTripletStart = xFindStartOfTriplet(y, Field.EMPTY);
			if(xFillNonTriplet(xTripletStart, y, maxFieldValue.invert())) {
				return true;
			}
			
			xTripletStart = xFindStartOfTriplet(y, maxFieldValue.invert());
			if(xFillNonTriplet(xTripletStart, y, maxFieldValue.invert())) {
				return true;
			}
		}
		
		if(rowOnesMin1Full && rowZeroesMin1Full) {
			// Do we have rows that matches the current row? Then invert the last two options.
			loopY:for (int y2 = 0; y2 < board.getSize(); y2++) {
				if(y2 == y) {
					// Skip this one: we're looking for another row
					continue;
				}
				
				for(int x=0; x<board.getSize(); x++) {
					if(board.get(x, y2) == Field.EMPTY) {
						// The other row (y2) isn't finished either: skip this row
						continue loopY;
					}
					if(board.get(x, y) != Field.EMPTY && board.get(x, y) != board.get(x, y2)) {
						continue loopY;
					}
				}
				
				// This row matches! Invert the last two fields!
				for(int x=0; x<board.getSize(); x++) {
					if(board.get(x, y) == Field.EMPTY) {
						board.set(x, y, board.get(x, y2).invert());
						return true;
					}
				}
			}
		}
		
		return false;
	}

	private boolean xFull(int y, Field field) {
		for (int x = 0; x < board.getSize(); x++) {
			if(board.get(x, y) == Field.EMPTY) {
				// Fill rest with zeros
				board.set(x, y, field);
				return true;
			}
		}
		return false;
	}

	private boolean yFull(int x, Field field) {
		for (int y = 0; y < board.getSize(); y++) {
			if(board.get(x, y) == Field.EMPTY) {
				// Fill rest with zeros
				board.set(x, y, field);
				return true;
			}
		}
		return false;
	}

	private boolean xFillNonTriplet(Integer xTripletStart, int y, Field leastFieldValue) {
		if(xTripletStart == null) {
			return false;
		}
		
		if(board.get(xTripletStart, y) == leastFieldValue.invert()) {
			board.set(xTripletStart+2, y, leastFieldValue);
		}
		if(board.get(xTripletStart+2, y) == leastFieldValue.invert()) {
			board.set(xTripletStart, y, leastFieldValue);
		}
		
		for(int x=0; x<board.getSize(); x++) {
			// Fill every empty field that is not in the triplet with the inverse value
			if((x < xTripletStart || x > xTripletStart+2) && board.get(x, y) == Field.EMPTY) {
				board.set(x, y, leastFieldValue);
				return true;
			}
		}
		return false;
	}

	private boolean yFillNonTriplet(Integer yTripletStart, int x, Field leastFieldValue) {
		if(yTripletStart == null) {
			return false;
		}
		
		if(board.get(x, yTripletStart) == leastFieldValue.invert()) {
			board.set(x, yTripletStart+2, leastFieldValue);
		}
		if(board.get(x, yTripletStart+2) == leastFieldValue.invert()) {
			board.set(x, yTripletStart, leastFieldValue);
		}
		
		for(int y=0; y<board.getSize(); y++) {
			// Fill every empty field that is not in the triplet with the inverse value
			if((y < yTripletStart || y > yTripletStart+2) && board.get(x, y) == Field.EMPTY) {
				board.set(x, y, leastFieldValue);
				return true;
			}
		}
		return false;
	}

	private Integer xFindStartOfTriplet(int y, Field nextTo) {
		for (int x = 0; x < board.getSize()-2; x++) {
			if(board.get(x, y) == Field.EMPTY && board.get(x+1, y) == Field.EMPTY && board.get(x+2, y) == nextTo) {
				return x;
			}
			if(board.get(x, y) == nextTo && board.get(x+1, y) == Field.EMPTY && board.get(x+2, y) == Field.EMPTY) {
				return x;
			}
		}
		return null;
	}

	private Integer yFindStartOfTriplet(int x, Field nextTo) {
		for (int y = 0; y < board.getSize()-2; y++) {
			if(board.get(x, y) == Field.EMPTY && board.get(x, y+1) == Field.EMPTY && board.get(x, y+2) == nextTo) {
				return y;
			}
			if(board.get(x, y) == nextTo && board.get(x, y+1) == Field.EMPTY && board.get(x, y+2) == Field.EMPTY) {
				return y;
			}
		}
		return null;
	}

	private int xCount(int y, Field field) {
		int sum = 0;
		for (int i = 0; i < board.getSize(); i++) {
			if(board.get(i, y) == field) {
				sum++;
			}
		}
		return sum;
	}

	private int yCount(int x, Field field) {
		int sum = 0;
		for (int i = 0; i < board.getSize(); i++) {
			if(board.get(x, i) == field) {
				sum++;
			}
		}
		return sum;
	}
}
