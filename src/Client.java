import java.io.FileNotFoundException;

public class Client {

	public static void main(String[] args) throws FileNotFoundException {
		PhotoImplementation p = new PhotoImplementation("ECHelmet.txt");
		p.load();
	}

}
