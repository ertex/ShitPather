package shit.pather;

import java.awt.Color;
import java.awt.Graphics;

public class PathObject {
// PathObject and shit.pather belongs to David Johansson Te2

    private int x, y, lenght;
    private boolean active, updateToActive;
    private Color color;
    private byte type;

    public PathObject(int x, int y, int lenght) { //"Regular" pathobject used to find the exit
        this.x = x;//the position in the grid object, not on the screen
        this.y = y;
        type = 1;
        this.lenght = lenght;
        active = false;
        updateToActive = true;//the PathObject need to have on eitteration lag behind in order 
        updateColor();

    }

    public PathObject(int x, int y, boolean Static) {//If it is a wall
        if (Static) {
            this.x = x;//the position in the grid object, not on the screen
            this.y = y;
            type = 3;
            active = false;
            updateColor();

        }
    }

    public PathObject(int x, int y) { //The object that is the exit
        this.x = x;//the position in the grid object, not on the screen
        this.y = y;
        type = 2;
        lenght = 13370;//really high, out since this is the last object and in findExit() it looks for the previous object
        active = false;
        updateColor();

    }

    public void update() {//updates the state of the object and making it behave correctly in the array
        if (active) {
            active = false;
        }
        if (updateToActive) {
            active = true;
            updateToActive = false;
            updateColor();
        }
    }

    public void checkSurroundings() {

    }

    public void updateColor() {
        if (type == 0) {
            color = Color.YELLOW;
        } else if (type == 1) {
            color = Color.BLACK;
        } else if (type == 2) {
            color = Color.RED;
        } else if (type == 3) { //wall
            color = Color.BLUE;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x * PathProgram.PathObjectSize, y * PathProgram.PathObjectSize, PathProgram.PathObjectSize, PathProgram.PathObjectSize);
        if (type == 1) {
            g.setColor(color.WHITE);
            g.drawString("" + lenght, x * PathProgram.PathObjectSize + PathProgram.PathObjectSize / 2 - 10, y * PathProgram.PathObjectSize + PathProgram.PathObjectSize / 2);
        }
    }

    public void setType(byte type) {
        this.type = type;
    }

    public int getLenght() {
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
}
