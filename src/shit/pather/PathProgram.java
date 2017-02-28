package shit.pather;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.BoxLayout;

public class PathProgram implements MouseListener {

    Canvas canvas;
    private int xSize, ySize, guiWidth;
    private Map map;
    private byte selectedType;
    static final int PathObjectSize = 20;
    private JButton button1, button2, button3;
    private JPanel guiPanel;
    private ActionHandler actionHandler = new ActionHandler();
    private boolean pathing;
    private BufferStrategy bs;
    Graphics g;
// PathProgram and shit.pather belongs to David Johansson Te2

    public PathProgram() {
        selectedType = 3;
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
 
        //     canvas.setBackground(Color.blue);

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
        button3.setText("ChangeType");
        button3.addActionListener(actionHandler);
        guiPanel.add(button3);

        guiWidth = guiPanel.getWidth();

        canvas.addMouseListener(this);
               canvas.createBufferStrategy(2);//creates double buffering in canvas object
        bs = canvas.getBufferStrategy();

    }

    public void run() {
        while (true) {

            paintComponents();

        }

    }

    public void paintComponents() {
        g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0, 0, xSize, ySize); //clears the canvas
        map.draw(g);

        if (!bs.contentsLost()) {
            bs.show();
        }
    }

    public void cycleType() {
    
         if (selectedType == 1) {
            selectedType = 2;
        } else if (selectedType == 2) {
            selectedType = 3;
        } else if (selectedType == 3) {
            selectedType = 1;
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("CLICK!");
        if (!pathing) {
            
            map.changeMap((int) ((e.getX()) / PathObjectSize), (int) (e.getY() / PathObjectSize), selectedType);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
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
                        map.resetMap();
                        break;

                    case "ChangeType":
                        System.out.println("ChangeType current :"+selectedType);
                        cycleType();
                        break;

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }

}
