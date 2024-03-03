package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BirdElement extends Polygon implements Element, KeyListener {

	boolean forward, left, right;
	double verticalVelocity;
	double gravity = 0.5;

	public BirdElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		verticalVelocity = 0;
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
		verticalVelocity += gravity;

		if (forward) {
			verticalVelocity = -10;
			forward = false;
		}

		position.setY(position.getY() + verticalVelocity);

		if (position.getY() >= 600) {
			position.setY(600);
			verticalVelocity = 0;
		}
		else if (position.getY() <= 10) {
			position.setY(10);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			forward = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			forward = false;
		}
	}

}