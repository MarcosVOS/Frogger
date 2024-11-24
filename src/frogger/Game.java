package frogger;

import Screen.LoseScreen;
import Screen.WinScreen;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import java.util.ArrayList;
import java.util.List;
import resourceLoader.SoundLoader;

public class Game implements GLEventListener {
    
    private Player player;
    private List<Obstacle> obstacles;
    private List<Platform> platforms;
    private static final float SPEED_MULTIPLIER = 0.7f;
    private KeyBoard keyboard;
    private SoundLoader sounds;


    

    public Game(KeyBoard keyboard) {
        this.keyboard = keyboard;
        this.sounds = new SoundLoader();
    }
    

    @Override
    public void init(GLAutoDrawable glad) {
        player = new Player(0.0f, -1.0f + 0.05f);
        obstacles = new ArrayList<>();
        generateObstacle();
        generatePlatorms();
    }
    
    private void generatePlatorms() {
        platforms = new ArrayList<>();
        int numRows = 13;
        float rowHeight = 2.0f / numRows;

        for (int i = 8; i < 13; i++) { 
            float yPosition = -1.0f + i * rowHeight;
            float platformWidth = 0.5f; 
            float baseSpeed = 0.01f + (float) Math.random() * 0.02f; 
            float speed = baseSpeed * SPEED_MULTIPLIER; 
            platforms.add(new Platform(-0.5f, yPosition, platformWidth, speed));
        }
    }
    
    private boolean checkCollision(Player player, Obstacle obstacle) {
        float playerLeft = player.getX();
        float playerRight = playerLeft + 0.1f;
        float playerBottom = player.getY();
        float playerTop = playerBottom + 0.1f; 

        float obstacleLeft = obstacle.getX();
        float obstacleRight = obstacleLeft + obstacle.getWidth();
        float obstacleBottom = obstacle.getY();
        float obstacleTop = obstacleBottom + obstacle.getHeight();

        return playerRight > obstacleLeft && playerLeft < obstacleRight &&
               playerTop > obstacleBottom && playerBottom < obstacleTop;
    }
    
    private void generateObstacleForRow(float yPosition) {
    float minWidth = 0.1f; 
    float maxWidth = 0.3f; 
    float playerHeight = 0.1f;
    float speed = (0.01f + (float) Math.random() * 0.02f) * SPEED_MULTIPLIER;

    if (yPosition >= -1.0f + 1 * (2.0f / 13) && yPosition <= -1.0f + 6 * (2.0f / 13)) {
        if (yPosition <= -1.0f + 3 * (2.0f / 13)) {
            obstacles.add(new Obstacle(-1.0f, yPosition, playerHeight, minWidth, maxWidth, speed));
        } else {
            obstacles.add(new Obstacle(1.0f, yPosition, playerHeight, minWidth, maxWidth, -speed));
        }
    }
}

private void generateObstacle() {
    int numRows = 13;
    float rowHeight = 2.0f / numRows;
    float playerHeight = 0.1f;
    float minWidth = 0.1f;
    float maxWidth = 0.3f;

    for (int i = 1; i <= 6; i++) { 
        float yPosition = -1.0f + i * rowHeight;
        float startX;
        float speed = (0.01f + (float) Math.random() * 0.02f) * SPEED_MULTIPLIER;

        if (i <= 3) { 
            startX = -1.0f; 
            obstacles.add(new Obstacle(startX, yPosition, playerHeight, minWidth, maxWidth, speed));
        } else { 
            startX = 1.0f; 
            obstacles.add(new Obstacle(startX, yPosition, playerHeight, minWidth, maxWidth, -speed));
        }
    }
}

    
    public Player getPlayer(){
        return this.player;
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

    if (player.getY() + 0.09f >= 1.0f) {
        Frogger.setCurrentScreen(new WinScreen());
        return;
    }

    for (int i = 0; i < numRows; i++) {
        float yStart = -1.0f + i * rowHeight;
        float yEnd = yStart + rowHeight;

        if (i == 0 || i == 7) {
            gl.glColor3f(1.0f, 1.0f, 0.0f);
        } else if (i >= 1 && i <= 6) {
            gl.glColor3f(0.0f, 0.0f, 0.0f); 
        } else if (i == numRows - 1) {
            gl.glColor3f(0.0f, 1.0f, 0.0f); 
        } else {
            gl.glColor3f(0.0f, 0.0f, 1.0f); 
        }

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(-1.0f, yStart);
        gl.glVertex2f(1.0f, yStart);
        gl.glVertex2f(1.0f, yEnd);
        gl.glVertex2f(-1.0f, yEnd);
        gl.glEnd();
    }

    boolean onPlatform = false;
    for (Platform platform : platforms) {
        platform.update();
        platform.draw(gl);

        if (player.getY() < 0.89f && player.getY() >= platform.getY() && player.getY() <= platform.getY() + 0.1f &&
            player.getX() >= platform.getX() && player.getX() <= platform.getX() + platform.getWidth()) {
            if (player.getY() < 1.0f) { 
                onPlatform = true;
                player.setX(player.getX() + platform.getSpeed());
            }
        }
    }

    if (player.getY() < 0.89f &&   !onPlatform && player.getY() >= -1.0f + 8 * rowHeight && player.getY() < -1.0f + 13 * rowHeight) {
        keyboard.setGameStatus("loseGame");        
        Frogger.setCurrentScreen(new LoseScreen());
        return;
    }

    for (int i = 0; i < obstacles.size(); i++) {
        Obstacle obs = obstacles.get(i);
        obs.update();
        obs.draw(gl);

        if (checkCollision(player, obs)) {
            sounds.playSound(sounds.getSongCar());
            keyboard.setGameStatus("loseGame");
            Frogger.setCurrentScreen(new LoseScreen());
            return;
        }

        if (obs.isOffScreen(1.0f)) {
            float yPosition = obs.getY();
            obstacles.remove(i);
            i--;
            generateObstacleForRow(yPosition);
        }
    }

    player.draw(gl);
    gl.glFlush();
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
}
