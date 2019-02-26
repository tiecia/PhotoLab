import java.io.File;
import java.io.FileNotFoundException;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		File[] dirFiles = new File("photos/").listFiles();
		PhotoFile[] f = new PhotoImplementation[dirFiles.length];
		for(int i = 0; i <dirFiles.length; i++) {
			f[i] = new PhotoImplementation(dirFiles[i].getPath());
		}
		
		PhotoEffect[] effects = new PhotoEffect[7];
		effects[0] = new FlipHorizontally("Flip Horizontally");
		effects[1] = new FlipVertically("Flip Vertically");
		effects[2] = new ConvertGreyscale("Covert Greyscale");
		effects[3] = new Shrink("Shrink");
		effects[4] = new Reflect("Reflect");
		effects[5] = new InvertColors("Invert Colors");
		effects[6] = new ChangeHue("Change Hue");
		PhotoLab.start(f, effects);
	}

}
