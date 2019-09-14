import java.awt.Color;

public class Main {

	// Possibility: Making the 'Accelerator' function in the 'Main' class.
	// Or making it a class of it's own.

	public static void main(String[] args) {

		// This is not the actual Main class for the project -- Just a testing ground

		EZ.initialize(800, 500);

		Player player1 = new Player("Player.png", 55, 125, 'w', 's', 'a', 'd');
		Player player2 = new Player("CPU.png", 55, 250, 'i', 'k', 'j', 'l');

		while (true) {
			player1.drive();
			player2.drive();
			EZ.refreshScreen();
		}
	}

}
