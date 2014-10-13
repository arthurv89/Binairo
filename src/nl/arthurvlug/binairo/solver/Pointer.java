package nl.arthurvlug.binairo.solver;
import java.awt.Point;


public class Pointer {
	private Point current = new Point(0, 0);
	private int size;
	
	public Pointer(int size, Point point) {
		this.size = size;
		this.current = (Point) point.clone();
	}

	public Pointer(int size) {
		this.size = size;
	}

	@Override
	protected Pointer clone() throws CloneNotSupportedException {
		return new Pointer(size, current);
	}

	public Point next() {
		int x = current.x;
		int y = current.y;
		
		x++;
		if(x == size) {
			x = 0;
			y++;
			
			if(y == size) {
				y = 0;
			}
		}
		current = new Point(x, y);
		return current;
	}

	public Point getCurrent() {
		return current;
	}

	private boolean exists(Point point) {
		return point.x >= 0 && point.y >= 0 && point.x < size && point.y < size; 
	}

	public Pointer left() {
		Point point = new Point(current.x-1, current.y);
		if(!exists(point)) {
			throw new UnknownFieldException(point);
		}
		return new Pointer(size, point);
	}

	public Pointer right() {
		Point point = new Point(current.x+1, current.y);
		if(!exists(point)) {
			throw new UnknownFieldException(point);
		}
		return new Pointer(size, point);
	}

	public Pointer down() {
		Point point = new Point(current.x, current.y+1);
		if(!exists(point)) {
			throw new UnknownFieldException(point);
		}
		return new Pointer(size, point);
	}

	public Pointer up() {
		Point point = new Point(current.x, current.y-1);
		if(!exists(point)) {
			throw new UnknownFieldException(point);
		}
		return new Pointer(size, point);
	}
	
	@Override
	public String toString() {
		return "[" + current.x + "," + current.y + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Pointer other = (Pointer) obj;
		return size == other.size && current.equals(other.current);
	}
}
