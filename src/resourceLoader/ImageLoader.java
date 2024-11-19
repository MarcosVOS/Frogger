package resourceLoader;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

public class ImageLoader {

    private Texture playerFroggerTexture;

    public ImageLoader(GL2 gl) {
        try {
            playerFroggerTexture = loadTexture(gl, "sapo.bmp");
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    private Texture loadTexture(GL2 gl, String textureName) throws IOException {
        File textureFile = new File(getClass().getResource("/resources/images/" + textureName).getFile());
        Texture texture = TextureIO.newTexture(textureFile, false); 
        
        texture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
        texture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
        texture.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
        texture.setTexParameteri(gl, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);

        return texture;
    }

    
    public void display(GL2 gl) {
        if (playerFroggerTexture != null) {
            playerFroggerTexture.bind(gl); 
            playerFroggerTexture.enable(gl); 
        }
    }

 
    public void cleanup(GL2 gl) {
        if (playerFroggerTexture != null) {
            playerFroggerTexture.disable(gl);
            playerFroggerTexture.destroy(gl); 
        }
    }

    public Texture getPlayerFroggerTexture() {
        return playerFroggerTexture;
    }
}
