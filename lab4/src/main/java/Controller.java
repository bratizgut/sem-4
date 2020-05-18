
import details.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bratizgut
 */
public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class.getName());

    private final Storage<Engine> engineStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Accessory> accessoryStorage;

    private final Storage<Car> carStorage;
    ThreadPoolExecutor workers;

    public Controller(Storage<Engine> engineStorage, Storage<Body> bodyStorage, Storage<Accessory> accessoryStorage, Storage<Car> carStorage, int workersNum) {
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.accessoryStorage = accessoryStorage;

        this.carStorage = carStorage;
        workers = (ThreadPoolExecutor) Executors.newFixedThreadPool(workersNum);
    }

    public void stop() {
        workers.shutdownNow();
    }

    public synchronized void notifyController() {
        LOG.log(Level.INFO, "Made: {0} Queue: {1}", new Object[]{workers.getCompletedTaskCount(), workers.getQueue().size()});
        while ((carStorage.getDetailsNum() + workers.getQueue().size()) < 0.4 * carStorage.getCapacity()) {
            workers.execute(new Worker(bodyStorage, engineStorage, accessoryStorage, carStorage));
        }
    }

}
