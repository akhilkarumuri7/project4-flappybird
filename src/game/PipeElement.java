package game;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * A PipeElement is one of the obstacles while playing the game. Green pipes generate from
 * the right of the screen and move towards the left, and if the BirdElement comes in
 * contact to a PipeElement the game is over. PipeElements are stored in an ArrayList.
 * 
 *
 */

public class PipeElement extends Polygon implements Element {
	private boolean scored;
	int width, height;

	/**
	 * Initializes a PipeElement with the shape, position, and rotation, width, and
	 * height.
	 * 
	 * @param inShape
	 * @param inPosition
	 * @param inRotation
	 * @param width
	 * @param height
	 */

	public PipeElement(Point[] inShape, Point inPosition, double inRotation, int width,
			int height) {
		super(inShape, inPosition, inRotation);
		Point[] points = getPoints();
		setPoints(points);
		this.scored = false;
		this.width = width;
		this.height = height;
	}

	/**
	 * 
	 * @return true if scored
	 */
	public boolean isScored() {
		return scored;
	}

	/**
	 * Sets the pipe to scored.
	 * 
	 * @param scored
	 */
	public void setScored(boolean scored) {
		this.scored = scored;
	}

	/**
	 * Ovverides Polygon's getPoints method so that points can be accurately generated.
	 * For some reason, polygon was not drawing rectangles properly, so we used fillRect()
	 * instead, took the points from that shape and put them in an array of Points
	 * 
	 * @return array of Points
	 */
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

	/**
	 * Implements the paint method from the Element interface Draws a tube in the frame
	 * 
	 * @param brush
	 */
	public void paint(Graphics brush) {
		brush.fillRect((int) position.getX(), (int) position.getY(), width, height);
	}

	/**
	 * Implements the move method from the Element interface to move an PipeElement
	 */
	public void move() {
		double velocity = -3;
		position.setX(position.getX() + velocity);
	}

	/**
	 * Stops the tube from moving across the screen
	 */
	public void stopMoving() {
		double noVelocity = 0;
		position.setX(position.getX() + noVelocity);
	}
}
