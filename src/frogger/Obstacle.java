package frogger;

import java.util.Random;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Obstacle {
    private float x, y; 
    private final float widht, height;
    private final float speed;
    private Texture carTexture;

    
    public Obstacle(float x, float y, float height, float minWidth, float maxWidth, float speed, Texture carTexture) {
        this.y = y;
        this.x = x;
        this.height = height;
        this.carTexture = carTexture;

        Random random = new Random();
        this.widht = minWidth + random.nextFloat() * (maxWidth - minWidth);
        this.speed = speed;
    }
    
    public void draw(GL2 gl){
        if (carTexture != null) {
            gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            carTexture.bind(gl);
            carTexture.enable(gl);
            
            gl.glEnable(GL2.GL_BLEND);
            gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0, 0); gl.glVertex2f(x, y);
            gl.glTexCoord2f(1, 0); gl.glVertex2f(x + widht, y);
            gl.glTexCoord2f(1, 1); gl.glVertex2f(x + widht, y + height);
            gl.glTexCoord2f(0, 1); gl.glVertex2f(x, y + height);
            gl.glEnd();

            carTexture.disable(gl);
        }
    }
    
    public void update(){
        x += speed;
    }
    
    public boolean isOffScreen(float screenWidth){
        return x > screenWidth || x + widht < -screenWidth;
    }
    
    public float getY(){
        return this.y;
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getWidth(){
        return this.widht;
    }
    
    public float getHeight(){
        return this.height;
    }
}
