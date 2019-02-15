import java.io.FileNotFoundException;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		PhotoFile[] f = new PhotoImplementation[3];
		f[0] = new PhotoImplementation("ECHelmet.txt");
		f[1] = new PhotoImplementation("ECMorning.txt");
		f[2] = new PhotoImplementation("ECShield.txt");
		PhotoEffect[] effects = new PhotoEffect[3];
		effects[0] = new FlipHorizontally("Flip Horizontally");
		effects[1] = new FlipVertically("Flip Vertically");
		effects[2] = new ConvertGreyscale("Covert Greyscale");
		PhotoLab.start(f, effects);
	}

}
