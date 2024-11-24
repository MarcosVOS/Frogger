package Screen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import frogger.KeyBoard;
import resourceLoader.SoundLoader;

public class HomeScreen implements GLEventListener {
    
    private SoundLoader sounds;
    
    public HomeScreen() {
        this.sounds = new SoundLoader();
    }

    @Override
    public void init(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClearColor(0.0f, 1.0f, 0.0f, 1.0f); // RGB: (0, 1, 0), Alpha: 1
        
        sounds.playSound(sounds.getHome());
    }

    @Override
    public void display(GLAutoDrawable glad) {
        GL2 gl = glad.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
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
        // Nada a ser limpo, pois não estamos carregando recursos extras
    }
}
