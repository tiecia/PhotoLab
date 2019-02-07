import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PhotoImplementation extends PhotoFile{
	
	public PhotoImplementation(String fileName) {
		super(fileName);
	}

	public Color[][] load() throws FileNotFoundException {
		File f = new File(getFileName());
		Scanner s = new Scanner(f);
		int width = s.nextInt();
		int height = s.nextInt();
		Color[][] pictureArray = new Color[width][height];
		for(int i = 0; i<width; i++) {
			for(int j = 0; j < height; j++) {
				int r = s.nextInt();
				int g = s.nextInt();
				int b = s.nextInt();
				pictureArray[i][j] = new Color(r,g,b);
			}
		}
		
		return null;
	}
}
