package game;

import java.awt.Graphics;

/*
 * Defines functionality of an Element
 */
public interface Element {

	/*
	 * Implements drawing an element on the screen
	 * 
	 * @param brush
	 */
	public void paint(Graphics brush);

	/*
	 * Implements drawing an moving an element across the screen
	 */
	public void move();
}
