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

class FlappyBird extends Game {
	static int counter = 0;
	static boolean gameOver = false;

	boolean started;
	Random random = new Random();

	public FlappyBird() {
		super("Flappy Bird!", 430, 770);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(b);
		addPipe(started);
	}

	Point[] birdPoints = { new Point(0, 0), new Point(10, 0), new Point(10, 10),
			new Point(0, 10) };

	Point[] obstaclePoints = { new Point(0, 0), new Point(10, 0), new Point(10, 10),
			new Point(20, 10), new Point(20, 20), new Point(10, 20), new Point(10, 30),
			new Point(0, 30), new Point(0, 20), new Point(-10, 20), new Point(-10, 10),
			new Point(0, 10) };

	int middleLevel = height / 2;
	int leftEdge = 10;
	int blockSize = 10;
	int blockX = width / 2 - 100;
	int blockY = middleLevel - blockSize / 2;
	Point blockPosition = new Point(blockX, blockY);

	BirdElement b = new BirdElement(birdPoints, blockPosition, 0);
	ArrayList<PipeElement> pipes = new ArrayList<PipeElement>();

	ArrayList<ObstacleElement> obstacles = new ArrayList<ObstacleElement>();

	public void addPipe(boolean started) {
		int space = 150;
		int h = 100 + random.nextInt(300);
		Point[] upperPipePoints = { new Point(0, 0), new Point(50, 0), new Point(50, h),
				new Point(0, h) };
		Point[] lowerPipePoints = { new Point(0, 0), new Point(50, 0),
				new Point(50, 600 - h - 150), new Point(0, 600 - h - 150) };

		pipes.add(new PipeElement(upperPipePoints,
				new Point(300 + pipes.size() * space, 0), 0));

		pipes.add(new PipeElement(lowerPipePoints,
				new Point(300 + (pipes.size() - 1) * space, h + 150), 0));

	}

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

	// single pipe element
	// PipeElement p = new PipeElement(pipePoints, new Point(100, 0), 0);

	public void paint(Graphics brush) {
		if (!gameOver) {
			brush.setColor(new Color(61, 196, 194));
			brush.fillRect(0, 0, width, height);

			brush.setColor(Color.green);
			brush.fillRect(0, 610, width, 15);

			brush.setColor(new Color(237, 205, 116));
			brush.fillRect(0, 625, width, 150);

			brush.setColor(Color.white);
			brush.drawString("Score is " + counter / 2, 10, 10);

			b.move();
			b.paint(brush);

			brush.setColor(Color.green.darker());

			if (pipes.size() < 8) {
				addPipe(started);
			}

			if (obstacles.size() < 10) {
				addObstacle();
			}

		}
		else {
			brush.setColor(Color.white);
			brush.fillRect(0, 0, width, height);
			brush.setColor(Color.black);
			brush.drawString("Game Over", width / 2 - 50, height / 2);
			brush.drawString("Score: " + counter / 2, width / 2 - 30, height / 2 + 20);
		}

		Iterator<PipeElement> iteratorP = pipes.iterator();
		while (iteratorP.hasNext()) {
			PipeElement p = iteratorP.next();
			if (!gameOver) {
				p.move();
				p.paint(brush);
			}
			if (!p.isScored()
					&& p.position.getX() + p.getPoints()[1].getX() < b.position.getX()) {
				p.setScored(true);
				counter++;
			}
			if (b.collides(p)) {
				gameOver = true;
				p.stopMoving();
			}
			if (p.position.getX() <= 0) {
				iteratorP.remove();
			}
		}

		Iterator<ObstacleElement> iteratorO = obstacles.iterator();
		while (iteratorO.hasNext()) {
			ObstacleElement o = iteratorO.next();
			if (!gameOver) {
				o.move();
				brush.setColor(Color.white);
				o.paint(brush);
			}

			if (b.collides(o)) {
				gameOver = true;
			}
			if (o.position.getX() <= 0) {
				iteratorO.remove();
			}

			Iterator<PipeElement> iteratorP2 = pipes.iterator();
			while (iteratorP2.hasNext()) {
				PipeElement pipe = iteratorP2.next();
				if (o.collides(pipe)) {
					iteratorO.remove(); // Remove the obstacle if it collides with a pipe
					break; // No need to check for collisions with other pipes
				}
			}
		}

		// pipes.removeIf(p -> p.position.getX() + p.getPoints()[1].getX() < 0);

		// if (counter % 500 == 0) {
		// addPipe(started);
		// }

	}

	public static void main(String[] args) {
		FlappyBird a = new FlappyBird();
		a.repaint();
	}
}