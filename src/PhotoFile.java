import java.awt.*;
import java.io.FileNotFoundException;

// Represents a file containing a photo; abstract since load must be implemented
public abstract class PhotoFile {
    
    private String fileName;
    
    // Constructor; takes the file name of the file containing the photo
    public PhotoFile(String fileName) {
        this.fileName = fileName;
    }
    
    // Getter; returns the file name of the file containing the photo
    public String getFileName() {
        return fileName;
    }
    
    // Loads the photo from the file 
    abstract public Color[][] load() throws FileNotFoundException;
    
}