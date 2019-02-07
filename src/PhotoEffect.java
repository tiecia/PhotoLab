import java.awt.Color;

// Represents an effect that can alter a photo
public interface PhotoEffect {
    
    // Returns the name of the effect for display to the user
    String getName();
    
    // Applies the effect to a given photo, returning the altered photo
    Color[][] apply(Color[][] photo);

}