package shit.pather;

import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
// Map and shit.pather belongs to David Johansson Te2
private byte map[][][],mapSize;
private ArrayList<PathObject> Pathers;
private int x,y,width,height;
private final int PathObjectSize = 20;
private PathObject OriginPathObject;

public Map(){
mapSize = 20;
map = new byte[mapSize][mapSize][mapSize];
}

public void draw(Graphics g){

}
public void updatePathing(){

}
public void changeMap(byte x,byte y,byte type){

}
public void resetMap(){

}
public void findExit(PathObject origin,PathObject exit){

}
public int getPathObjectSize(){
return PathObjectSize;
}
public byte getMapSize(){
return mapSize;
}
}