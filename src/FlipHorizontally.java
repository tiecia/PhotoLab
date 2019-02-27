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
//				Save the first pixel to the cache
				cache = photo[r][c];
//				Set the first to the last
				photo[r][c] = photo[r][photo[r].length - c - 1];
//				Set the last to the first
				photo[r][photo[r].length - c - 1] = cache;
			}
		}

		return photo;
	}
}
