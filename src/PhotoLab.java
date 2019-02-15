import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

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
        PhotoFile[] files;
        PhotoLabImagePanel imagePanel;

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
        private void addMenuBar(PhotoFile[] files, PhotoEffect[] effects) {
            this.effects = effects;
            JMenuBar menuBar = new JMenuBar();

            // Create a File menu with an entry for each PhotoFile
            if (files != null) {
                JMenu fileMenu = new JMenu("File");
                for (PhotoFile file : files) {
                    JMenuItem item = new JMenuItem(file.getFileName());
                    item.addActionListener(this);
                    fileMenu.add(item);
                }
                menuBar.add(fileMenu);
            }
            this.files = files;

            // Create an Effects menu with an entry for each PhotoEffect
            if (effects != null) {
                JMenu effectMenu = new JMenu("Effect");
                for (PhotoEffect effect : effects) {
                    JMenuItem item = new JMenuItem(effect.getName());
                    item.addActionListener(this);
                    effectMenu.add(item);
                }
                menuBar.add(effectMenu);
            }

            // Add our menu bar to the main window
            setJMenuBar(menuBar);
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
