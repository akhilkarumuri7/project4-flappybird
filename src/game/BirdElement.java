package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class represents the bird element in the Flappy Bird game.
 * Extends Polygon and implements Element and KeyListener interfaces.
 */
public class BirdElement extends Polygon implements Element, KeyListener {

	boolean forward, left, right;
	double verticalVelocity;
	double gravity = 0.5;

	 /**
     * The parameter constructs a BirdElement object with the specified shape, 
     * position, and rotation.
     * This also sets the verticalVelocity to 0
     * 
     * @param inShape     the shape of the bird element
     * @param inPosition  the position of the bird element
     * @param inRotation  the rotation angle of the bird element
     */
	public BirdElement(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
		verticalVelocity = 0;
	}
    
	/**
     * Paints the bird element on the specified graphics context.
     * Declares two arrays, for x and y points and sets it to same length n 
     * For loop uses casting (casts to int) and iterates through the points and 
     * it assigns the x/y-coordinate of the i-th point in the shape to the i-th 
     * element of the array 
     * 
     * @param brush the graphics context to paint on
     */
	public void paint(Graphics brush) {
		int n = getPoints().length;
		int[] xPoints = new int[n], yPoints = new int[n];

		for (int i = 0; i < getPoints().length; i++) {
			xPoints[i] = (int) getPoints()[i].x;
			yPoints[i] = (int) getPoints()[i].y;
		}
		brush.drawPolygon(xPoints, yPoints, n);
	}

	/**
     * Moves the bird element based on its velocity and gravity.
     * Adds the effect of gravity on the vertical velocity overtime. 
     * When forward is true (space bar pressed) then -8 is added to vertical 
     * velocity, causing it to move upwards. Then position is updated based on 
     * value of vertical velocity. 
     * Also upper and lower boundary cutoffs, vertical velocity is 0 if reaches 
     * top.
     * Handles user input to make the bird jump.
     */
	
	public void move() {
		verticalVelocity += gravity;

		if (forward) {
			verticalVelocity = -8;
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

	/**
     * Responds to key press events by initiating a forward movement.
     * Sets forward to true 
     * @param e  the key event
     */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			forward = true;
		}

	}

	/**
     * Responds to key release events by stopping the forward movement.
     * Sets forward to false 
     * @param e  the key event
     */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			forward = false;
		}
	}

}
