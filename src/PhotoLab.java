import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * PhotoLab
 * 
 * AP Computer Science, Eastside Catholic High School
 * Adapted from: AP Computer Science at Hazen High School, Renton, WA
 * 
 * Based on AP Labs' pixLab by Barb Ericson ericson@cc.gatech.edu
 */

// Represents a GUI that allows users to view and manipulate photos
public class PhotoLab {

    // Represents the main window of the PhotoLab GUI
    private static class PhotoLabFrame extends JFrame implements ActionListener {

        PhotoEffect[] effects;
        ArrayList<PhotoFile> files = new ArrayList<PhotoFile>();
        PhotoLabImagePanel imagePanel;
        
        
        JMenu fileMenu = new JMenu("File");
        JMenu effectMenu = new JMenu("Effect");;
        JMenuBar menuBar = new JMenuBar();
        JMenu openMenu = new JMenu("Open");

        // Constructor; builds the main window, adding menu and image panel
        public PhotoLabFrame(PhotoFile[] files, PhotoEffect[] effects) {
            setTitle("PhotoLab");
            addMenuBar(files, effects);
            addImagePanel(files);
            pack(); // Resizes main window to fit its content
            setLocationByPlatform(true); // Use system window location
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
        }

        // Adds a menu bar containing the provided files and effects
        private void addMenuBar(PhotoFile[] newFiles, PhotoEffect[] effects) {
            this.effects = effects;

            // Create a File menu with an entry for each PhotoFile
            if (newFiles != null) {
                for (PhotoFile file : newFiles) {
                    JMenuItem item = new JMenuItem(file.getFileName());
                    item.addActionListener(this);
                    fileMenu.add(item);
                }
                menuBar.add(fileMenu);
            }
            
            for(int i = 0; i<newFiles.length; i++) {
            	files.add(i, newFiles[i]);
            }

            // Create an Effects menu with an entry for each PhotoEffect
            if (effects != null) {
                for (PhotoEffect effect : effects) {
                    JMenuItem item = new JMenuItem(effect.getName());
                    item.addActionListener(this);
                    effectMenu.add(item);
                }
                menuBar.add(effectMenu);
            }
            
            JMenuItem openFile = new JMenuItem("Open File");
            openFile.addActionListener(this);
            openMenu.add(openFile);
            menuBar.add(openMenu);

            // Add our menu bar to the main window
            setJMenuBar(menuBar);
        }
        
        private void addPhotoToBar(PhotoFile newPhoto) {
        	JMenuItem item = new JMenuItem(newPhoto.getFileName());
            item.addActionListener(this);
            fileMenu.add(item);
            menuBar.add(fileMenu);
        }

        // Adds an image panel to window, loading the first PhotoFile (if any)
        private void addImagePanel(PhotoFile[] files) {
            imagePanel = new PhotoLabImagePanel();
            add(imagePanel);
            if (files != null) {
                loadPhotoFromFile(files[0]);
            }
        }

        // Handles menu selections by the user
        public void actionPerformed(ActionEvent e) {

            // If the event matches a File menu item, load the file
            if (files != null) {
                for (PhotoFile file : files) {
                    if (e.getActionCommand().equals(file.getFileName())) {
                        loadPhotoFromFile(file);
                    }
                }
            }

            // If the event matches a Effects menu item, apply the effect
            if (effects != null) {
                for (PhotoEffect effect : effects) {
                    if (e.getActionCommand().equals(effect.getName())) {
                        updateImagePanel(effect.apply(imagePanel.getPhoto()));
                    }
                }
            }
            
            if(e.getActionCommand().equals("Open File")) {
            	JFileChooser open = new JFileChooser();
            	FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".jpg", "jpg");
            	FileNameExtensionFilter jpegFilter = new FileNameExtensionFilter(".jpeg", "jpeg");
            	FileNameExtensionFilter pngFilter = new FileNameExtensionFilter(".png", "png");
            	
               	open.setAcceptAllFileFilterUsed(true);
            	open.setFileFilter(jpgFilter);
            	open.setFileFilter(jpegFilter);
            	open.setFileFilter(pngFilter);
            	open.showOpenDialog(imagePanel);
            	
            	boolean notOpen = true;
            	
            	for(int i = 0; i < files.size() && notOpen; i++) {
            		if(files.get(i).getFileName() == open.getSelectedFile().getName()) {
            			JOptionPane.showMessageDialog(imagePanel, "Image Already Open", "Error", JOptionPane.ERROR_MESSAGE);
            			notOpen = false;
            		}
            	}
            	
            	if(open.getSelectedFile() != null && notOpen) {
	            	File openedImage = open.getSelectedFile();
	            	BufferedImage image = null;
	            	try {
	            		image = ImageIO.read(openedImage);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	            	Color[][] picture = new Color[image.getHeight()][image.getWidth()];
	            	
	            	String outputFileName = "photos/" + openedImage.getName() + ".txt";
	            	
	            	System.out.println(outputFileName);
	            	
	            	File out = new File(outputFileName);
	            	PrintStream print = null;
	            	try {
						print = new PrintStream(out);
					} catch (FileNotFoundException e1) {
						System.out.println("Output File Not Found");
						e1.printStackTrace();
					}
	                
	            	print.println(image.getHeight() + " " + image.getWidth());
	            	for(int r = 0; r<picture.length; r++) {
	            		for(int c = 0; c<picture[r].length; c++) {
	//            			System.out.println("r: " + r);
	//            			System.out.println("c: " + c);
	//            			System.out.println("Height: " + image.getHeight());
	//            			System.out.println("Width: " + image.getWidth());
	                        int color = image.getRGB(c, r);
	                        int red = (color & 0x00ff0000) >> 16;
	                        int green = (color & 0x0000ff00) >> 8;
	                        int blue = color & 0x000000ff;
	                        print.println(red + " " + green + " " + blue);
	                        picture[r][c] = new Color(red, green, blue);
	//            			System.out.println(red + " " + green + " " + blue);
	//            			System.out.println();
	            		}
	            	}
	            	
	            	PhotoImplementation newPhoto = null;
					try {
						newPhoto = new PhotoImplementation(outputFileName, picture);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
	            	addPhotoToBar(newPhoto);
	            	files.add(newPhoto);
	            	loadPhotoFromFile(newPhoto);
            	}
            	
            	System.out.println("Done...");
            	
            }
        }

        // Loads an image from a file into the image panel, handling errors
        public void loadPhotoFromFile(PhotoFile file) {
            Color[][] photo;
            try {
                photo = file.load();
                updateImagePanel(photo);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        // Updates the image panel, then does a resize and repaint of window
        private void updateImagePanel(Color[][] photo) {
            imagePanel.setPhoto(photo);
            pack();
            repaint();
        }

    }

    // Represents a window panel that can display a Photo
    private static class PhotoLabImagePanel extends JPanel {

        // Color to show if no photo provided (cyan)
        private static final int RGB_NO_PHOTO = 0x00FFFF;

        // Color to show if a pixel doesn't have a Color (magenta)
        private static final int RGB_NO_PIXEL = 0xFF00FF;

        private Color[][] photo;
        private Image image;
        private Dimension size;

        // Constructor; initializes panel with a fake 1x1 image
        public PhotoLabImagePanel() {
            Color[][] photo = new Color[1][1];
            photo[0][0] = new Color(RGB_NO_PHOTO);
            setPhoto(photo);
        }

        // Sets the photo to display, converting to Java image format
        public void setPhoto(Color[][] photo) {
            this.photo = photo;
            image = createImageFromPhoto(photo);
            size = new Dimension(photo[0].length, photo.length);
            setPreferredSize(size); // We want to be the size of the image
            invalidate(); // Request that we be redrawn
        }

        // Gets the photo that is currently being displayed
        public Color[][] getPhoto() {
            return photo;
        }

        // Paints the image onto the panel when we are shown on window
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }

        // Converts our Photo array to a Java BufferedImage
        private static BufferedImage createImageFromPhoto(Color[][] photo) {
            BufferedImage image = new BufferedImage(photo[0].length,
                    photo.length, BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < photo.length; y++) {
                for (int x = 0; x < photo[y].length; x++) {
                    if (photo[y][x] == null) {
                        image.setRGB(x, y, RGB_NO_PIXEL);
                    } else {
                        image.setRGB(x, y, photo[y][x].getRGB());
                    }
                }
            }

            return image;
        }

    }

    // Starts the PhotoLab with no files or effects
    public static void start() {
        new PhotoLabFrame(null, null);
    }

    // Starts the PhotoLab with an array of files to display
    public static void start(PhotoFile[] files) {
        new PhotoLabFrame(files, null);
    }

    // Starts the PhotoLab with arrays of files to display and effects to apply
    public static void start(PhotoFile[] files, PhotoEffect[] effects) {
        new PhotoLabFrame(files, effects);
    }

}
