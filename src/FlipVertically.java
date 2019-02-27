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
		for(int r = 0; r<photo.length/2; r++) {
				Color[] cache;
//				Set the cache to first row
				cache = photo[r];
//				Set the first row to the last row
				photo[r] = photo[photo.length - r - 1];
//				Set the last row to the cache
				photo[photo.length - r - 1] = cache;
		}
		return photo;
	}
}
