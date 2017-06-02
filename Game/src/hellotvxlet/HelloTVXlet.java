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
import org.dvb.ui.DVBColor;



public class HelloTVXlet implements Xlet, HActionListener {

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
    int startlen=3;
    DVBColor DARKRED=new DVBColor(150,0,0,255);
    DVBColor DARKGREEN=new DVBColor(0,150,0,255);
    DVBColor DARKYELLOW=new DVBColor(150,150,0,255);
    DVBColor DARKBLUE=new DVBColor(0,0,150,255);
    
    public HelloTVXlet() {
        
    } 
    
    public void initXlet(XletContext ctx) throws XletStateChangeException {
        
        scene=HSceneFactory.getInstance().getDefaultHScene();
        
        knop1=new HTextButton("rood",250,0,200,200);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop1.setBackground(DARKRED);
        knop1.setTextContent("ROOD", HVisible.FOCUSED_STATE);
       
        scene.add(knop1);
        
        knop2=new HTextButton("blauw",50,200,200,200);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop2.setBackground(DARKBLUE);
        scene.add(knop2);
                knop2.setTextContent("BLAUW", HVisible.FOCUSED_STATE);
        knop3=new HTextButton("groen",250,400,200,200);
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop3.setBackground(DARKGREEN);
        scene.add(knop3);
                knop3.setTextContent("GROEN", HVisible.FOCUSED_STATE);
        knop4=new HTextButton("geel",450,200,200,200);
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop4.setBackground(DARKYELLOW);
        scene.add(knop4);
                knop4.setTextContent("GEEL", HVisible.FOCUSED_STATE);
        knop5=new HTextButton("",250,200,200,200);
        knop5.setBackgroundMode(HVisible.BACKGROUND_FILL);
        knop5.setBackground(Color.BLACK);
        scene.add(knop5);
        
        knop1.setFocusTraversal(null, knop3, knop2,knop4 );
        knop2.setFocusTraversal(knop1, knop3, null, knop4);
        knop3.setFocusTraversal(knop1, null, knop2, knop4);
        knop4.setFocusTraversal(knop1, knop3, knop2, null);
        knop5.setFocusTraversal(knop1, knop3, knop2, knop4);
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
    for (int i=0;i<startlen;i++)
    {
    addRandom();
        
    }
//    manager.addUserEventListener(this, repository);
        

     
     Timer t=new Timer();
     MijnTimerTask mtt=new MijnTimerTask(this);
     t.scheduleAtFixedRate(mtt, 0, 1000); // elke seconde
     running=true;
    }
    public void clearColors()
    {
                        knop1.setBackground(DARKRED);
                knop2.setBackground(DARKBLUE);
                
                knop3.setBackground(DARKGREEN);
                
                knop4.setBackground(DARKYELLOW);
    }
    public void callback()
    {
        if (!running) return; // einde functie
        int vtoestand=toestand; // volgende toestand
        switch (toestand)
        {
            case State.COMPUTERWAIT:
                    clearColors();
                scene.repaint();
                vtoestand=State.COMPUTERDISPLAY;
                
                      if (display==lijst.size())
                    {
                        System.out.println("EINDE LIJST BEREIKT");
                        vtoestand=State.USERPLAY;
                        knop5.requestFocus();
                        userList.clear();
                    }
                 
                break;
            case State.COMPUTERDISPLAY:
                System.out.println("State.COMPUTERPLAY display="+display);
                    
                    
                    
                    int r=((Integer)lijst.get(display)).intValue();
                    System.out.println("index="+display+" waarde="+r);
                    if (r==1) { knop1.requestFocus(); knop1.setBackground(Color.RED); }
                    if (r==2)  { knop2.requestFocus(); knop2.setBackground(Color.BLUE); }
                    if (r==3) { knop3.requestFocus();knop3.setBackground(Color.GREEN);} 
                    if (r==4) { knop4.requestFocus(); knop4.setBackground(Color.YELLOW); } 
                    
                   scene.repaint();
                   display++;
                   vtoestand=State.COMPUTERWAIT;
                 
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
                
                    addRandom();
                    display=0;
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
        clearColors();
        playerlijst.add(arg0.getActionCommand());
        
        // kleur hoog zetten actief zetten
        
        if (arg0.getActionCommand().equals("1"))
        {
            knop1.setBackground(Color.RED);
        }
        
        scene.repaint();
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
  
    /*public void userEventReceived(org.dvb.event.UserEvent e) {
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
            }*/
    


}