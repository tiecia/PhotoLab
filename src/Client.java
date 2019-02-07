import java.io.FileNotFoundException;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		// Create an array of PhotoImplementation and do the start() method from the GUI on it.
		PhotoFile[] f = new PhotoImplementation[3];
		f[0] = new PhotoImplementation("ECHelmet.txt");
		f[1] = new PhotoImplementation("ECMorning.txt");
		f[2] = new PhotoImplementation("ECShield.txt");
		PhotoEffect[] effects = new PhotoEffect[2];
		effects[0] = new FlipHorizontally("Flip Horizontally");
		effects[1] = new FlipVertically("Flip Vertically");
		PhotoLab.start(f, effects);
	}

}
