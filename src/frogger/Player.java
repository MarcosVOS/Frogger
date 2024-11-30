package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import resourceLoader.ImageLoader;
import resourceLoader.SoundLoader;


public class Player {
    private float x, y;
    private final float stepY; 
    private final float stepX; 
    private float rotationAngle;
    private SoundLoader sounds;
    
    public Player(float x, float y) {
        
        this.sounds = new SoundLoader();
        
        this.x = x;
        this.y = y;

        int numRows = 13; 
        int numCols = 5; 
        this.stepY = 2.0f / numRows; 
        this.stepX = 1.0f / numCols;
    }

     public void moveLeft() {
        this.x -= stepX;
        float size = 0.09f;
        if (this.x - size < -1.0f) {
            this.x = -1.0f + size;
        }
        this.rotationAngle = 90.0f;  
        sounds.playSound(sounds.getHopFrogger());
    }

    public void moveRight() {
        this.x += stepX;
        float size = 0.09f;
        if (this.x + size > 1.0f) {
            this.x = 1.0f - size;
        }
        this.rotationAngle = 270.0f;
        sounds.playSound(sounds.getHopFrogger());
    }

    public void moveUp() {
        this.y += stepY;
        float size = 0.09f;
        if (this.y + size > 1.0f) {
            this.y = 1.0f - size;
        }

        this.rotationAngle = 0.0f; 
        sounds.playSound(sounds.getHopFrogger());
    }

    public void moveDown() {
        this.y -= stepY;
        float size = 0.09f;
        if (this.y - size < -1.0f) {
            this.y = -1.0f + size;
        }
        this.rotationAngle = 180.0f;  
        sounds.playSound(sounds.getHopFrogger());
    }

    public void draw(GL2 gl) {
        gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        float size = 0.09f;

        ImageLoader imageLoader = new ImageLoader(gl);
        Texture texture = imageLoader.getPlayerFroggerTexture();

        texture.bind(gl);
        texture.enable(gl);

        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        gl.glPushMatrix(); 

        gl.glTranslatef(x, y, 0.0f);
        gl.glRotatef(rotationAngle, 0.0f, 0.0f, 1.0f);
        gl.glTranslatef(-x, -y, 0.0f);

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

        gl.glPopMatrix(); 
        texture.disable(gl);
    }

            
   public float getX(){
       return this.x;
   }
   
   public float getY(){
       return this.y;
   }
   
    public float setX(float x){
       this.x = x; 
       return this.x;
   }
   
   public float setY(float y){
       this.y = y; 
       return this.y;
   }
}
