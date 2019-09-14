// November 16, 2017
// 2017 Jackie Wong - University of Hawaii at Manoa
public class Player {

	// Player object member variables for the constructor
	EZImage player;
	static int posX;
	static int posY;
	static int xdir;
	static int ydir;

	// Directional keys for the constructor
	char up;
	char down;
	char left;
	char right;

	// adding variables that store the images of the wall arrays from the map class
	// - RS 11/19/2017
	static EZImage[] wall;

	// member varible that stores amount for wall array from map class - rs
	// 11/19/2017
	static int wallCount;

	// Speed of the car
	public int speed = 2;

	// Constructor for player object
	Player(String filename, int x, int y, char ch1, char ch2, char ch3, char ch4) {
		posX = x;
		posY = y;
		player = EZ.addImage(filename, posX, posY);
		up = ch1;
		down = ch2;
		left = ch3;
		right = ch4;
	}

	// member function to call wall value from map class and assign that value to
	// wallCount - rs 11/19/2017
	public static void wallCount() {
		wallCount = Map.w;
	}

	// member function that assigns wall values from map to the member variable wall
	// - rs 11/19/2017
	public static void wallValues() {
		wall = Map.walls;
	}

	// member function that gets the x and y postion from getting the center of the
	int getXPosition() {
		posX = player.getXCenter();
		return posX;
	}
	int getYPosition() {
		posY = player.getYCenter();
		return posY;
	}

	// member function that assigns values to xdir and ydir - rs 11/19/2017
	void direction() {
		xdir = 3;
		ydir = 3;
	}

	// function that scales the image to a more appropriate size for the map to make
	// both cars fit in the map - rs 11/19/2017
	void scaleCars() {
		player.scaleTo(0.5);
	}

	public boolean collisions(EZImage player, int xstep, int ystep) {
		int x = player.getXCenter() + xstep;
		int y = player.getYCenter() + ystep;
		for (int i = 0; i < wallCount; i++) {
			if (wall[i].isPointInElement(x, y)) {
				return true;
			}
		}
		return false;
	}
	void drive() {
		// If the 'up' key is pressed, then move forward 5 pixels.
		if (EZInteraction.isKeyDown(up))  {
			if (!collisions(player, 0, -speed)) {
			player.translateBy(0, -speed); //changed to translate by to move up when up key is pressed - ts
			System.out.println("hey");
		}
		} else if (EZInteraction.isKeyDown(down)) {
			if (!collisions(player, 0, speed)) {
			player.translateBy(0, speed); // changed to translate by to move down - ts
			}
		} else if (EZInteraction.isKeyDown(left)) {
			if (!collisions(player, -speed, 0)) {
			player.translateBy(-speed, 0); //changed to translate by to move left - ts
			}
		} else if (EZInteraction.isKeyDown(right)) {
			if (!collisions(player, speed, 0)) {
			player.translateBy(speed, 0); //changed to translate by to move right - ts
			System.out.println("test");
			}
		}
	}
	
	//test member function, sees if the car is able to stop when it hits the boundary of the window
	public void testCollision() {
		if (player.isPointInElement(500, 500)) {
			xdir = -xdir;
		}
	}

	public void translateBy(int i, int j) {
		player.translateBy(i, j);
		
	}
	//added to hide cars for opening screen -ts
	public void hide() {
		player.hide();
	}
	//added to show cars after map chosen -ts
	public void show() {
		player.show();
	}

}
