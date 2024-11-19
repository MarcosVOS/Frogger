package frogger;

import com.jogamp.opengl.GL2;

public class Player {
    private float x, y;
    private final float step = 0.1f;
    
    
    public Player(float x, float y){
        this.x = x; 
        this.y = y;
    }
    
    public void moveUp(){
        this.y += step;
    }
    
    public void moveOown(){
        this.y -= step;
    }
    
    public void moveLeft(){
        this.x -= step;
    }
    
    public void moveRight(){
        this.x += step;
    }
    
   public void draw(GL2 gl){
       gl.glColor3f(1.0f, 1.0f, 0.0f);
       float size = 0.05f;
       
       gl.glBegin(GL2.GL_QUADS);
       gl.glVertex2f(x - size, y - size);
       gl.glVertex2f(x + size, y - size);
       gl.glVertex2f(x + size, y + size);
       gl.glVertex2f(x - size, y + size);
       gl.glEnd();
   }
            
   public float getX(){
       return this.x;
   }
   
   public float getY(){
       return this.y;
   }
}
