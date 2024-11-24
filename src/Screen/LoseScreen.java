package Screen;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;

public class LoseScreen implements GLEventListener{
    
    @Override
    public void init(GLAutoDrawable glad){
    }
    
    @Override
    public void dispose(GLAutoDrawable glad){}
    
    @Override
    public void display(GLAutoDrawable glad){
        GL2 gl = glad.getGL().getGL2();
        gl.glClearColor(0, 1.0f, 0, 0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
    }
    
    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3){}
}
