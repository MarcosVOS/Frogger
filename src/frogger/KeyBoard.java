package frogger;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyBoard implements KeyListener{
    private Game game;
    private String gameStatus;
    
    public KeyBoard(String status){
        this.gameStatus = status;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        if("homeGame".equals(gameStatus) && e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStatus = "runGame";
            Game newGame = new Game(this); 
            this.game = newGame; 
            Frogger.setCurrentScreen(newGame);
        }
        
        if("runGame".equals(gameStatus)){
            if(e.getKeyCode() == KeyEvent.VK_UP)
                game.getPlayer().moveUp();
        
            if(e.getKeyCode() == KeyEvent.VK_DOWN)
                game.getPlayer().moveDown();

            if(e.getKeyCode() == KeyEvent.VK_LEFT)
                game.getPlayer().moveLeft();

            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                game.getPlayer().moveRight();
        }
        
        if("loseGame".equals(gameStatus)){
            if(e.getKeyCode() == KeyEvent.VK_R){
                 gameStatus = "runGame";
                Game newGame = new Game(this); 
                this.game = newGame; 
                Frogger.setCurrentScreen(newGame);
            }
            
            if(e.getKeyCode() == KeyEvent.VK_X){
                 System.exit(0);
            }
        }
    }
    
    public void setGameStatus(String gameStatus){
        this.gameStatus = gameStatus; 
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}