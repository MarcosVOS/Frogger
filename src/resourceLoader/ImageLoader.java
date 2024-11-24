package resourceLoader;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

import java.io.InputStream;
import java.io.IOException;

public class ImageLoader {

    private Texture playerFroggerTexture;
    private Texture sapoVictory;
    private Texture gameOver;
    private Texture carTexture;
    
    

    public ImageLoader(GL2 gl) {
        try {
            playerFroggerTexture = loadTexture(gl, "sapo.bmp");
            
            gameOver = loadTexture(gl, "GameOver.bmp");
            
            sapoVictory = loadTexture(gl, "Vitoria.bmp");
            
            carTexture = loadTexture(gl, "Carro_Rosa.png");
            
            
            
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    private Texture loadTexture(GL2 gl, String textureName) throws IOException {
        // Usando o caminho correto para os recursos no classpath
        String resourcePath = "/resources/images/" + textureName;
//        System.out.println("Tentando carregar o recurso: " + resourcePath);  //---- PRECISO ARRUMAR ISSO AQUI
        
        // Verificando se o recurso existe antes de tentar carregar
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Recurso não encontrado: " + resourcePath);
        }

        // Criação do TextureData a partir do InputStream
        TextureData textureData = TextureIO.newTextureData(gl.getGLProfile(), inputStream, false, "bmp");

        // Criando a textura a partir do TextureData
        Texture texture = TextureIO.newTexture(textureData);

        // Configurações de parâmetros para a textura
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
        
        if (sapoVictory != null) {
        sapoVictory.bind(gl); 
        sapoVictory.enable(gl); 
        }
        
         if (carTexture != null) {
        carTexture.bind(gl); 
        carTexture.enable(gl); 
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
         
         if (sapoVictory != null) {
            sapoVictory.disable(gl);
            sapoVictory.destroy(gl); 
        }
         
          if (carTexture != null) {
            carTexture.disable(gl);
            carTexture.destroy(gl); 
        }
    }

    public Texture getPlayerFroggerTexture() {
        return playerFroggerTexture;
    }
    
    public Texture getGameOverTexture(){
        return gameOver;
    }
    
    public Texture getSapoVictoryTexture(){
        return sapoVictory;
    }
    
    public Texture getCarTexture(){
        return carTexture;
    }
    
    
}
