package resourceLoader;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private Texture playerFroggerTexture;
    private Texture gameOver; 
    private Texture gameWin;
    private Texture gameHome;
    private Texture car;
    private Texture truck;
    private Texture stem;

    public ImageLoader(GL2 gl) {
        try {
            playerFroggerTexture = loadTexture(gl, "sapo.bmp");
            gameOver = loadTexture(gl, "GameOver.bmp");
            gameWin = loadTexture(gl, "GameWin.bmp");
            gameHome = loadTexture(gl, "HomeScreen.bmp");
            car = loadTexture(gl, "car.bmp");
            truck = loadTexture(gl, "truck.bmp");
            stem = loadTexture(gl, "stem.bmp");
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
        
        if (gameOver != null) {
            gameOver.bind(gl); 
            gameOver.enable(gl); 
        }

        if (gameWin != null) {
            gameWin.bind(gl); 
            gameWin.enable(gl); 
        }
        
        if (gameHome != null) {
            gameHome.bind(gl); 
            gameHome.enable(gl); 
        }   

        if (car != null) {
            car.bind(gl); 
            car.enable(gl); 
        }
        
        if (truck != null) {
            truck.bind(gl); 
            truck.enable(gl); 
        }
        
        if (truck != null) {
            truck.bind(gl); 
            truck.enable(gl); 
        }
    }

 
    public void cleanup(GL2 gl) {
        if (playerFroggerTexture != null) {
            playerFroggerTexture.disable(gl);
            playerFroggerTexture.destroy(gl); 
        }
        
        if (gameOver != null) {
            gameOver.disable(gl);
            gameOver.destroy(gl); 
        }

        if (gameWin != null) {
            gameWin.disable(gl);
            gameWin.destroy(gl); 
        }
        
        if (gameHome != null) {
            gameHome.disable(gl);
            gameHome.destroy(gl); 
        }


        if (car != null) {
            car.disable(gl);
            car.destroy(gl); 
        }
        
        if (truck != null) {
            truck.disable(gl);
            truck.destroy(gl); 
        }
        
        if (stem != null) {
            stem.disable(gl);
            stem.destroy(gl); 
        }
    }

    public Texture getPlayerFroggerTexture() {
        return playerFroggerTexture;
    }
    
     public Texture getGameOverTexture(){
        return gameOver;
    }

    public Texture getGameWinTexture(){
        return gameWin;
    }
    
    public Texture getGameHomeTexture(){
        return gameHome;
    }

    public Texture getCarTexture(){
        return car;
    }
    
    public Texture getTruckTexture(){
        return truck;
    }
    
    public Texture getStemTexture(){
        return stem;
    }
}
