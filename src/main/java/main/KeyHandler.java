package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    GameWindow gw;
    public boolean upPressed, downPressed, leftPressed, rightPressed, shotKeyPressed;

    public KeyHandler(GameWindow gw)
    {
        this.gw = gw;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_ESCAPE:
                if (gw.gameState == gw.playState) {
                    gw.gameState = gw.pauseState;
                }
                else if (gw.gameState == gw.pauseState) {
                    gw.gameState = gw.playState;
                }
                break;
            case KeyEvent.VK_F:
                shotKeyPressed = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_F:
                shotKeyPressed = false;
                break;
            default:
                break;
        }
    }

}