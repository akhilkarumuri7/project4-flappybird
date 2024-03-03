package game;

import java.awt.Graphics;

public class PipeElement extends Polygon {
	private boolean scored;
	int width, height;

	public PipeElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		this.scored = false;
	}

	public boolean isScored() {
		return scored;
	}

	public void setScored(boolean scored) {
		this.scored = scored;
	}

	public void paint(Graphics brush) {
		int n = getPoints().length;
		int[] xPoints = new int[n], yPoints = new int[n];

		for (int i = 0; i < getPoints().length; i++) {
			xPoints[i] = (int) getPoints()[i].x;
			yPoints[i] = (int) getPoints()[i].y;
		}
		brush.drawPolygon(xPoints, yPoints, n);
	}

	public void move() {
		double velocity = -3;
		position.setX(position.getX() + velocity);
	}

	public void stopMoving() {
		double noVelocity = 0;
		position.setX(position.getX() + noVelocity);
	}
}
