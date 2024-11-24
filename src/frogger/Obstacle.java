package frogger;

import java.util.Random;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import resourceLoader.ImageLoader;
import resourceLoader.SoundLoader;

public class Obstacle {
    private float x, y; 
    private final float width, height;
    private final float speed;
   
    private ImageLoader imageLoader; // Carregar a imagem
    private SoundLoader cars;

    public Obstacle(float x, float y, float height, float minWidth, float maxWidth, float speed, ImageLoader imageLoader){
        this.y = y; 
        this.x = x;
        this.height = height;
        
        Random random = new Random();
        this.width = minWidth + random.nextFloat() * (maxWidth - minWidth);
        this.speed = speed;
        this.imageLoader = imageLoader; // Receber o ImageLoader
//        this.cars = cars;
    }
    
    public void draw(GL2 gl){
       // Configuração inicial para desenhar um quadrado com textura
    gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f); // Define a cor para branco (sem modulação)
    float size = 0.09f; // Define o tamanho do quadrado

    
    
//    SoundLoader cars = new SoundLoader();
//    cars.playSound(cars.getSongCar()); 

    ImageLoader imageLoaderCar = new ImageLoader(gl);

   
    Texture texture = imageLoaderCar.getCarTexture(); // Substitua por sua textura

   
    texture.bind(gl);
    texture.enable(gl);

   
    gl.glEnable(GL2.GL_BLEND);
    gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

    
    gl.glBegin(GL2.GL_QUADS);

    // Define as coordenadas de textura e os vértices do quadrado
    gl.glTexCoord2f(0, 1); gl.glVertex2f(x - size, y - size); // Inferior esquerdo
    gl.glTexCoord2f(1, 1); gl.glVertex2f(x + size, y - size); // Inferior direito
    gl.glTexCoord2f(1, 0); gl.glVertex2f(x + size, y + size); // Superior direito
    gl.glTexCoord2f(0, 0); gl.glVertex2f(x - size, y + size); // Superior esquerdo

    gl.glEnd(); 
    
    texture.disable(gl);


  
    }
    
    public void update(){
        x += speed;
    }
    
    public boolean isOffScreen(float screenWidth){
        return x > screenWidth || x + width < -screenWidth;
    }
    
    public float getY(){
        return this.y;
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getWidth(){
        return this.width;
    }
    
    public float getHeight(){
        return this.height;
    }
}
