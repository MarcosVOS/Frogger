package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Platform {
    private float x, y, width, speed;
    private Texture texture;

    public Platform(float x, float y, float width, float speed, Texture texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.speed = speed;
        this.texture = texture;
    }

     public void update() {
        x += speed;

        if (x <= -1.0f || x + width >= 1.0f) {
            speed = -speed;
            x = Math.max(-1.0f, Math.min(x, 1.0f - width));
        }
    }

    public void draw(GL2 gl) {
        if (texture != null) {
            texture.bind(gl);
            texture.enable(gl);
        }

        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(x, y); 
        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(x + width, y); 
        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(x + width, y + 0.1f); 
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(x, y + 0.1f);
        gl.glEnd();

        if (texture != null) {
            texture.disable(gl);
        }
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
