package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import java.awt.event.WindowEvent;

public class Game implements GLEventListener {
    
    private Player player;

    @Override
    public void init(GLAutoDrawable glad) {
        player = new Player(0.0f, -1.0f + 0.05f);
    }

    @Override
    public void dispose(GLAutoDrawable glad) {
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        int numRows = 13; 
        float rowHeight = 2.0f / numRows;

        for (int i = 0; i < numRows; i++) {
            float yStart = -1.0f + i * rowHeight;
            float yEnd = yStart + rowHeight;

            switch (i % 3) {
                case 0 -> gl.glColor3f(0.0f, 0.8f, 0.0f);
                case 1 -> gl.glColor3f(0.4f, 0.4f, 0.4f);
                case 2 -> gl.glColor3f(0.0f, 0.0f, 0.8f);
            }


            gl.glBegin(GL2.GL_QUADS);
            gl.glVertex2f(-1.0f, yStart); 
            gl.glVertex2f(1.0f, yStart); 
            gl.glVertex2f(1.0f, yEnd);   
            gl.glVertex2f(-1.0f, yEnd);   
            gl.glEnd();
        }
        
        player.draw(gl);
        
        gl.glFlush();
        
        if (player.getY() + 0.09f >= 1.0f) {
            Frogger.setCurrentScreen(new WinScreen());
        }
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int width, int height) {
        GL2 gl = glad.getGL().getGL2();

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
    
    public Player getPlayer(){
        return this.player;
    }
}
