    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimerTask extends TimerTask {

    HelloTVXlet xlet;
    
    public MijnTimerTask(HelloTVXlet x)
    {
        xlet=x;
    }
    public void run() {
        xlet.callback();
    }

}
