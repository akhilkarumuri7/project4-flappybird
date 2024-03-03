package game;

import java.awt.Graphics;
import java.util.ArrayList;

public class PipeElement extends Polygon implements Element {
	private boolean scored;
	int width, height;

	public PipeElement(Point[] inShape, Point inPosition, double inRotation, int width,
			int height) {
		super(inShape, inPosition, inRotation);
		this.scored = false;
		this.width = width;
		this.height = height;
	}

	public boolean isScored() {
		return scored;
	}

	public void setScored(boolean scored) {
		this.scored = scored;
	}

	@Override
	public Point[] getPoints() {
		ArrayList<Point> points = new ArrayList<>();
		points.add(new Point((int) position.getX(), (int) position.getY()));
		points.add(new Point((int) position.getX() + width, (int) position.getY()));
		points.add(
				new Point((int) position.getX() + width, (int) position.getY() + height));
		points.add(new Point((int) position.getX(), (int) position.getY() + height));
		return points.toArray(new Point[0]);
	}

	public void paint(Graphics brush) {
		brush.fillRect((int) position.getX(), (int) position.getY(), width, height);
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
