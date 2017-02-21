package shit.pather;

import java.awt.Color;
import java.awt.Graphics;

public class PathObject {
// PathObject and shit.pather belongs to David Johansson Te2
private int x,y,width,height;
private boolean foundExit,active;
private Color color;
private byte type,lenght;

public PathObject(byte x,byte y,byte type,byte lenght){
this.x = x;
this.y = y;
this.type = type;
this.lenght = lenght;

}

public void CheckSurroundings(){

}
public void draw(Graphics g){

}

public boolean getActive(){
return active;
}

public void setState(boolean state){
active = state;
}

public byte getType(){
return type;
}
public int getX(){
return x;
}
public int getY(){
return y;
}
public byte getLenght(){
return lenght;
}
}