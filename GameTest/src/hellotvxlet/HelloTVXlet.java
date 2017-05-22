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


public class HelloTVXlet implements Xlet, HActionListener {
    
        ArrayList gameArray=new ArrayList();
        ArrayList guessArray=new ArrayList();
        String[] colorsArray = {"yellow","blue","green","red"};
        private int max = 3;
        private int min = 0;
        Random rn = new Random();
        
    public HelloTVXlet() {
        
    }
    
    public void initXlet(XletContext context) {
      // knoppen en scene zetten
    }
    
    public void newRound()
    {
        int randomNbr = rn.nextInt(4) + 0;//min + (int)(Math.random() * max);
        gameArray.add(colorsArray[randomNbr]);
    }
    
    public void showSequence()
    {
        int gameLength = gameArray.size();
        
        for (int i=0; i<gameLength; i++)
        {
            System.out.println(gameArray.get(i));
        }
    }
    
    public void guessSequence()
    {
        int gameLength = gameArray.size();
        guessArray = new ArrayList();
        for (int i=0; i<gameLength; i++)
        {
            
            int randomNbr = rn.nextInt(4) + 0;
            String ask = colorsArray[randomNbr];// = en dan user input krijgen
            guessArray.add(ask);
            System.out.println(ask);
        }
    }

    

    public void startXlet() throws XletStateChangeException {
        String gameOver = "false";
        do
        {
           newRound();
           showSequence();
           guessSequence();
           
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
        while(gameOver == "false");
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
        
    }
}
