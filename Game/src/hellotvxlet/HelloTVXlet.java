package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.event.*;
import org.dvb.event.*;
import org.havi.ui.event.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Random;
import java.util.Timer;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventRepository;



public class HelloTVXlet implements Xlet, HActionListener {

    
    ArrayList    lijst=new ArrayList();
    int display=0;
    boolean running=false;
    HTextButton knop1;
    HTextButton knop2;
    HTextButton knop3;
    HTextButton knop4;
    HScene scene;
    
    public HelloTVXlet() {
        
    } 
    
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        
         scene=HSceneFactory.getInstance().getDefaultHScene();
        
        knop1=new HTextButton("knop1",300,100,100,100);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop1.setBackground(Color.RED);
        scene.add(knop1);
        
         knop2=new HTextButton("knop2",200,200,100,100);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop2.setBackground(Color.BLUE);
        scene.add(knop2);
        
        knop3=new HTextButton("knop3",100,100,100,100);
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop3.setBackground(Color.GREEN);
        scene.add(knop3);
        
         knop4=new HTextButton("knop4",200,0,100,100);
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
        /*
     System.out.println("Gestart");
     EventManager manager = EventManager.getInstance();
     UserEventRepository repository = new UserEventRepository( "Voorbeeld" );
     repository.addKey(org.havi.ui.event.HRcEvent.VK_UP);
     repository.addKey(org.havi.ui.event.HRcEvent.VK_RIGHT);
     repository.addKey(org.havi.ui.event.HRcEvent.VK_DOWN);
     repository.addKey(org.havi.ui.event.HRcEvent.VK_LEFT);
     manager.addUserEventListener( (UserEventListener) this,repository);
     */
     
    
     int max = 4;
     int min = 1;
     int  randomGetal;


        
     for (int i=0; i<100; i++)
        {
            randomGetal = min + (int)(Math.random() * max);
            Integer in=new Integer(randomGetal);
            lijst.add(in);
            System.out.println(randomGetal);
        }
     
     Timer t=new Timer();
     MijnTimerTask mtt=new MijnTimerTask(this);
     t.scheduleAtFixedRate(mtt, 0, 1000); // elke seconde
     running=true;
    }
    
    public void callback()
    {
        if (running)
        {
        display++;
        int r=((Integer)lijst.get(display)).intValue();
        System.out.println("index="+display+" waarde="+r);
        if (r==1) knop1.requestFocus();
        if (r==2) knop2.requestFocus();
        if (r==3) knop3.requestFocus();
        if (r==4) knop4.requestFocus();
        //scene.repaint();
        }
    }
    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    /*
    public void userEventReceived(org.dvb.event.UserEvent e) {
        if (e.getType() == KeyEvent.KEY_PRESSED) {
            System.out.println ( "Pushed Button" );
            switch( e.getCode ()) {
                case HRcEvent.VK_UP:
                    System.out.println("VK_UP is PRESSED");
                    break;
                case HRcEvent.VK_DOWN:
                    System.out.println("VK_DOWN is PRESSED");
                    break;
                case HRcEvent.VK_LEFT:
                    System.out.println("VK_LEFT is PRESSED");
                    break;
                case HRcEvent.VK_RIGHT:
                    System.out.println("VK_RIGHT is PRESSED");
                    break;
            }
        }
    }*/
    

}