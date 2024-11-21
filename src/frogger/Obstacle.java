package frogger;

import java.util.Random;
import com.jogamp.opengl.GL2;

public class Obstacle {
    private float x, y; 
    private float widht, height;
    private float speed;
    
    public Obstacle(float x, float y, float height, float minWidth, float maxWidth, float speed){
        this.y = y; 
        this.x = x;
        this.height = height;
        
        Random random = new Random();
        this.widht = minWidth + random.nextFloat() * (maxWidth - minWidth);
        this.speed = speed;
    }
    
    public void draw(GL2 gl){
        gl.glColor3f(0.8f, 0.0f, x);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(x, y);
        gl.glVertex2f(x + widht, y);
        gl.glVertex2f(x + widht, y + height);
        gl.glVertex2f(x, y + height);
        gl.glEnd(); 
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
    
}
