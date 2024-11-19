package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import resourceLoader.ImageLoader;

public class Player {
    private float x, y;
    private final float step = 0.2f;
    
    
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
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        float size = 0.09f;
       
        ImageLoader imageLoader = new ImageLoader(gl);
        Texture texture = imageLoader.getPlayerFroggerTexture();
        
        texture.bind(gl);
        texture.enable(gl);
        
        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
        
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 1); 
        gl.glVertex2f(x - size, y - size);
        gl.glTexCoord2f(1, 1);  
        gl.glVertex2f(x + size, y - size); 
        gl.glTexCoord2f(1, 0); 
        gl.glVertex2f(x + size, y + size);
        gl.glTexCoord2f(0, 0); 
        gl.glVertex2f(x - size, y + size);
        gl.glEnd();

        texture.disable(gl);
   }
            
   public float getX(){
       return this.x;
   }
   
   public float getY(){
       return this.y;
   }
}
