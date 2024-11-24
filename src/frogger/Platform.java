package frogger;
import com.jogamp.opengl.GL2;
public class Platform {
    private float x, y, width, speed;
    public Platform(float x, float y, float width, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.speed = speed;
    }
    public void update() {
        x += speed;
        if (x > 1.0f || x < -1.0f) {
            speed = -speed;  
        }
    }
    public void draw(GL2 gl) {
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(x, y); 
        gl.glVertex2f(x + width, y); 
        gl.glVertex2f(x + width, y + 0.1f); 
        gl.glVertex2f(x, y + 0.1f);
        gl.glEnd();
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getSpeed() {
        return speed;
    }
}