import java.awt.Color;

public class Reflect implements PhotoEffect{
	
	String name;
	
	public Reflect(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Color[][] apply(Color[][] photo){
		for(int c = 0; c<photo.length-1; c++) {
			for(int r = 0; r<photo[0].length/2; r++) {
				photo[photo.length - c - 1][photo[0].length - r - 1] = photo[c][r];
			}
		}
		
		for(int r = 0; r<photo.length/2; r++) {
			for(int c = photo[r].length/2; c<photo[r].length;c++) {
				Color cache;
				
				cache = photo[r][c];
				photo[r][c] = photo[photo.length - r - 1][c];
				photo[photo.length - r - 1][c] = cache;
//				System.out.println("r: " + r);
//				System.out.println("c: " + c);
//				System.out.println();
			}
		}
		return photo;
	}

}