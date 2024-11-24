package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import resourceLoader.ImageLoader;

public class WinScreen implements GLEventListener {

    private ImageLoader imageLoader;

    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();
        imageLoader = new ImageLoader(gl);
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();
       
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(1.0f, 1.0f, 1.0f); 

        drawVictoryImage(gl);

        gl.glFlush();
    }

    private void drawVictoryImage(GL2 gl) {
        float size = 1.0f; 
        imageLoader.getGameWinTexture().bind(gl);
        imageLoader.getGameWinTexture().enable(gl);

        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 1); gl.glVertex2f(-size, -size);
        gl.glTexCoord2f(1, 1); gl.glVertex2f(size, -size); 
        gl.glTexCoord2f(1, 0); gl.glVertex2f(size, size);  
        gl.glTexCoord2f(0, 0); gl.glVertex2f(-size, size); 
        gl.glEnd();

        imageLoader.getGameWinTexture().disable(gl);
    }

    @Override
    public void reshape(GLAutoDrawable glad, int x, int y, int width, int height) {
        GL2 gl = glad.getGL().getGL2();

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
        if (imageLoader != null) {
            imageLoader.cleanup(glad.getGL().getGL2());
        }
    }
}