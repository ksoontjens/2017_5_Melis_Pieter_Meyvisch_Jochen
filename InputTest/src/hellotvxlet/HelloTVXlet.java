package hellotvxlet;

import javax.tv.xlet.*;
import org.havi.ui.*;
import java.awt.event.*;
import org.havi.ui.event.*;
import org.dvb.event.*;

public class HelloTVXlet implements Xlet,UserEventListener {
    
    public void initXlet(XletContext xletContext) throws XletStateChangeException {
    }
    
    public void startXlet () throws XletStateChangeException
    {
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
    manager.addUserEventListener(this, repository);
    }
    
    public void pauseXlet() {
    }
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }
    // Opvangen van de Key Events
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
    }
}