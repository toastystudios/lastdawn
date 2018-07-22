package toastystudios.lastdawn.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class KeyboardController implements InputProcessor {

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;

    public boolean isMouse1Down, isMouse2Down,isMouse3Down;
    public boolean isDragged;
    public Vector2 mouseLocation;

    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcessed = false;
        switch (keycode) // switch code base on the variable keycode
        {
            case Input.Keys.A:  	// if keycode is the same as Keys.LEFT a.k.a 21
                left = true;	// do this
                keyProcessed = true;
                break;	// we have reacted to a keypress so stop checking for more
            case Input.Keys.D: 	// if keycode is the same as Keys.LEFT a.k.a 22
                right = true;	// do this
                break;	// we have reacted to a keypress so stop checking for more
            case Input.Keys.W: 		// if keycode is the same as Keys.LEFT a.k.a 19
                up = true;		// do this
                break;  // we have reacted to a keypress so stop checking for more
            case Input.Keys.S: 	// if keycode is the same as Keys.LEFT a.k.a 20
                down = true;	// do this
                break;	// we have reacted to a keypress so stop checking for more
            case Input.Keys.SPACE:
                up = true;
                break;
        }
        return keyProcessed;	// no keys pressed that we care about so return false
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        switch (keycode) // switch code base on the variable keycode
        {
            case Input.Keys.A:  	// if keycode is the same as Keys.LEFT a.k.a 21
                left = false;	// do this
                keyProcessed = true;	// we have reacted to a keypress
                break;
            case Input.Keys.D: 	// if keycode is the same as Keys.LEFT a.k.a 22
                right = false;	// do this
                keyProcessed = true;	// we have reacted to a keypress
                break;
            case Input.Keys.W: 		// if keycode is the same as Keys.LEFT a.k.a 19
                up = false;		// do this
                keyProcessed = true;	// we have reacted to a keypress
                break;
            case Input.Keys.S: 	// if keycode is the same as Keys.LEFT a.k.a 20
                down = false;	// do this
                keyProcessed = true;	// we have reacted to a keypress
        }
        return keyProcessed;	//  return our peyProcessed flag
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        mouseLocation = new Vector2(screenX, screenY);
        if(button == 0){
            isMouse1Down = true;
        }else if(button == 1){
            isMouse2Down = true;
        }else if(button == 2){
            isMouse3Down = true;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        mouseLocation = new Vector2(screenX, screenY);
        isDragged = false;
        //System.out.println(button);
        if(button == 0){
            isMouse1Down = false;
        }else if(button == 1){
            isMouse2Down = false;
        }else if(button == 2){
            isMouse3Down = false;
        }
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mouseLocation = new Vector2(screenX, screenY);
        isDragged = true;
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLocation = new Vector2(screenX, screenY);
        mouseLocation.x = screenX;
        mouseLocation.y = screenY;
        return false;
    }
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
