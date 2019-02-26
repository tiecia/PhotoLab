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
		
		return null;
	}
	
//	private class Sliders{
//		public static JFrame frame2 = new JFrame();
//		
//		private static int red = 214;
//		private static int green = 227;   //214, 227, 223
//		private static int blue = 223;
//		public static String color = null;
//		
//		/**
//		 * @wbp.parser.entryPoint
//		 */
//		public static void colorChange() {
//		
//		frame2.setBounds(100, 100, 344, 269);
//		frame2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//		frame2.getContentPane().setLayout(null);
//		
//
//		
//		JPanel colorPanel = new JPanel();
//		colorPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//		colorPanel.setBounds(31, 174, 260, 45);
//		frame2.getContentPane().add(colorPanel);
//		
//		JSlider redSlider = new JSlider();
//		redSlider.setMaximum(255);
//		redSlider.setValue(214);
//		redSlider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				red = redSlider.getValue();
//				colorPanel.setBackground(new Color(red, green, blue));
//				frmFractionCalculator.getContentPane().setBackground(new Color(red, green, blue));
//				panel_1.setBackground(new Color(red, green, blue));
//				panel_2.setBackground(new Color(red, green, blue));
//				panel_3.setBackground(new Color(red, green, blue));
//				panel.setBackground(new Color(red, green, blue));
//				panel_5.setBackground(new Color(red, green, blue));
//			}
//		});
//		redSlider.setBounds(41, 29, 200, 26);
//		frame2.getContentPane().add(redSlider);
//		
//		JSlider greenSlider = new JSlider();
//		greenSlider.setMaximum(255);
//		greenSlider.setValue(227);
//		greenSlider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				green = greenSlider.getValue();
//				colorPanel.setBackground(new Color(red, green, blue));
//				frmFractionCalculator.getContentPane().setBackground(new Color(red, green, blue));
//				panel_1.setBackground(new Color(red, green, blue));
//				panel_2.setBackground(new Color(red, green, blue));
//				panel_3.setBackground(new Color(red, green, blue));
//				panel.setBackground(new Color(red, green, blue));
//				panel_5.setBackground(new Color(red, green, blue));
//			}
//		});
//		greenSlider.setBounds(41, 81, 200, 26);
//		frame2.getContentPane().add(greenSlider);
//		
//		JSlider blueSlider = new JSlider();
//		blueSlider.setMaximum(255);
//		blueSlider.setValue(223);
//		blueSlider.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//				blue = blueSlider.getValue();
//				colorPanel.setBackground(new Color(red, green, blue));
//				frmFractionCalculator.getContentPane().setBackground(new Color(red, green, blue));
//				panel_1.setBackground(new Color(red, green, blue));
//				panel_2.setBackground(new Color(red, green, blue));
//				panel_3.setBackground(new Color(red, green, blue));
//				panel.setBackground(new Color(red, green, blue));
//				panel_5.setBackground(new Color(red, green, blue));
//			}
//		});
//		blueSlider.setBounds(41, 137, 200, 26);
//		frame2.getContentPane().add(blueSlider);
//		
//		JLabel lblRed = new JLabel("Red");
//		lblRed.setBounds(280, 29, 46, 14);
//		frame2.getContentPane().add(lblRed);
//		
//		JLabel lblGreen = new JLabel("Green");
//		lblGreen.setBounds(280, 81, 46, 14);
//		frame2.getContentPane().add(lblGreen);
//		
//		JLabel lblBlue = new JLabel("Blue");
//		lblBlue.setBounds(280, 137, 46, 14);
//		frame2.getContentPane().add(lblBlue);
//	}
//	}

}
