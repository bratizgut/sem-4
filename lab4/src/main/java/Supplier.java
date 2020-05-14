
import details.Detail;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bratizgut
 * @param <T>
 */
public class Supplier<T extends Detail> extends Thread {

    private final Storage<T> storage;
    private final Class<T> detailClass;
    private final int waitTime;

    public Supplier(Storage<T> storage, Class<T> detailClass, int waitTime) {
        this.storage = storage;
        this.detailClass = detailClass;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        while(!this.isInterrupted()) {
            try {
                T detail = detailClass.newInstance();
                try {
                    storage.add(detail);
                } catch (InterruptedException ex) {
                    break;
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.sleep(waitTime);
            } catch (InterruptedException ex) {
                    break;
            }
        }
    }

}
