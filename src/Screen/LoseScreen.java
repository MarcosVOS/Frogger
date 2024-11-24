package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.util.awt.TextRenderer;
import java.awt.Font;
import resourceLoader.ImageLoader;

public class LoseScreen implements GLEventListener {

    private ImageLoader imageLoader;
    private TextRenderer textRenderer;

    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();
        imageLoader = new ImageLoader(gl);

        textRenderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 24));
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

        gl.glColor3f(1.0f, 1.0f, 1.0f); 

        drawGameOverImage(gl);

        renderText(glad);

        gl.glFlush();
    }

    private void drawGameOverImage(GL2 gl) {
        float size = 1.0f; 
        imageLoader.getGameOverTexture().bind(gl);
        imageLoader.getGameOverTexture().enable(gl);

        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 1); gl.glVertex2f(-size, -size); 
        gl.glTexCoord2f(1, 1); gl.glVertex2f(size, -size);  
        gl.glTexCoord2f(1, 0); gl.glVertex2f(size, size);   
        gl.glTexCoord2f(0, 0); gl.glVertex2f(-size, size); 
        gl.glEnd();

        imageLoader.getGameOverTexture().disable(gl);
    }

    private void renderText(GLAutoDrawable glad) {
        int width = glad.getSurfaceWidth();
        int height = glad.getSurfaceHeight();

        textRenderer.beginRendering(width, height);
        textRenderer.setColor(1.0f, 1.0f, 1.0f, 1.0f); 

        int textX = width / 2 - 100;
        int textY = height / 3;

        textRenderer.draw("Press X to Exit", textX, textY + 30);  
        textRenderer.draw("Press R to Play Again", textX, textY - 30);

        textRenderer.endRendering();
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
