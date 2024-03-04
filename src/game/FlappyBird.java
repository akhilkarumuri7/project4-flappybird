package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//FlappyBird class, extends the Game class
class FlappyBird extends Game {
	// Static variables for game score and game over state
	static int counter = 0;
	static boolean gameOver = false;

	// Inner class for generating pipes and obstacles
	private PipeGenerator pipeGenerator;
	private ObstacleGenerator obstacleGenerator;

	// Instance variables
	boolean started;
	Random random = new Random();

	// Constructor
	public FlappyBird() {
		super("Flappy Bird!", 430, 770);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(b);
		pipeGenerator = new PipeGenerator();
		pipeGenerator.addPipe();
		obstacleGenerator = new ObstacleGenerator();
	}

	// Array of points representing the bird's shape
	Point[] birdPoints = { new Point(0, 0), new Point(10, 0), new Point(10, 10),
			new Point(0, 10) };

	// Array of points representing the obstacle's shape
	Point[] obstaclePoints = { new Point(0, 0), new Point(10, 0), new Point(10, 10),
			new Point(20, 10), new Point(20, 20), new Point(10, 20), new Point(10, 30),
			new Point(0, 30), new Point(0, 20), new Point(-10, 20), new Point(-10, 10),
			new Point(0, 10) };

	// Variables for game layout and positioning
	int middleLevel = height / 2;
	int leftEdge = 10;
	int blockSize = 10;
	int blockX = width / 2 - 100;
	int blockY = middleLevel - blockSize / 2;
	Point blockPosition = new Point(blockX, blockY);

	// BirdElement instance
	BirdElement b = new BirdElement(birdPoints, blockPosition, 0);

	// ArrayLists for pipes and obstacles
	ArrayList<PipeElement> pipes = new ArrayList<PipeElement>();
	ArrayList<ObstacleElement> obstacles = new ArrayList<ObstacleElement>();

	// Inner class for generating pipes
	class PipeGenerator {
		public void addPipe() {
			int space = 150;
			int h = 100 + random.nextInt(300); // Height of the gap between pipes
			int yTopPipe = 0; // Y-coordinate of the top pipe
			int yBottomPipe = h + 150; // Y-coordinate of the bottom pipe

			// Points for the upper pipe
			Point[] upperPipePoints = { new Point(0, 0), new Point(50, 0),
					new Point(50, h), new Point(0, h) };

			// Points for the lower pipe
			Point[] lowerPipePoints = { new Point(0, 0), new Point(50, 0),
					new Point(50, 800 - h - 150), new Point(0, 800 - h - 150) };

			// Add upper pipe
			pipes.add(new PipeElement(upperPipePoints,
					new Point(300 + pipes.size() * space, yTopPipe), 0, 50, h));

			// Add lower pipe
			pipes.add(new PipeElement(lowerPipePoints,
					new Point(300 + (pipes.size() - 1) * space, yBottomPipe), 0, 50,
					610 - yBottomPipe));
		}
	}

	// Inner class for generating obstacles
	class ObstacleGenerator {
		public void addObstacle() {
			int numObstacles = random.nextInt(3); // Generate random number of obstacles
			// between 0 and 2
			int space = 150;
			int h = 100 + random.nextInt(300);
			for (int i = 0; i < numObstacles; i++) {
				obstacles.add(new ObstacleElement(obstaclePoints,
						new Point(350 + (obstacles.size()) * space + random.nextInt(50),
								100 + random.nextInt(300)),
						0));
			}
		}
	}

	// anonymous class for the game over screen
	Element gameOverScreen = new Element() {
		public void paint(Graphics brush) {
			// Draw game over screen elements
			brush.setColor(Color.white);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.black);
			brush.drawString("Game Over", width / 2 - 50, height / 2);
			brush.drawString("Score: " + counter / 2, width / 2 - 30, height / 2 + 20);
		}

		public void move() {
		}
	};

	// Paint method to render game elements
	public void paint(Graphics brush) {
		if (!gameOver) {
			// Draw background
			brush.setColor(new Color(61, 196, 194));
			brush.fillRect(0, 0, width, height);

			// Draw ground
			brush.setColor(Color.green);
			brush.fillRect(0, 610, width, 15);

			// Draw sky
			brush.setColor(new Color(237, 205, 116));
			brush.fillRect(0, 625, width, 150);

			// Draw score
			brush.setColor(Color.white);
			brush.drawString("Score is " + counter / 2, 10, 10);

			// Move and draw bird
			brush.setColor(Color.yellow);
			b.move();
			b.paint(brush);

			// Generate pipes if needed
			brush.setColor(Color.green.darker());
			if (pipes.size() < 8) {
				pipeGenerator.addPipe();
			}

			// Generate obstacles if needed
			if (obstacles.size() < 10) {
				obstacleGenerator.addObstacle();
			}
		}
		else {
			gameOverScreen.paint(brush);
		}

		// Iterate over pipes
		Iterator<PipeElement> iteratorP = pipes.iterator();
		while (iteratorP.hasNext()) {
			PipeElement p = iteratorP.next();
			if (!gameOver) {
				// Move and draw pipes
				p.move();
				p.paint(brush);
			}
			if (!p.isScored()
					&& p.position.getX() + p.getPoints()[1].getX() < b.position.getX()) {
				// Update score if pipe is passed
				p.setScored(true);
				counter++;
			}
			if (b.collides(p)) {
				// Set game over if bird collides with pipe
				gameOver = true;
				p.stopMoving();
			}
			if (p.position.getX() <= 0) {
				// Remove pipes that have passed off the screen
				iteratorP.remove();
			}
		}

		// Iterate over obstacles
		Iterator<ObstacleElement> iteratorO = obstacles.iterator();
		while (iteratorO.hasNext()) {
			ObstacleElement o = iteratorO.next();
			if (!gameOver) {
				// Move and draw obstacles
				o.move();
				brush.setColor(Color.white);
				o.paint(brush);
			}

			if (b.collides(o)) {
				// Set game over if bird collides with obstacle
				gameOver = true;
			}
			if (o.position.getX() <= 0) {
				// Remove obstacles that have passed off the screen
				iteratorO.remove();
			}

			// Check for collisions between obstacles and pipes
			Iterator<PipeElement> iteratorP2 = pipes.iterator();
			while (iteratorP2.hasNext()) {
				PipeElement pipe = iteratorP2.next();
				if (o.collides(pipe)) {
					// Remove the obstacle if it collides with a pipe
					iteratorO.remove();
					break; // No need to check for collisions with other pipes
				}
			}
		}
	}

	// Main method to start the game
	public static void main(String[] args) {
		FlappyBird a = new FlappyBird();
		a.repaint();
	}
}