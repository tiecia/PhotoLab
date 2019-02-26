import java.awt.Color;

public class InvertColors implements PhotoEffect {

	private String name;
	
	public InvertColors(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	
	public Color[][] apply(Color[][] photo) {
		for(int r = 0; r<photo.length; r++) {
			for(int c = 0; c<photo[0].length; c++) {
				int red = photo[r][c].getRed();
				int green = photo[r][c].getGreen();
				int blue = photo[r][c].getBlue();
				photo[r][c] = new Color(255-red, 255-green, 255-blue);
			}
		}
		return photo;
	}

}
