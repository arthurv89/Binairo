package nl.arthurvlug.binairo.board;
import java.awt.Point;

import nl.arthurvlug.binairo.Utils;
import nl.arthurvlug.binairo.solver.Pointer;


public class Board {
	private int SIZE = 12;
	private int emptyFieldsCount = SIZE * SIZE;
	
	private Field[][] fields = emptyBoard();
	private Pointer lastChange;

	private Field[][] emptyBoard() {
		Field[][] fields = new Field[SIZE][SIZE];
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				fields[i][j] = Field.EMPTY;
			}
		}
		return fields;
	}

	public void set(int x, int y, Field field) {
		if(field != Field.ONE && field != Field.ZERO) {
			throw new RuntimeException("Could not set field to: " + field);
		}
		fields[x][y] = field;
		
		emptyFieldsCount--;
		lastChange = new Pointer(getSize(), new Point(x, y));
	}

	public int getEmptyFieldCount() {
		return emptyFieldsCount;
	}

	public int getSize() {
		return SIZE;
	}

	public Field get(int x, int y) {
		return fields[x][y];
	}

	public Field get(Pointer pointer) {
		Point point = pointer.getCurrent();
		return fields[point.x][point.y];
	}

	public void set(Pointer pointer, Field invert) {
		Point point = pointer.getCurrent();
		fields[point.x][point.y] = invert;
		
		lastChange = pointer;
	}

	public void print() {
		StringBuilder sb = new StringBuilder();
		
		sb.append('\n');
		horizontalLine(sb);
		sb.append('\n');
		
		for(int y=0; y<SIZE; y++) {
			sb.append('|');
			for(int x=0; x<SIZE; x++) {
				char fieldChar = fields[x][y].toChar();
				if(lastChange.getCurrent().x == x && lastChange.getCurrent().y == y) {
					System.out.print(sb.toString());
					System.out.flush();
					Utils.flushSleep();
					System.err.print(fieldChar);
					System.err.flush();
					Utils.flushSleep();
					sb = new StringBuilder();
				} else {
					sb.append(fieldChar);
				}
			}
			sb.append('|');
			sb.append('\n');
		}
		
		horizontalLine(sb);
		sb.append('\n');

		System.out.println(sb.toString());
	}

	private void horizontalLine(StringBuilder sb) {
		sb.append('#');
		for(int j=0; j<SIZE; j++) {
			sb.append('-');
		}
		sb.append('#');
	}

	public Pointer getLastChange() {
		return lastChange;
	}
}
