package shit.pather;

import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
// Map and shit.pather belongs to David Johansson Te2

    private byte mapSize;
    private PathObject map[][];
    //  private ArrayList<PathObject> Pathers;
    private int x, y, width, height;

    private boolean foundExit;
    private byte exitPath[][];
    private PathObject exitObject;

    public Map() {
        mapSize = 20;
        map = new PathObject[mapSize][mapSize];
        map[5][5] = new PathObject((byte) 5, (byte) 5, (byte) 0, (byte) 0);
        map[10][10] = new PathObject((byte) 10, (byte) 10, (byte) 2, (byte) 5);
    }

    public void draw(Graphics g) {
        for (byte x = 0; x < mapSize; x++) {
            for (byte y = 0; y < mapSize; y++) {
                if (map[x][y] != null) {
                    System.out.println("This is a debug message10203");
                    map[x][y].draw(g);
                }
            }
        }

    }

    public void updatePathing() {
        System.out.println("This is a debug message");
        for (byte x = 1; x < mapSize - 1; x++) {
            for (byte y = 1; y < mapSize - 1; y++) {
                if (map[x][y] != null) {
                    if (map[x][y].getActive()) {//only updates the active objects

                        if (map[x + 1][y] == null) {//the following if statements checks to see if there are empty spaces next to it, if so, pastes a new object
                            map[x + 1][y] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                        } else if (map[x + 1][y].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x + 1][y];
                        }
                        if (map[x - 1][y] == null) {
                            map[x - 1][y] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                        } else if (map[x - 1][y].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x + 1][y];
                        }
                        if (map[x][y + 1] == null) {
                            map[x][y + 1] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                        } else if (map[x][y + 1].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x][y + 1];
                        }
                        if (map[x][y - 1] == null) {
                            map[x][y - 1] = new PathObject(x, y, (byte) 1, (byte) (map[x][y].getLenght() + 1));
                        } else if (map[x][y - 1].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x][y - 1];
                        }
                    }
                    map[x][y].update();//changes the active(used) objects and the newly placed objects to true.
                    //the newly placed are set to active = false by default so they don't get triggered while it's itterating
                }
            }
        }
        if (!foundExit) {//will repeat until exit is found
            updatePathing();//calls itself 
        } else {
            System.out.println("THAH got em!");
            createExitPath(exitObject);//Call to find the path to the exit

        }
    }

    public void changeMap(byte x, byte y, byte type) {

    }

    public void resetMap() {

    }

    public void checkSurroundings() {

    }

    public void createExitPath(PathObject exit) {
        if (foundExit) { //security so it can oly be accesible when the exit has been found
            PathObject o = exit;
            x = exit.getX();
            y = exit.getY();
            while (o.getlenght() > 0) {
                x = o.getX();
                y = o.getY();
                if (map[x + 1][y] != null) {//makes sure no nullpointers get into the system
                    if (map[x + 1][y].getlenght() < o.getlenght()) {//checks if any of the nearby pathobjects are closer to the origin, if so, saves the location ...
                        //of the object so It can be displayed as a path later on, also saves the object into "o" so it ccan check whst is closer around itself
                        o = map[x + 1][y];
                        System.out.println(x + " : " + y);
                        exitPath[o.getLenght()][0] = (byte) x;
                        exitPath[o.getLenght()][1] = (byte) y;
                    }
                }
                if (map[x - 1][y] != null) {
                    if (map[x - 1][y].getlenght() < o.getlenght()) {
                        o = map[x - 1][y];
                        System.out.println(x + " : " + y);
                        exitPath[o.getLenght()][0] = (byte) x;
                        exitPath[o.getLenght()][1] = (byte) y;
                    }
                }
                if (map[x][y + 1] != null) {
                    if (map[x][y + 1].getlenght() < o.getlenght()) {
                        o = map[x][y + 1];
                        System.out.println(x + " : " + y);
                        exitPath[o.getLenght()][0] = (byte) x;
                        exitPath[o.getLenght()][1] = (byte) y;
                    }
                }
                if (map[x][y - 1] != null) {
                    if (map[x][y - 1].getlenght() < o.getlenght()) {
                        o = map[x][y - 1];
                        System.out.println(x + " : " + y);
                        exitPath[o.getLenght()][0] = (byte) x;
                        exitPath[o.getLenght()][1] = (byte) y;
                    }
                }
            }

        }
    }

    public byte getMapSize() {
        return mapSize;
    }
}
