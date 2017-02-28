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
        mapSize = 30;
        map = new PathObject[mapSize][mapSize];
        map[5][5] = new PathObject((byte) 5, (byte) 5, 0);
        map[25][25] = new PathObject(25, 25);//Exit
//        map[7][7] = new PathObject(7,7,true); //Wall
//        map[8][7] = new PathObject(8,7,true); //Wall
//        map[6][7] = new PathObject(6,7,true); //Wall
//        map[7][9] = new PathObject(7,9,true); //Wall
//        map[8][9] = new PathObject(8,9,true); //Wall
//        map[6][9] = new PathObject(6,9,true); //Wall
//        map[8][8] = new PathObject(8,8,true); //Wall
//        map[9][7] = new PathObject(9,7,true); //Wall

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
        if (!foundExit) {
            System.out.println("Pathing...");
            for (int x = 1; x < mapSize - 1; x++) {
                for (int y = 1; y < mapSize - 1; y++) {
                    if (map[x][y] != null) {
                        if (map[x][y].getActive()) {//only updates the active objects

                            if (map[x + 1][y] == null) {//the following if statements checks to see if there are empty spaces next to it, if so, pastes a new object

                                map[x + 1][y] = new PathObject(x, y, map[x][y].getLenght() + 1);
                            } else if (map[x + 1][y].getType() == 2) {
                                foundExit = true;
                                exitObject = map[x + 1][y];
                            }
                            if (map[x - 1][y] == null) {
                                map[x - 1][y] = new PathObject(x, y, map[x][y].getLenght() + 1);
                            } else if (map[x - 1][y].getType() == 2) {
                                foundExit = true;
                                exitObject = map[x + 1][y];
                            }
                            if (map[x][y + 1] == null) {
                                map[x][y + 1] = new PathObject(x, y, map[x][y].getLenght() + 1);
                            } else if (map[x][y + 1].getType() == 2) {
                                foundExit = true;
                                exitObject = map[x][y + 1];
                            }
                            if (map[x][y - 1] == null) {
                                map[x][y - 1] = new PathObject(x, y, map[x][y].getLenght() + 1);
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
        if (map[x][y] != null) {
            map[x][y].setType(type);
        } else if (type == 3) {
            map[x][y] = new PathObject(x, y, true);
        }
    }

    public void resetMap() {

    }

    public PathObject CompareObject(int x, int y, PathObject o, int L) { //Terrible name
        if (map[x][y] != null) {
            //   System.out.println("is : "+map[x - 1][y].getlenght()+" < "+L);
            if (map[x][y].getLenght() < L & map[x - 1][y].getType() == 1) {
                o = map[x - 1][y];

                System.out.println(x + ":" + y);
                exitPath[o.getLenght()][0] = x;//saves it in an array
                exitPath[o.getLenght()][1] = y;
                return o;
            }

        }
        return null;
    }

    public void createExitPath(PathObject exit) {
        if (foundExit) { //security so it can oly be accesible when the exit has been found
            PathObject o = exit;
            x = exit.getX();
            y = exit.getY();
            System.out.println("the exit is at" + x + " : " + y);
            exitPath = new int[o.getLenght()][2]; //creates a exitpath array the same size as how many needs to be fitted into it
            while (o.getLenght() > 1) {

                o.setType((byte) 0);
                o.updateColor();

                x = o.getX();
                y = o.getY();
                int L = o.getLenght();
                PathObject u;

                u = CompareObject(x + 1, y, o, L);
                if (u != null) {
                    o = u;
                }

                u = CompareObject(x - 1, y, o, L);
                if (u != null) {
                    o = u;
                }

                u = CompareObject(x, y + 1, o, L);
                if (u != null) {
                    o = u;
                }

                u = CompareObject(x, y - 1, o, L);
                if (u != null) {
                    o = u;
                }

            }
            System.out.println("The Path Has been found!");
        }
    }

    public void showPath() {
        for (int x = exitObject.getLenght(); x > 1; x--) {
            map[exitPath[x][0]][exitPath[x][1]].setType((byte) 0);
            map[exitPath[x][0]][exitPath[x][1]].updateColor();
            System.out.println("Lenght"+map[exitPath[x][0]][exitPath[x][1]].getLenght());
        }
    }

    public int getMapSize() {
        return mapSize;
    }
}
