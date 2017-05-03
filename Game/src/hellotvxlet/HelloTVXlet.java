package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.event.*;
import org.havi.ui.event.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Random;



public class HelloTVXlet implements Xlet, HActionListener {

    public HelloTVXlet() {
        
    } 
    
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        
        HScene scene=HSceneFactory.getInstance().getDefaultHScene();
        
        HTextButton knop1=new HTextButton("knop1",300,100,100,100);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop1.setBackground(Color.RED);
        scene.add(knop1);
        
        HTextButton knop2=new HTextButton("knop2",200,200,100,100);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop2.setBackground(Color.BLUE);
        scene.add(knop2);
        
        HTextButton knop3=new HTextButton("knop3",100,100,100,100);
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop3.setBackground(Color.GREEN);
        scene.add(knop3);
        
        HTextButton knop4=new HTextButton("knop4",200,0,100,100);
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop4.setBackground(Color.YELLOW);
        scene.add(knop4);
        
        knop1.setFocusTraversal(knop4, knop2, knop3, null);
        knop2.setFocusTraversal(knop4, null, knop3, knop1);
        knop3.setFocusTraversal(knop4, knop2, null, knop1);
        knop4.setFocusTraversal(null, knop2, knop3, knop1);
        
        knop1.setActionCommand("knop1a");
        knop2.setActionCommand("knop2a");
        knop3.setActionCommand("knop3a");
        knop4.setActionCommand("knop4a");

        knop1.addHActionListener(this);
        knop2.addHActionListener(this);
        knop3.addHActionListener(this);
        knop4.addHActionListener(this);
        
        knop1.requestFocus();
        
        
        
        scene.validate(); scene.setVisible(true);
    }   


    public void startXlet() throws XletStateChangeException {

     int max = 4;
     int min = 1;
     int randomGetal;
        
     for (int i=0; i<10; i++)
        {
            randomGetal = min + (int)(Math.random() * max);
            System.out.println(randomGetal);
        }
    }
    
    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}