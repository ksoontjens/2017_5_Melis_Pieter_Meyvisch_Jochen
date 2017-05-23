package hellotvxlet;

import javax.tv.xlet.*;
import java.awt.event.*;
import org.havi.ui.event.*;
import org.dvb.ui.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import java.util.Random;
import java.util.Timer;
import org.dvb.event.*;


public class HelloTVXlet implements Xlet, HActionListener, UserEventListener {
        
    
        //////////////////////////////////////////////
        ArrayList lijst=new ArrayList();
        int display=0;
        boolean running=false;
        HTextButton knop1;
        HTextButton knop2;
        HTextButton knop3;
        HTextButton knop4;
        HScene scene;
        //////////////////////////////////////////////
        
        String color;
    
        ArrayList gameArray=new ArrayList();
        ArrayList guessArray=new ArrayList();
        ArrayList clrArr=new ArrayList();
        String[] colorsArray = {"red","blue","green","yellow"};
        private int max = 3;
        private int min = 0;
        Random rn = new Random();
        String inputAsk = "false";
        String inputCheck = "false";
        String inputGiven = "false";
        
    public HelloTVXlet() {
        
    }
    
    public void initXlet(XletContext context) {
      // knoppen en scene zetten
        ////////////////////////////////////////////////////////////////////////
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
        ////////////////////////////////////////////////////////////////////////
        scene.validate(); scene.setVisible(true);
    }
    
    public void newRound()
    {
        int randomNbr = rn.nextInt(4) + 0;//min + (int)(Math.random() * max);
        Integer nbr=new Integer(randomNbr);
        gameArray.add(nbr);
    }
    
    public void showSequence() {
        
        /*
        int max = 4;
        int min = 1;
        int  randomGetal;
        
        for (int i=0; i<10; i++)
        {
            
            randomGetal = min + (int)(Math.random() * max);
            Integer in=new Integer(randomGetal);
            lijst.add(in);
            System.out.println(randomGetal);
             
            System.out.println(gameArray.get(i));
        }
        
        Timer t=new Timer();
        MijnTimerTask mtt=new MijnTimerTask(this);
        t.scheduleAtFixedRate(mtt, 0, 1000); // elke seconde
        running=true;
        */
        int gameLength = gameArray.size();

        for (int i=0; i<gameLength; i++)
        {
            System.out.println(gameArray.get(i)); 
        }
        
        Timer t=new Timer();
        MijnTimerTask mtt=new MijnTimerTask(this);
        t.scheduleAtFixedRate(mtt, 0, 1000);
        running=true;
         // elke seconde
        
        
    }
    
    public void guessSequence()
    {
        int gameLength = gameArray.size();
        guessArray = new ArrayList();
        for (int i=0; i<gameLength; i++)
        {
            inputAsk = "true";
            
            if (inputCheck == "true")
            {
                System.out.println("///////////////////////////////////////////////////////////////////////////");
                int randomNbr = rn.nextInt(4) + 0;
                String ask = colorsArray[randomNbr];// = en dan user input krijgen
                guessArray.add(ask);
                System.out.println(ask);
                inputAsk = "false";
                System.out.println("ïnputAsk false");
                int guessLength = guessArray.size();
                if ((guessLength == gameLength))
                {
                    inputGiven = "true";
                }
            }
        }
        
    }

    

    public void startXlet() throws XletStateChangeException {
        
        
        
        ////////////////////////////////////////////////////////////////////////
        /*int max = 4;
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
         running=true;*/
        ////////////////////////////////////////////////////////////////////////
        
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
        // Bekend maken bij EventManager
        manager.addUserEventListener(this, repository);
        
        String gameOver = "false";
        do
        {
           newRound();
           showSequence();
           guessSequence();
           if (inputGiven == "true")
           {
               int gameLength = gameArray.size();

               boolean result = Arrays.equals(gameArray.toArray(), guessArray.toArray());
               if(result == true)
               {
                   System.out.println("Juist!");
               }
               else
               {
                   System.out.println("Fout, game over!");
                   gameOver = "true";
               }
           }
           
        }
        while(gameOver == "false");
    
    }
    
    public void callback()
    {
        if (running)
        {
        display++;
        int r=((Integer)gameArray.get(display)).intValue();
        System.out.println("index="+display+" waarde="+r);
        if (r==1) knop1.requestFocus();
        if (r==2) knop2.requestFocus();
        if (r==3) knop3.requestFocus();
        if (r==4) knop4.requestFocus();
        //scene.repaint();
        }
        /*
        if (running)
        {
        int gameLength = gameArray.size();
        
        //for (int i=0; i<gameLength; i++)
        //{
            //String r = mt(i);//get(i));
            int r=((Integer)lijst.get(display)).intValue();
            
            String rToString;
            if (r==1) rToString = colorsArray[0];
            if (r==2) rToString = colorsArray[1];
            if (r==2) rToString = colorsArray[2];
            if (r==3) rToString = colorsArray[3];
            if (rToString=="red") knop1.requestFocus();
            if (rToString=="blue") knop2.requestFocus();
            if (rToString=="green") knop3.requestFocus();
            if (rToString=="yellow") knop4.requestFocus();
        //}
        
        //scene.repaint();
        }*/
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
    
    public void userEventReceived(org.dvb.event.UserEvent e) {
        if (inputAsk == "true") {
            if (e.getType() == KeyEvent.KEY_PRESSED) {
                inputCheck = "true";
                System.out.println("ïnputCheck");
                System.out.println ( "Pushed Button" );
                switch( e.getCode ()) {
                    case HRcEvent.VK_UP:
                        System.out.println("VK_UP is PRESSED");
                        inputCheck = "false";
                        break;
                    case HRcEvent.VK_DOWN:
                        System.out.println("VK_DOWN is PRESSED");
                        inputCheck = "false";
                        break;
                    case HRcEvent.VK_LEFT:
                        System.out.println("VK_LEFT is PRESSED");
                        inputCheck = "false";
                        break;
                    case HRcEvent.VK_RIGHT:
                        System.out.println("VK_RIGHT is PRESSED");
                        inputCheck = "false";
                        break;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
        
    }
}
