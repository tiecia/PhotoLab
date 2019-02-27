import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColorSliders extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JPanel panel;
	private JSlider sliderRed;
	private JSlider sliderGreen;
	private JSlider sliderBlue;
	
	private boolean canceled = false;
	
	public boolean isCanceled() {
		return canceled;
	}
	
	public Color getHue() {
		return new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue());
	}

	
	public ColorSliders() {
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[center][grow,center]", "[grow,center][grow][grow][grow]"));
		{
			JLabel lblRed = new JLabel("Red");
			contentPanel.add(lblRed, "cell 0 0");
		}
		{
			sliderRed = new JSlider();
			sliderRed.setMaximum(255);
			sliderRed.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					panel.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
				}
			});
			contentPanel.add(sliderRed, "cell 1 0");
		}
		{
			JLabel lblGreen = new JLabel("Green");
			contentPanel.add(lblGreen, "cell 0 1");
		}
		{
			sliderGreen = new JSlider();
			sliderGreen.setMaximum(255);
			contentPanel.add(sliderGreen, "cell 1 1");
			sliderGreen.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					panel.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
				}
			});
		}
		{
			JLabel lblBlue = new JLabel("Blue");
			contentPanel.add(lblBlue, "cell 0 2");
		}
		{
			sliderBlue = new JSlider();
			sliderBlue.setMaximum(255);
			sliderBlue.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					panel.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
				}
			});
			contentPanel.add(sliderBlue, "cell 1 2");
		}
		{
			panel = new JPanel();
			contentPanel.add(panel, "cell 1 3,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						canceled = false;
					}
				});
				
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		panel.setBackground(new Color(sliderRed.getValue(), sliderGreen.getValue(), sliderBlue.getValue()));
	}

}
