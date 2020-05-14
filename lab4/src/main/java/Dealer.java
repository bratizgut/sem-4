
import details.Car;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bratizgut
 */
public class Dealer extends Thread {
    
    private static final Logger LOG = Logger.getLogger(Dealer.class.getName());

    private final Storage<Car> carStorage;
    private final int waitTime;
    private final int ID;
    
    public Dealer(Storage<Car> carStorage, int waitTime, int ID) {
        this.carStorage = carStorage;
        this.waitTime = waitTime;
        this.ID = ID;
    }
    
    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                Car car = (Car) carStorage.get();
                LOG.log(Level.INFO, "{0} {1}\n", new Object[]{ID, car.getID()});
            } catch (InterruptedException e) {
                break;
            }
            try {
                sleep(waitTime);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
    
}
