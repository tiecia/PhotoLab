import java.awt.Color;

public class FlipVertically implements PhotoEffect{
	
	private String name;
	
	public FlipVertically(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Color[][] apply(Color[][] photo) {
		for(int c = 0; c<photo.length/2; c++) {
			for(int r = 0; r<photo[c].length; r++) {
				Color cache;
				System.out.println("r = " + r);
				System.out.println("c = " + c);
				System.out.println();
				
				cache = photo[c][r];
				photo[c][r] = photo[photo.length - c - 1][photo[c].length - r - 1];
				photo[photo.length - c - 1][photo[c].length - r - 1] = cache;
			}
		}
		return photo;
	}
}
