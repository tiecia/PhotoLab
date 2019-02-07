import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhotoImplementation extends PhotoFile{
	
	private Color[][] pixels;

	public PhotoImplementation(String fileName) throws FileNotFoundException {
		super(fileName);
		pixels = load();
	}

	public Color[][] load() throws FileNotFoundException {
		File f = new File(getFileName());
		Scanner s = new Scanner(f);
		int width = s.nextInt();
		int height = s.nextInt();
		
		Color[][] displayArray = new Color[width][height];
		for(int i = 0; i<width; i++) {
			for(int j = 0; j < height; j++) {
				int r = s.nextInt();
				int g = s.nextInt();
				int b = s.nextInt();
				displayArray[i][j] = new Color(r,g,b);
			}
		}
		return displayArray;
	}
}
