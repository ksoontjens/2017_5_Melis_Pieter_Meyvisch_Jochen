package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.event.*;
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
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;



public class HelloTVXlet implements Xlet, HActionListener, UserEventListener {

     int max = 4;
     int min = 1;
     int  randomGetal;
    ArrayList    lijst=new ArrayList();
    ArrayList    playerlijst=new ArrayList();
    int display=0;
    boolean running=false;
    HTextButton knop1;
    HTextButton knop2;
    HTextButton knop3;
    HTextButton knop4;
    HScene scene;
    int toestand=0;
    ArrayList userList=new ArrayList();
    
    public HelloTVXlet() {
        
    } 
    
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        
        scene=HSceneFactory.getInstance().getDefaultHScene();
        
        knop1=new HTextButton("rood",300,100,100,100);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop1.setBackground(Color.RED);
        scene.add(knop1);
        
        knop2=new HTextButton("blauw",200,200,100,100);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop2.setBackground(Color.BLUE);
        scene.add(knop2);
        
        knop3=new HTextButton("groen",300,300,100,100);
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop3.setBackground(Color.GREEN);
        scene.add(knop3);
        
        knop4=new HTextButton("geel",400,200,100,100);
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop4.setBackground(Color.YELLOW);
        scene.add(knop4);
        
        knop1.setFocusTraversal(knop4, knop2, knop3, null);
        knop2.setFocusTraversal(knop4, null, knop3, knop1);
        knop3.setFocusTraversal(knop4, knop2, null, knop1);
        knop4.setFocusTraversal(null, knop2, knop3, knop1);
        
        knop1.setActionCommand("1");
        knop2.setActionCommand("2");
        knop3.setActionCommand("3");
        knop4.setActionCommand("4");

        knop1.addHActionListener(this);
        knop2.addHActionListener(this);
        knop3.addHActionListener(this);
        knop4.addHActionListener(this);
        
        knop1.requestFocus();
        
        
        
        scene.validate(); scene.setVisible(true);
        toestand=State.COMPUTERDISPLAY;
    }   


    public void startXlet() throws XletStateChangeException {
    
            System.out.println("Xlet gestart");
    // EventManager aanvragen
    EventManager manager = EventManager.getInstance();
    // Repository
    UserEventRepository repository = new UserEventRepository("Voorbeeld");
    // Events toevoegen
    repository.addKey(org.havi.ui.event.HRcEvent.VK_UP);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_RIGHT);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_DOWN);
    repository.addKey(org.havi.ui.event.HRcEvent.VK_LEFT);
    // Bekend maken b i j EventManager
    // niet nodig:
  //  manager.addUserEventListener(this, repository);
        
     for (int i=0; i<=10; i++)
        {
            addRandom();
        }
     
     Timer t=new Timer();
     MijnTimerTask mtt=new MijnTimerTask(this);
     t.scheduleAtFixedRate(mtt, 0, 1000); // elke seconde
     running=true;
    }
    
    public void callback()
    {
        if (!running) return; // einde functie
        int vtoestand=toestand; // volgende toestand
        switch (toestand)
        {
            case State.COMPUTERDISPLAY:
                    int r=((Integer)lijst.get(display)).intValue();
                    System.out.println("index="+display+" waarde="+r);
                    if (r==1) knop1.requestFocus();
                    if (r==2) knop2.requestFocus();
                    if (r==3) knop3.requestFocus();
                    if (r==4) knop4.requestFocus();
                   // scene.repaint();
                   display++;
                       if (display==lijst.size())
                    {
                        System.out.println("EINDE LIJST BEREIKT");
                        vtoestand=State.USERPLAY;
                    }
                break;
                
            case State.USERPLAY:
                
                break;
                
        }
        toestand=vtoestand;
 
    }
    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent arg0) {
        
        userList.add(arg0.getActionCommand());
        if (userList.size()==lijst.size())
        {
        System.out.println("Controleer of alles klopt");
        boolean allesklopt=true;
        for (int i=0;i<lijst.size();i++)
        {
            System.out.print((Integer)lijst.get(i));
            System.out.print("=?=");
            System.out.println((String)userList.get(i));
            int user=Integer.parseInt((String)userList.get(i));
            
            if (   ((Integer)lijst.get(i)).intValue()
                    !=user )
            {
                allesklopt=false;
            }
        }
        
    }
    }
    public void addRandom(){
            randomGetal = min + (int)(Math.random() * max);
            Integer in=new Integer(randomGetal);
            lijst.add(in);
            System.out.println(randomGetal);
    }
        public void userEventReceived(org.dvb.event.UserEvent e) {
        if (e.getType() == KeyEvent.KEY_PRESSED) {
            System.out.println ( "Pushed Button" );
            switch( e.getCode ()) {
                case HRcEvent.VK_UP:
                    System.out.println("VK_UP is PRESSED");
                    playerlijst.add("1");
                    knop1.requestFocus();
                    System.out.println(playerlijst);
                    break;
                case HRcEvent.VK_DOWN:
                    System.out.println("VK_DOWN is PRESSED");
                    playerlijst.add("3");
                    knop3.requestFocus();
                    System.out.println(playerlijst);
                    break;
                case HRcEvent.VK_LEFT:
                    System.out.println("VK_LEFT is PRESSED");
                    playerlijst.add("2");
                    knop2.requestFocus();
                    System.out.println(playerlijst);
                    break;
                case HRcEvent.VK_RIGHT:
                    System.out.println("VK_RIGHT is PRESSED");
                    playerlijst.add("4");
                    knop4.requestFocus();
                    System.out.println(playerlijst);
                    break;
            }
        }
    }
}