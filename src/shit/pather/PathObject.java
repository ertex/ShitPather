package shit.pather;

import java.awt.Color;
import java.awt.Graphics;

public class PathObject {
// PathObject and shit.pather belongs to David Johansson Te2

    private int x, y;
    private boolean active,updateToActive;
    private Color color;
    private byte type, lenght;

    public PathObject(byte x, byte y, byte lenght) { //"Regular" pathobject used to find the exit
        this.x = x;//the position in the grid object, not on the screen
        this.y = y;
        type = 1;
        this.lenght = lenght;
        active = false;
        updateToActive = true;//the PathObject need to have on eitteration lag behind in order 
        updateColor();

    }
        public PathObject(byte x, byte y) { //The object that is the exit
        this.x = x;//the position in the grid object, not on the screen
        this.y = y;
        type = 2;
        lenght = 127;//capped out since this is the last object and in findExit() it looks for the previous object
        active = false;
        updateColor();

    }
    
    public void update(){//updates the state of the object and making it behave correctly in the array
        if(active){
        active = false;
        }
    if(updateToActive){
        active = true;
        updateToActive = false;
        updateColor();
    }
    }

    public void checkSurroundings() {

    }

    public void updateColor() {
        if (type == 0) {
            color = Color.WHITE;
        } else if (type == 1) {
            color = Color.BLACK;
        } else if (type == 2) {
            color = Color.RED;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x * PathProgram.PathObjectSize, y * PathProgram.PathObjectSize, PathProgram.PathObjectSize, PathProgram.PathObjectSize);
        g.setColor(color.WHITE);
        g.drawString(""+lenght, x*PathProgram.PathObjectSize+PathProgram.PathObjectSize/2-10, y*PathProgram.PathObjectSize+PathProgram.PathObjectSize/2);
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getlenght() {
        return lenght;
    }

    public boolean getActive() {
        return active;
    }

    public void setState(boolean state) {
        active = state;
    }

    public byte getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public byte getLenght() {
        return lenght;
    }
}
