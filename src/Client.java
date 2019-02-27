import java.io.File;
import java.io.FileNotFoundException;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		//Searches the directory and pulls pictures
		File[] dirFiles = new File("photos/").listFiles();
		PhotoFile[] f = new PhotoImplementation[dirFiles.length];
		for(int i = 0; i <dirFiles.length; i++) {
			f[i] = new PhotoImplementation(dirFiles[i].getPath());
		}
		
		//Creates an array of the effects
		PhotoEffect[] effects = new PhotoEffect[7];
		effects[0] = new FlipHorizontally("Flip Horizontally");
		effects[1] = new FlipVertically("Flip Vertically");
		effects[2] = new ConvertGreyscale("Covert Greyscale");
		effects[3] = new Shrink("Shrink");
		effects[4] = new Reflect("Reflect");
		effects[5] = new InvertColors("Invert Colors");
		effects[6] = new ChangeHue("Change Hue (Does Not Work)");
		//Starts the PhotoLab class
		PhotoLab.start(f, effects);
	}

}
