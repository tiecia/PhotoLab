import java.awt.Color;

public class ConvertGreyscale implements PhotoEffect{
	
	String name;
	
	public ConvertGreyscale(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public Color[][] apply(Color[][] photo){
		for(int r = 0; r<photo.length; r++) {
			for(int c = 0; c<photo[r].length; c++) {
				int grey = (photo[r][c].getRed() + photo[r][c].getGreen() + photo[r][c].getBlue())/3;
				photo[r][c] = new Color(grey,grey,grey);
			}
		}
		return photo;
	}
}
