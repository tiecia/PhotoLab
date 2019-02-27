import java.awt.Color;

public class ChangeHue implements PhotoEffect {

	private String name;
	
	public ChangeHue(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public Color[][] apply(Color[][] photo) {
		ColorSliders s = new ColorSliders();
		s.setVisible(true);
		//In Dialog
		Color hue = s.getHue();
		
		int hred = hue.getRed();
		int hgreen = hue.getGreen();
		int hblue = hue.getBlue();
		int large;
		
		for(int r = 0; r<photo.length; r++) {
			for(int c = 0; c<photo[0].length; c++) {
				int red = photo[r][c].getRed();
				int green = photo[r][c].getGreen();
				int blue = photo[r][c].getBlue();
				
				int redPercent = hred/255*100;
				int greenPercent = hgreen/255*100;
				int bluePercent = hblue/255*100;
				
				if(hred > hgreen && hred > hblue) {
//					photo[r][c] = new Color(255, (int)(g/2), (int)(b/2));
					photo[r][c] = new Color(255,green,blue);
//					photo
				} else if(hgreen > hred && hgreen > hblue) {
//					photo[r][c] = new Color((int)(red/2), 255, (int)(b/2));
					photo[r][c] = new Color(red,255,blue);
				} else if(hblue > hred && hblue > hgreen) {
//					photo[r][c] = new Color((int)(red/2), (int)(g/2), 255);
					photo[r][c] = new Color(red,green,255);
				} else {
					large = -1;
				}
			}
		}
		return photo;
	}
}
