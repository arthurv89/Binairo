package nl.arthurvlug.binairo.solver;
import java.awt.Point;


@SuppressWarnings("serial")
public class UnknownFieldException extends RuntimeException {

	public UnknownFieldException(Point point) {
		super("Unknown point: [" + point.x + ", " + point.y + "]");
	}

}
