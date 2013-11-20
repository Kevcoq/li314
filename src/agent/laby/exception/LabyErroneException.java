package agent.laby.exception;

import java.awt.Point;

public class LabyErroneException extends Exception {

	private static final long serialVersionUID = 1L;
	private Point point;

	public LabyErroneException(Point point, String s) {
		super(s);
		this.point = point;

	}

	public Point getPoint() {
		return point;
	}

}
