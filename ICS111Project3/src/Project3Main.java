import java.awt.Color;
import java.io.IOException;

/* (c) Ryota Seki - University of Hawaii at Manoa
 * November 16th, 2017
 * 
 * ICS 111 Project 3 v1.0
 * 
 * This is the main class, it makes objects from the two other classes and incorporates them into the game. 
 * The game is a simple car racing game, where the player races against another car (which is either the computer or another player)
 * Both cars are free to move around the map, but if they hit a wall, they will bounce in the other direction (work in progress)
 * A "Boost" bar will be implemented in a future version of this project, where each player can use a boost to get through the game faster
 * The boost is determined by a value shown by a rectangle that will boost the car by the amount of the value of boost
 * 
 */

public class Project3Main {

	
	//member variables to hold text - rs 12/3
	private static EZText instructions;
	private static EZText title;
	private static EZText one;
	private static EZText two;
	private static EZText three;
	private static EZText player1steps;
	private static EZText player2steps;
	private static EZText winGame;
	private static EZText boost;
	private static EZText advance;
	private static EZText go;
	
	private static EZSound dangerZone;
	
	//member funciton that assigns values to text variables - rs 12/3
	public static void text() {
		instructions = EZ.addText(1395 / 2, 50, "INSTRUCTIONS");
		title = EZ.addText(1395 / 2, 775 / 4, "MAP SELECTION");
		one = EZ.addText(1395 / 4, 775 / 2, "Map 1");
		two = EZ.addText(1395 / 2, 775 / 2, "Map 2");
		three = EZ.addText(1395 / 4 * 3, 775 / 2, "Map 3");
		player1steps = EZ.addText(1395 / 2, 200, "Player 1 is controlled by the 'w' 'a' 's' 'd' keys!");
		player2steps = EZ.addText(1395 / 2, 300, "Player 2 is controlled by the 'i' 'j' 'k' 'l' keys!");
		winGame = EZ.addText(1395 / 2, 400, "Whoever reaches the checkered flag first wins!");
		boost = EZ.addText(1395 / 2, 500, "The gas cans give you a little speed boost if you grab them!");
		advance = EZ.addText(1395 / 2, 600, "Click 'go' to proceed!");
		go = EZ.addText(1395 / 2, 700, "GO");
		
		instructions.fontSize = 100;
		player1steps.fontSize = 25;
		player2steps.fontSize = 25;
		winGame.fontSize = 25;
		boost.fontSize = 25;
		advance.fontSize = 25;
		go.fontSize = 50;
		title.fontSize = 100;
		one.fontSize = 50;
		two.fontSize = 50;
		three.fontSize = 50;
		
		instructions.show();
		player1steps.show();
		player2steps.show();
		winGame.show();
		boost.show();
		advance.show();
		go.show();
		title.hide();
		one.hide();
		two.hide();
		three.hide();
	}
	
	public static void assignSound() {
		dangerZone = EZ.addSound("dangerzone1.wav");
	}
	
	public static boolean collisions(Player player, int xstep, int ystep) {
		int x = player.getXPosition() + xstep;
		int y = player.getYPosition() + ystep;
		for (int i = 0; i < Map.w; i++) {
			if (Map.walls[i].isPointInElement(x, y)) {
				return true;
			}
		}
		return false;
	}

	// Gas collision function for the player to collect, and make it "disappear." -
	// JW 12/2/2017
	public static boolean gasCollision(Player player) {
		int x = player.getXPosition();
		int y = player.getYPosition();
		EZSound boost = EZ.addSound("boost.wav"); // Added sound effect for booster - JW 12/2/2017
		for (int i = 0; i < Map.g; i++) {
			if (Map.gas[i].isPointInElement(x, y)) {
				Map.gas[i].translateTo(-20, -20);
				boost.play();
				return true;
			}
		}
		return false;
	}
	
	//checks which player hit the finish line first - rs 12/3
	public static boolean finishCollision(Player player) {
		int x = player.getXPosition();
		int y = player.getYPosition();
		for (int i = 0; i < Map.f; i++) {
			if(Map.finish[i].isPointInElement(x, y)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws java.io.IOException {
		// initializing the screen
		EZ.initialize(1395, 775);
		
		text();
		//adds background music
		assignSound();
		dangerZone.play();
		// make 2 car objects, one for each player, parameters for different keys
		Player player1 = new Player("bb8.png", 55, 675, 'w', 's', 'a', 'd'); // Replaced the car with BB-8 picture - JW 12/3/2017
		Player player2 = new Player("bb9.png", 55, 725, 'i', 'k', 'j', 'l'); // Replaced the car with BB-9 picture - JW 12/3/2017
		player1.scaleCars();
		player2.scaleCars();
		// hiding cars when on title screen
		player1.hide();
		player2.hide();
		// run game loop
		long initTime = System.currentTimeMillis();
		while (true) {

			
			// locating where the player clicked
			int clickx = EZInteraction.getXMouse();
			int clicky = EZInteraction.getYMouse();
			// checking for a click
			if (EZInteraction.wasMouseLeftButtonPressed()) {
				// checking if map 1 was chosen, if it was it will load
				if (go.isPointInElement(clickx, clicky)) {
					instructions.hide();
					go.hide();
					player1steps.hide();
					player2steps.hide();
					winGame.hide();
					boost.hide();
					advance.hide();
					go.hide();
					one.show();
					two.show();
					three.show();
					title.show();
				}
				if (one.isPointInElement(clickx, clicky)) {
					one.hide();
					two.hide();
					three.hide();
					title.hide();
					Map m = new Map(1);
					m.loadMap();
					player1.show();
					player2.show();
					// checking if map 2 was chosen, if so it will load
				} else if (two.isPointInElement(clickx, clicky)) {
					one.hide();
					two.hide();
					three.hide();
					title.hide();
					Map m = new Map(2);
					m.loadMap();
					player1.show();
					player2.show();
					// checking if map 3 was chosen, if so it will load
				} else if (three.isPointInElement(clickx, clicky)) {
					one.hide();
					two.hide();
					three.hide();
					title.hide();
					Map m = new Map(3);
					m.loadMap();
					player1.show();
					player2.show();
				}
			}

			// checking if player pressed w key
			if (EZInteraction.isKeyDown('w')) {
				// checking for collision with walls
				if (!collisions(player1, 0, -player1.speed)) {
					player1.translateBy(0, -player1.speed); // changed to translate by to move up when up key is pressed
															// - ts
				}
				// checking if player pressed s key
			} else if (EZInteraction.isKeyDown('s')) {
				if (!collisions(player1, 0, player1.speed)) {
					// checking for collision with walls
					player1.translateBy(0, player1.speed); // changed to translate by to move down - ts
				}
				// checking if player pressed a key
			} else if (EZInteraction.isKeyDown('a')) {
				if (!collisions(player1, -player1.speed, 0)) {
					// checking for collision with walls
					player1.translateBy(-player1.speed, 0); // changed to translate by to move left - ts
				}
				// checking if player pressed d key
			} else if (EZInteraction.isKeyDown('d')) {
				if (!collisions(player1, player1.speed, 0)) {
					// checking for collision with walls
					player1.translateBy(player1.speed, 0); // changed to translate by to move right - ts
				}
			}
			// checking if player pressed i key
			if (EZInteraction.isKeyDown('i')) {
				// checking for collision with walls
				if (!collisions(player2, 0, -player2.speed)) {
					player2.translateBy(0, -player2.speed); // changed to translate by to move up when up key is pressed
															// - ts // - ts
				}
				// checking if player pressed k key
			} else if (EZInteraction.isKeyDown('k')) {
				// checking for collision with walls
				if (!collisions(player2, 0, player2.speed)) {
					player2.translateBy(0, player2.speed); // changed to translate by to move down - ts
				}
				// checking if player pressed j key
			} else if (EZInteraction.isKeyDown('j')) {
				// checking for collision with walls
				if (!collisions(player2, -player2.speed, 0)) {
					player2.translateBy(-player2.speed, 0); // changed to translate by to move left - ts
				}
				// checking if player pressed l key
			} else if (EZInteraction.isKeyDown('l')) {
				// checking for collision with walls
				if (!collisions(player2, player2.speed, 0)) {
					player2.translateBy(player2.speed, 0); // changed to translate by to move right - ts
					// System.out.println("test");
				}
			}

			if (gasCollision(player1)) { // Call gas function -JW 12/2/2017
				player1.speed += 5;
			}
			if (gasCollision(player2)) {
				player2.speed += 5;
			}

			long newTime = System.currentTimeMillis(); // Timer to decrease speed back to original speed - JW 12/2/2017
			if (newTime - initTime >= 500) {
				player1.speed--;
				player2.speed--;
				if (player1.speed < 2) {
					player1.speed = 2;
				}
				if (player2.speed < 2) {
					player2.speed = 2;
				}
				initTime = newTime;
			}
			
			//finish line collision - rs 12/3
			if (finishCollision(player1)) {
				
				EZ.addRectangle(1395/2, 775 / 4, 900, 100, Color.WHITE, true);
				EZText p1win = EZ.addText(1395 / 2, 775 / 4, "PLAYER 1 WINS");
				
				p1win.fontSize = 100;
				EZSound win = EZ.addSound("cheehoo.wav");
				win.play();
				break;
			}
			
			//finish line collision - rs 12/3
			if(finishCollision(player2)) {
				
				EZ.addRectangle(1395/2, 775 / 4, 900, 100, Color.WHITE, true);
				EZText p2win = EZ.addText(1395 / 2, 775 / 4, "PLAYER 2 WINS");
				
				p2win.fontSize = 100;
				EZSound win = EZ.addSound("cheehoo.wav");
				win.play();
				break;
			}

			EZ.refreshScreen();

		}
		// run win or lose conditions

	}
}
