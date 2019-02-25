import java.awt.Color;

public class Shrink implements PhotoEffect{
	
	public String name;
	
	public Shrink(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Color[][] apply(Color[][] photo){
		int height = (photo.length/2)-1;
		int width = (photo[0].length/2)-1;

		
		Color[][] small = new Color[height][width];
		for(int c = 0; c<photo.length; c+=2) {
			for(int r = 0; r<photo[0].length; r+=2) {
				int red = (photo[c][r].getRed() + photo[c+1][r].getRed() + photo[c][r+1].getRed() + photo[c+1][r+1].getRed())/4;
				int green = (photo[c][r].getGreen() + photo[c+1][r].getGreen() + photo[c][r+1].getGreen() + photo[c+1][r+1].getGreen())/4;
				int blue = (photo[c][r].getBlue() + photo[c+1][r].getBlue() + photo[c][r+1].getBlue() + photo[c+1][r+1].getBlue())/4;
				
				photo[c+1][r] = null;
				photo[c+1][r+1] = null;
				photo[c][r+1] = null;
				
				photo[c][r] = new Color(red,green,blue);
			}
		}
		
		int smallC = 0;
		int smallR = 0;
		for(int c = 0; c<548; c+=2) {
			for(int r = 0; r<548; r++) {
				if(photo[c][r] != null) {
					small[smallC][smallR] = photo[c][r];
					System.out.println("SmallR: " + smallR);
					smallR++;
				}
			}
			smallR = 0;
			System.out.println("SmallC: " + smallC);
			smallC++;
		}
		return small;
	}
}
