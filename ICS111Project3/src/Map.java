//copyright 2017 Trey Sumida
import java.io.FileReader;
import java.util.Scanner;

public class Map {

	//member variables
	public static int x = 500;
	public static int y = 50;
	public static int w;
	public static int g; // -JW 12/2/2017
	public static int f; //rs 12/3
	public static int map;
	public static EZImage[] walls = new EZImage[x];
	public static EZImage[] gas = new EZImage[y]; // Array for adding the gas booster images -JW 12/2/2017
	public static EZImage[] finish = new EZImage[y]; // array for finish line images - rs 12/3

	//constructor taking in what map to choose
	Map(int a) {
		map = a;
	}
	
	//member functions that get height and width of windows
	
	//function to choose the map
	void loadMap() throws java.io.IOException {
		//switches depending on what map is chosen
		switch (map) {
		//load map 1
		case 1:
			Scanner fs = new Scanner(new FileReader("map1.txt"));
			int width = fs.nextInt();
			int height = fs.nextInt();
			String input = fs.nextLine();
			for (int row = 0; row < height; row++) {

				input = fs.nextLine();
				System.out.println(input);
				for (int column = 0; column < input.length(); column++) {

					char ch = input.charAt(column);
					switch (ch) {
					case 'w':
						walls[w] = EZ.addImage("wall.png", column * 32, row * 32);
						w++;
						break;
					case 'g':		// Load gas images - JW 12/2/2017
						gas[g] = EZ.addImage("Gas.png", column * 32, row * 32);
						g++;
						break;
					case 'f':	//load finish line images - rs 12/3
						finish[f] = EZ.addImage("finish.png", column * 32, row * 32);
						f++;
						break;
					default:
						break;
					}
				}
			}
			break;
			//load map 2
		case 2:
			Scanner fs1 = new Scanner(new FileReader("map2.txt"));
			int width1 = fs1.nextInt();
			int height1 = fs1.nextInt();
			String input1 = fs1.nextLine();
			for (int row = 0; row < height1; row++) {

				input1 = fs1.nextLine();
				System.out.println(input1);
				for (int column = 0; column < input1.length(); column++) {

					char ch = input1.charAt(column);
					switch (ch) {
					case 'w':
						walls[w] = EZ.addImage("wall.png", column * 32, row * 32);
						w++;
						break;
					case 'g':		// Load gas images - JW 12/2/2017
						gas[g] = EZ.addImage("Gas.png", column * 32, row * 32);
						g++;
						break;
					case 'f':	//load finish line images - rs 12/3
						finish[f] = EZ.addImage("finish.png", column * 32, row * 32);
						f++;
						break;
					default:
						break;
					}
				}
			}
			break;
			//load map 3
		case 3:
			Scanner fs2 = new Scanner(new FileReader("map3.txt"));
			int width2 = fs2.nextInt();
			int height2 = fs2.nextInt();
			String input2 = fs2.nextLine();
			for (int row = 0; row < height2; row++) {

				input2 = fs2.nextLine();
				System.out.println(input2);
				for (int column = 0; column < input2.length(); column++) {

					char ch = input2.charAt(column);
					switch (ch) {
					case 'w':
						walls[w] = EZ.addImage("wall.png", column * 32, row * 32);
						w++;
						break;
					case 'g':		// Load gas images - JW 12/2/2017
						gas[g] = EZ.addImage("Gas.png", column * 32, row * 32);
						g++;
						break;
					case 'f':	//load finish line images - rs 12/3
						finish[f] = EZ.addImage("finish.png", column * 32, row * 32);
						f++;
						break;
					default:
						break;
					}
				}
			}
			break;
		}
	}

}

