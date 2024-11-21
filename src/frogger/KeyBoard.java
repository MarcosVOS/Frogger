package frogger;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyBoard implements KeyListener{
    private final Game game;
    
    public KeyBoard(Game cena){
        this.game = cena;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {        
    
        if(e.getKeyCode() == KeyEvent.VK_UP)
            game.getPlayer().moveUp();
        
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            game.getPlayer().moveDown();
        
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            game.getPlayer().moveLeft();
        
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            game.getPlayer().moveRight();
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}