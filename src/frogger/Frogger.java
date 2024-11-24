package frogger;

import Screen.HomeScreen;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;


public class Frogger {
    private static GLWindow window = null; 
    public static int screenWidth = 1200;
    public static int screenHeight = 800;
    
    public static void init(){
        GLProfile.initSingleton();
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);
        
        window = GLWindow.create(caps);
        window.setSize(screenWidth, screenHeight);
        window.setResizable(false);
        window.setTitle("Frogger");
        
        HomeScreen homeScreen = new HomeScreen();
        
        window.addKeyListener(new KeyBoard("homeGame"));
        window.addGLEventListener(homeScreen);
        
        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();
        
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent e) {
                System.exit(0);
            }
        });
        
//        SoundLoader player = new SoundLoader();
//        player.playSound(player.getBaseSound());  

        window.setVisible(true);
    }
    
    public static void setCurrentScreen(GLEventListener screen) {
        if (window.getGLEventListenerCount() > 0) {
            window.removeGLEventListener(window.getGLEventListener(0));
        }
        window.addGLEventListener(screen);
    }
            
    public static void main(String[] args) {
        init();
    }
    
    
}
