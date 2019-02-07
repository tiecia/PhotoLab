import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class PhotoImplementation extends PhotoFile{
	
	public static void main(String[] args) throws FileNotFoundException {
//		PhotoImplementation p = new PhotoImplementation("ECHelmet.txt");
		PhotoFile[] files = new PhotoFile[3];
		PhotoImplementation p = new PhotoImplementation("ECHelmet.txt");
		Color[][] displayArray = p.load();
		PhotoLab.start(files);
	}

	public PhotoImplementation(String fileName) {
		super(fileName);
	}

	public Color[][] load() throws FileNotFoundException {
		File out = new File("Out.txt");
		PrintStream print = new PrintStream(out);
		File f = new File(getFileName());
		Scanner s = new Scanner(f);
		int width = s.nextInt();
		int height = s.nextInt();
		System.out.println("Width: " + width + " Height: " + height);
		print.println("Width: " + width + " Height: " + height);
		Color[][] displayArray = new Color[width][height];
		for(int i = 0; i<width; i++) {
			for(int j = 0; j < height; j++) {
				int r = s.nextInt();
				int g = s.nextInt();
				int b = s.nextInt();
				//System.out.print(String.format("%03d", r) +""+ String.format("%03d", g)+""+ String.format("%03d", b) + " ");
				//print.print(String.format("%03d", r) +""+ String.format("%03d", g)+""+ String.format("%03d", b) + " ");
				displayArray[i][j] = new Color(r,g,b);
			}
			System.out.println();
			print.println();
		}
		return displayArray;
	}
}
