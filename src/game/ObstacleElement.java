package game;

import java.awt.Graphics;
import java.util.function.Consumer;

public class ObstacleElement extends Polygon implements Element {

	public ObstacleElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);

	}

	@Override
	public void paint(Graphics brush) {
		int n = getPoints().length;
		int[] xPoints = new int[n], yPoints = new int[n];

		for (int i = 0; i < getPoints().length; i++) {
			xPoints[i] = (int) getPoints()[i].x;
			yPoints[i] = (int) getPoints()[i].y;
		}
		brush.fillPolygon(xPoints, yPoints, n);
	}

	@Override
	public void move() {
		double velocity = -3;
		position.setX(position.getX() + velocity);
		rotateAction.accept(3);
		//rotate(3);

	}

	Consumer<Integer> rotateAction = degrees -> {
	    rotation = (rotation + degrees) % 360;
	};
}
