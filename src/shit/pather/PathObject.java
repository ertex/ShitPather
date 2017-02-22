package shit.pather;

import java.awt.Color;
import java.awt.Graphics;

public class PathObject {
// PathObject and shit.pather belongs to David Johansson Te2

    private int x, y;
    private boolean active;
    private Color color;
    private byte type, lenght;

    public PathObject(byte x, byte y, byte type, byte lenght) {
        this.x = x;//the position in the grid object, not on the screen
        this.y = y;
        this.type = type;
        this.lenght = lenght;
        updateColor();

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
        g.drawRect(x * PathProgram.PathObjectSize, y * PathProgram.PathObjectSize, PathProgram.PathObjectSize, PathProgram.PathObjectSize);
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
