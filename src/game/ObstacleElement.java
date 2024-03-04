package game;

import java.awt.Graphics;
import java.util.function.Consumer;

/**
 * A ObstacleElement is one of the obstacles while playing the game. Rotating plus symbols
 * generate from the right of the screen and move towards the left, and if the BirdElement
 * comes in contact to a ObstacleElement the game is over. ObtsacleElements are stored in
 * an ArrayList.
 */

public class ObstacleElement extends Polygon implements Element {

	/**
	 * Initializes an ObstacleElement with the shape, position, and rotation
	 * 
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 */
	public ObstacleElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);

	}

	/**
	 * Implements the paint method from the Element interface to draw an ObstacleElement
	 * 
	 * @param brush
	 */
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

	/**
	 * Implements the move method from the Element interface to move an ObstacleElement
	 * across the screen
	 */
	@Override
	public void move() {
		double velocity = -3;
		position.setX(position.getX() + velocity);
		rotateAction.accept(3);
		// rotate(3);

	}

	// lamda expression to rotate the ObstacleElement
	Consumer<Integer> rotateAction = degrees -> {
		rotation = (rotation + degrees) % 360;
	};
}
