import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simon extends JFrame implements MouseListener, ActionListener
{
    final Dimension SIZE = new Dimension(910, 700);
    private SimonSquare redButton;
    private SimonSquare blueButton;
    private SimonSquare greenButton;
    private SimonSquare yellowButton;
    private JLabel label;
    private JButton button;
    private ArrayList<SimonSquare> colorList;
    private int pos;
    private static boolean playerTurn;
    private ArrayList<SimonSquare> colors;

    public Simon()
    {

        this.setSize(SIZE);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(3,2));

        this.colors = new ArrayList<SimonSquare>();
        this.redButton = new SimonSquare(Color.RED);
        this.colors.add(redButton);
        this.blueButton = new SimonSquare(Color.BLUE);
        this.colors.add(blueButton);
        this.greenButton = new SimonSquare(Color.GREEN);
        this.colors.add(greenButton);
        this.yellowButton = new SimonSquare(Color.YELLOW);
        this.colors.add(yellowButton);
        this.label = new JLabel();
        this.button = new JButton();

        this.redButton.addMouseListener(this);
        this.blueButton.addMouseListener(this);
        this.greenButton.addMouseListener(this);
        this.yellowButton.addMouseListener(this);

        this.add(redButton);
        this.add(blueButton);
        this.add(greenButton);
        this.add(yellowButton);
        this.add(label);
        this.add(button);

        this.setTitle("Simon");
        this.label.setText("Simon");
        this.label.setFont(new Font("Arial", Font.PLAIN, 90));
        this.button.setText("Replay Sequence");
        this.button.addActionListener(this);

        this.colorList = new ArrayList<SimonSquare>();
        this.addColor();
    }

    private void addColor()
    {
        this.add(button);
        this.label.setText("Simon");
        pos = 0;
        playerTurn = false;
        int c = (int) (Math.random() *4);
        this.colorList.add(colors.get(c));
        showSequence(pos);
    }

    private void showSequence(int i)
    {
        if(i < colorList.size())
        {
            SimonSquare s = colorList.get(i);
            
            ActionListener e = new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    s.setDark();
                }
            };

            Timer t = new Timer(400, e);
            t.setRepeats(false);
            t.start();

            e = new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    s.setBright();
                    showSequence(i+1);
                }
            };

            t = new Timer(500, e);
            t.setRepeats(false);
            t.start();
        }
        playerTurn = true;
        return;
    }

    public static void main(String[] args) 
    {
        new Simon();
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(playerTurn)
        {
            SimonSquare s = (SimonSquare) e.getSource();
            if(s == colorList.get(pos))
            {
                this.label.setText("Correct!");
                if(pos < colorList.size()-1)
                pos++;
                else
                addColor();
            }
            else
            {
                this.label.setText("WRONG");
                this.colorList = new ArrayList<SimonSquare>();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                
                this.addColor();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {   
        if(playerTurn)
        {
            SimonSquare button = (SimonSquare) e.getSource();
            Color darker = button.getBackground().darker();
            button.setBackground(darker);    
        } 
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        if(playerTurn)
        {
            SimonSquare button = (SimonSquare) e.getSource();
            Color brighter = button.getBackground().brighter();
            button.setBackground(brighter);  
        }      
    }

    @Override
    public void mouseEntered(MouseEvent e) {        
    }

    @Override
    public void mouseExited(MouseEvent e) {        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.showSequence(0);
        this.remove(button);
    }
}
