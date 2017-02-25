package shit.pather;

import java.awt.Graphics;
import java.util.ArrayList;

public class Map {
// Map and shit.pather belongs to David Johansson Te2

    private int mapSize;
    private PathObject map[][];
    //  private ArrayList<PathObject> Pathers;
    private int x, y, width, height;

    private boolean foundExit;
    private int exitPath[][];
    private PathObject exitObject;

    public Map() {
        mapSize = 80;
        map = new PathObject[mapSize][mapSize];
        map[5][5] = new PathObject((byte) 5, (byte) 5, 0);
        map[70][70] = new PathObject((byte) 70, (byte) 70);//Exit
    }

    public void draw(Graphics g) {
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                if (map[x][y] != null) {

                    map[x][y].draw(g);
                }
            }
        }

    }

    public void updatePathing() {
        if(!foundExit){
        System.out.println("This is a debug message");
        for (int x = 1; x < mapSize - 1; x++) {
            for (int y = 1; y < mapSize - 1; y++) {
                if (map[x][y] != null) {
                    if (map[x][y].getActive()) {//only updates the active objects

                        if (map[x + 1][y] == null) {//the following if statements checks to see if there are empty spaces next to it, if so, pastes a new object
                            map[x + 1][y] = new PathObject(x, y,  map[x][y].getLenght() + 1);
                        } else if (map[x + 1][y].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x + 1][y];
                        }
                        if (map[x - 1][y] == null) {
                            map[x - 1][y] = new PathObject(x, y,  map[x][y].getLenght() + 1);
                        } else if (map[x - 1][y].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x + 1][y];
                        }
                        if (map[x][y + 1] == null) {
                            map[x][y + 1] = new PathObject(x, y,  map[x][y].getLenght() + 1);
                        } else if (map[x][y + 1].getType() == 2) {
                            foundExit = true;
                            exitObject = map[x][y + 1];
                        }
                        if (map[x][y - 1] == null) {
                            map[x][y - 1] = new PathObject(x, y,  map[x][y].getLenght() + 1);
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
    }

    public void changeMap(int x, int y, byte type) {
        map[x][y].setType(type);

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
            System.out.println("the exit is at"+x+" : "+y);
            exitPath = new int[o.getLenght()][2]; //cerates a exitpath array the same size as how many needs to be fitted into it
            while (o.getLenght() > 1) {

                x = o.getX();
                y = o.getY();
               int L = o.getLenght();
                if (map[x + 1][y] != null) {//makes sure no nullpointers get into the system
                //     System.out.println("is : "+map[x + 1][y].getlenght()+" < "+L);
                    if (map[x + 1][y].getLenght() < L) {//checks if any of the nearby pathobjects are closer to the origin, if so, saves the location ...
                        //of the object so It can be displayed as a path later on, also saves the object into "o" so it ccan check whst is closer around itself
                        o = map[x + 1][y];
                        System.out.println(x + " :1: " + y);
                        exitPath[o.getLenght()][0] = ( x+1);
                        exitPath[o.getLenght()][1] =  y;
                    }
                  
                }
                if (map[x - 1][y] != null) {
                 //   System.out.println("is : "+map[x - 1][y].getlenght()+" < "+L);
                    if (map[x - 1][y].getLenght() < L) {
                        o = map[x - 1][y];
                        System.out.println(x + ":2 : " + y);
                        exitPath[o.getLenght()][0] =  (x-1);
                        exitPath[o.getLenght()][1] =  y;
                    }
                  
                }
                if (map[x][y + 1] != null) {
                 //   System.out.println("is : "+map[x][y+1].getlenght()+" < "+L);
                    if (map[x][y + 1].getLenght() < L) {
                        o = map[x][y + 1];
                        System.out.println(x + " :3: " + y);
                        exitPath[o.getLenght()][0] =  x;
                        exitPath[o.getLenght()][1] =  (y+1);
                    }
                    
                }
                if (map[x][y - 1] != null) {
                 //   System.out.println("is : "+map[x][y-1].getlenght()+" < "+L);
                    if (map[x][y - 1].getLenght() < L) {
                        o = map[x][y - 1];
                        System.out.println(x + ": 4: " + y);
                        exitPath[o.getLenght()][0] =  x;
                        exitPath[o.getLenght()][1] =  (y-1);
                    }
                   
                }
            }
            System.out.println("The Path Has been found!");
        }
    }

    public int getMapSize() {
        return mapSize;
    }
}
