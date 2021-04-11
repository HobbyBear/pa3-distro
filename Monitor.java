import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor {
    /*
     * ------------
     * Data members
     * ------------
     */

    private boolean exitSomeBodySay;

    private int chopsticksNum;

    private int numOfPhilosophers;

    private Object pickObj;


    /**
     * Constructor
     */
    ///////////////////////我鞋底////////////////////
    public Monitor(int piNumberOfPhilosophers) {
        // TODO: set appropriate number of chopsticks based on the # of philosophers
        numOfPhilosophers = piNumberOfPhilosophers;
        pickObj = new Object();
    }

    /*
     * -------------------------------
     * User-defined monitor procedures
     * -------------------------------
     */

    /**
     * Grants request (returns) to eat when both chopsticks/forks are available.
     * Else forces the philosopher to wait()
     */
    ///////////////////////我鞋底////////////////////
    public synchronized void pickUp(final int piTID) {
        // ...
        synchronized (pickObj){
            if (chopsticksNum == numOfPhilosophers){
                try {
                    pickObj.wait();
                    chopsticksNum++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * When a given philosopher's done eating, they put the chopstiks/forks down
     * and let others know they are available.
     */
    ///////////////////////我鞋底////////////////////
    public synchronized void putDown(final int piTID) {
        // ...
        synchronized (pickObj){
            chopsticksNum --;
            pickObj.notify();
        }
    }

    /**
     * Only one philopher at a time is allowed to philosophy
     * (while she is not eating).
     */
    ///////////////////////我鞋底////////////////////
    public synchronized void requestTalk() {
        // ...
        if (exitSomeBodySay){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        exitSomeBodySay = true;
    }

    /**
     * When one philosopher is done talking stuff, others
     * can feel free to start talking.
     */
    ///////////////////////我鞋底////////////////////
    public synchronized void endTalk() {
        // ...
        exitSomeBodySay = false;
        this.notify();
    }
}

// EOF
