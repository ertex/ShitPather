package shit.pather;

import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
// Map and shit.pather belongs to David Johansson Te2

    private byte mapSize;
    private PathObject map[][];
    //  private ArrayList<PathObject> Pathers;
    private int x, y, width, height;
    private final int PathObjectSize = 20;
    private boolean foundExit;
    private byte exitPath[][];
    private PathObject exitObject;

    public Map() {
        mapSize = 20;
        map = new PathObject[mapSize][mapSize];
    }

    public void draw(Graphics g) {

    }

    public void updatePathing() {
        for (byte x = 0; x < mapSize; x++) {
            for (byte y = 0; y < mapSize; y++) {
                if (map[x][y].getActive()) {//only updates the active objects

                    if (map[x + 1][y].getType() == 0) {//the following if statements checks to see if there are empty spaces next to it, if so, paste a new object
                        map[x + 1][y] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                    } else if (map[x + 1][y].getType() == 2) {
                        foundExit = true;
                        exitObject = map[x + 1][y];
                    }
                    if (map[x - 1][y].getType() == 0) {
                        map[x - 1][y] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                    } else if (map[x - 1][y].getType() == 2) {
                        foundExit = true;
                        exitObject = map[x + 1][y];
                    }
                    if (map[x][y + 1].getType() == 0) {
                        map[x][y + 1] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                    } else if (map[x][y + 1].getType() == 2) {
                        foundExit = true;
                        exitObject = map[x][y + 1];
                    }
                    if (map[x][y - 1].getType() == 0) {
                        map[x][y - 1] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                    } else if (map[x][y - 1].getType() == 2) {
                        foundExit = true;
                        exitObject = map[x][y - 1];
                    }
                    map[x][y].setState(false);
                }
            }
        }
        if(!foundExit){
        updatePathing();
        }
        else{
        createExitPath(exitObject);
        }
    }

    public void changeMap(byte x, byte y, byte type) {

    }

    public void resetMap() {

    }

    public void checkSurroundings() {

    }

    public void createExitPath(PathObject exit) {
        
    }

    public int getPathObjectSize() {
        return PathObjectSize;
    }

    public byte getMapSize() {
        return mapSize;
    }
}
