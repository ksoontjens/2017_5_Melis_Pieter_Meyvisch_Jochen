package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.event.*;
import org.dvb.event.UserEvent;
import org.havi.ui.event.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Timer;
import org.dvb.event.EventManager;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;



public class HelloTVXlet implements Xlet, HActionListener, UserEventListener {

     int max = 4;
     int min = 1;
     int  randomGetal;
    ArrayList lijst = new ArrayList();
    ArrayList playerlijst=new ArrayList();
    int display=0;
    boolean running=false;
    HTextButton knop1;
    HTextButton knop2;
    HTextButton knop3;
    HTextButton knop4;
    HTextButton knop5;
    HTextButton end;
    HScene scene;
    int toestand=0;
    ArrayList userList=new ArrayList();
    String slijst = "";
    String splayerlijst = "";
    String[] colors = {"Rood","Blauw","Groen","Geel"};
    
    public HelloTVXlet() {
        
    } 
    
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        
        scene=HSceneFactory.getInstance().getDefaultHScene();
        
        knop1=new HTextButton("rood",250,0,200,200);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop1.setBackground(Color.RED);
        scene.add(knop1);
        
        knop2=new HTextButton("blauw",50,200,200,200);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop2.setBackground(Color.BLUE);
        scene.add(knop2);
        
        knop3=new HTextButton("groen",250,400,200,200);
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop3.setBackground(Color.GREEN);
        scene.add(knop3);
        
        knop4=new HTextButton("geel",450,200,200,200);
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop4.setBackground(Color.YELLOW);
        scene.add(knop4);
        
        knop5=new HTextButton("",250,200,200,200);
        knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop5.setBackground(Color.BLACK);
        scene.add(knop5);
        
        knop1.setFocusTraversal(null, knop3, knop2,knop4 );
        knop2.setFocusTraversal(knop1, knop3, null, knop4);
        knop3.setFocusTraversal(knop1, null, knop2, knop4);
        knop4.setFocusTraversal(knop1, knop3, knop2, null);
        
        knop1.setActionCommand("1");
        knop2.setActionCommand("2");
        knop3.setActionCommand("3");
        knop4.setActionCommand("4");

        knop1.addHActionListener(this);
        knop2.addHActionListener(this);
        knop3.addHActionListener(this);
        knop4.addHActionListener(this);
        
        knop1.requestFocus();
        

        scene.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.setBackground(Color.BLACK);
        
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
    
    manager.addUserEventListener(this, repository);
        

     
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
                
                    
                    addRandom();
                    
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
                   
                if(lijst.size() == playerlijst.size()){
                
                for (int i=0; i< lijst.size(); i++)
                {
                    slijst += lijst.get(i);
                }
                for (int i=0; i< playerlijst.size(); i++)
                {
                    splayerlijst += playerlijst.get(i);
                }
                
                if (slijst.equals(splayerlijst))
                {
                    System.out.println("gelijk");
                    knop5.setBackground(Color.GREEN); 
                    playerlijst.clear();
                    vtoestand=State.COMPUTERDISPLAY;
                }
                else
                {
                    knop5.setBackground(Color.RED); 
                    System.out.println("niet gelijk");  
                    vtoestand=State.USERPLAY;
                }
                
                
                    // VORIGE CODE //
                    /*
                    for (int i = 0; i < lijst.size(); i++) {
                        for (int j = 0; j < playerlijst.size(); j++) {

                          System.out.println(playerlijst.get(j));
                          System.out.println(lijst.get(i));

                          System.out.println("lijst = "+lijst);
                          System.out.println("player = "+playerlijst);
                        
                    if(lijst.get(i).toString() == playerlijst.get(j).toString()){
                        
                        System.out.println("Print v lijst: " + lijst.get(i).toString());
                        System.out.println("Print v playerlijst: " + playerlijst.get(j).toString());
                        
                        System.out.println("gelijk");
                        knop5.setBackground(Color.GREEN); 
                        playerlijst.clear();
                        j=0;
                        vtoestand=State.COMPUTERDISPLAY;
                       
                    }else{
                          
                        knop5.setBackground(Color.RED); 
                        System.out.println("niet gelijk");  
                        vtoestand=State.USERPLAY;

                    }
                    }
                    }*/
                     
                   }
                
                
                
                
                   
                
                
                break;
                
        }
        toestand=vtoestand;
 
    }
    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent arg0) {
        
        playerlijst.add(arg0.getActionCommand());
        if (playerlijst.size()==lijst.size())
        {
        System.out.println("Controleer of alles klopt");
        boolean allesklopt=true;
        for (int i=0;i<lijst.size();i++)
        {
            System.out.print((Integer)lijst.get(i));
            System.out.print("=?=");
            System.out.println((String)playerlijst.get(i));
            int user=Integer.parseInt((String)playerlijst.get(i));
            
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
            System.out.println(colors[randomGetal-1]); //Print de kleur ipv randomgetal
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