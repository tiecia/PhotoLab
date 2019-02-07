import java.awt.Color;

public class FlipHorizontally implements PhotoEffect{
	
	private String name;
	
	public FlipHorizontally(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Color[][] apply(Color[][] photo) {
		for(int c = 0; c<photo.length/2; c++) {
			for(int r = 0; r<photo[c].length; r++) {
				Color cache;
				cache = photo[r][c];
				System.out.println("r = " + r);
				System.out.println("c = " + c);
				System.out.println();
				photo[r][c] = photo[r][photo[r].length - c - 1]; 
				photo[r][photo[r].length - c - 1] = cache;
			}
		}
		return photo;
	}
}
