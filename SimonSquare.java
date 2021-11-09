import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimonSquare extends JPanel implements ActionListener
{
    public SimonSquare(Color c)
    {
        this.setBackground(c);
    }

    public void setDark()
    {
        this.setBackground(this.getBackground().darker());
    }

    public void setBright()
    {
        this.setBackground(this.getBackground().brighter());
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
        this.setBright();        
    }
    
}
