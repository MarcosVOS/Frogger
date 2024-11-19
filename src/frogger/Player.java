package frogger;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import resourceLoader.ImageLoader;

public class Player {
    private float x, y;
    private final float stepY; // Altura de uma linha
    private final float stepX; // Largura de um passo horizontal

    public Player(float x, float y) {
        this.x = x;
        this.y = y;

        // Baseado na tela (-1.0 a 1.0) e número de linhas/colunas
        int numRows = 13; 
        int numCols = 5; 
        this.stepY = 2.0f / numRows; // Altura de uma linha
        this.stepX = 2.0f / numCols; // Largura de um passo (ajuste conforme necessário)
    }

    public void moveUp() {
        this.y += stepY;
        if (this.y > 1.0f) { // Limita ao topo da tela
            this.y = 1.0f;
        }
    }

    public void moveDown() {
        this.y -= stepY;
        if (this.y < -1.0f) { // Limita à base da tela
            this.y = -1.0f;
        }
    }

    public void moveLeft() {
        this.x -= stepX;
        if (this.x < -1.0f) { // Limita à borda esquerda
            this.x = -1.0f;
        }
    }

    public void moveRight() {
        this.x += stepX;
        if (this.x > 1.0f) { // Limita à borda direita
            this.x = 1.0f;
        }
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
