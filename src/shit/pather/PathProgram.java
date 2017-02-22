package shit.pather;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class PathProgram {

    Canvas canvas;
    private int xSize, ySize;
    Map map;
    static final int PathObjectSize = 20;
    private JButton button;
    private JPanel guiPanel;
    private ActionHandler actionHandler = new ActionHandler();
// PathProgram and shit.pather belongs to David Johansson Te2

    public PathProgram() {
        map = new Map(); //This needs to be before CreateAndShowGui(), since it's using methods from it to make the window
        CreateAndShowGui();
    }

    public void CreateAndShowGui() {
        xSize = PathObjectSize * map.getMapSize() + 100;
        ySize = PathObjectSize * map.getMapSize();
        JFrame frame = new JFrame("Shit Pather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setSize(xSize, ySize);
        canvas.setVisible(true);
        frame.setVisible(true);
        frame.setSize(xSize, ySize);

        guiPanel = new JPanel();
        guiPanel.setSize(100, ySize);
        frame.add(guiPanel, BorderLayout.WEST);

        button = new JButton("Start"); // the button for high attacks
        button.setVisible(true);
        button.setText("Start");
        button.addActionListener(actionHandler);
        guiPanel.add(button);
        
        frame.pack();
    }

    public void draw(Graphics g) {

    }

    public void run() {
        while(true){
        map.draw(canvas.getGraphics());
        
        }

    }

    private class ActionHandler implements ActionListener//this listens if a action is performed and exceutes the linked action 
    {

        public void actionPerformed(ActionEvent e) {

            try {

                String cmd = e.getActionCommand();
                switch (cmd) {
                    case "Start":
                        System.out.println("This is a debug message");
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //   }
        }
    }

}
