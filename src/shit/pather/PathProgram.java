package shit.pather;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;

public class PathProgram implements MouseListener {

    Canvas canvas;
    private int xSize, ySize,guiWidth;
    private Map map;
    private byte selectedType;
    static final int PathObjectSize = 10;
    private JButton button1, button2, button3;
    private JPanel guiPanel;
    private ActionHandler actionHandler = new ActionHandler();
    private boolean pathing ;
// PathProgram and shit.pather belongs to David Johansson Te2

    public PathProgram() {
        map = new Map(); //This needs to be before CreateAndShowGui(), since it's using methods from it to make the window
        CreateAndShowGui();
        run();
    }

    public void CreateAndShowGui() {
        xSize = PathObjectSize * map.getMapSize() + 100;
        ySize = PathObjectSize * map.getMapSize();
        System.out.println(xSize);
        JFrame frame = new JFrame("Shit Pather");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new Canvas();
        canvas.setSize(xSize, ySize);
        canvas.setVisible(true);
        canvas.setBackground(Color.blue);

        frame.setVisible(true);
        frame.setSize(xSize + 100, ySize + 100);

        guiPanel = new JPanel();
        guiPanel.setSize(100, ySize);
        guiPanel.setLayout(new BoxLayout(guiPanel, 1));
        frame.add(guiPanel, BorderLayout.WEST);
        frame.add(canvas, BorderLayout.EAST);

        button1 = new JButton("Start");
        button1.setVisible(true);
        button1.setText("Start");
        button1.addActionListener(actionHandler);
        guiPanel.add(button1);

        button2 = new JButton("Reset");
        button2.setVisible(true);
        button2.setText("Reset");
        button2.addActionListener(actionHandler);
        guiPanel.add(button2);

        button3 = new JButton("ChangeType");
        button3.setVisible(true);
        button3.setText("Change XXX");
        button3.addActionListener(actionHandler);
        guiPanel.add(button3);

        guiWidth = guiPanel.getWidth();

    }

    public void draw(Graphics g) {

    }

    public void run() {
        while (true) {
            map.draw(canvas.getGraphics());
            

        }

    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK!");
        if(!pathing){
        map.changeMap((int)((e.getX()-guiWidth)/map.getMapSize()),(int)(e.getY()/map.getMapSize()),selectedType);    
        }

    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    private class ActionHandler implements ActionListener//this listens if a action is performed and exceutes the linked action 
    {

        public void actionPerformed(ActionEvent e) {

            try {

                String cmd = e.getActionCommand();
                switch (cmd) {
                    case "Start":
                        pathing = true;
                        
                        System.out.println("Start");
                        map.updatePathing();
                        break;

                    case "Reset":
                        pathing = false;
                        System.out.println("Reset");
                        break;

                    case "ChangeType":
                        System.out.println("ChangeType");
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //   }
        }
    }

}
