package frogger;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyBoard implements KeyListener{
    private Game game;
    
    public KeyBoard(Game cena){
        this.game = cena;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {        
        //System.out.println("Key pressed: " + e.getKeyCode());
//        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            cena.anguloX = 0;
//            cena.anguloY = 0;
//            cena.anguloZ = 0;
//        }

//        
        if(e.getKeyCode() == KeyEvent.VK_UP)
            game.getPlayer().moveUp();
        
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            game.getPlayer().moveDown();
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            game.getPlayer().moveLeft();
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            game.getPlayer().moveRight();
        
//        if(e.getKeyCode() == KeyEvent.VK_SPACE)
//            cena.anguloZ -= 5;
//        
//        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
//            cena.anguloZ += 5;
        
        //if(e.getKeyChar() == 'a')
        //    System.out.println("Pressionou tecla a");
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}