
import details.Detail;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author bratizgut
 * @param <T>
 */
public class Storage<T extends Detail> extends Observable {

    private final ArrayList<T> details;
    private final int capacity;

    public Storage(int capacity) {
        this.capacity = capacity;
        details = new ArrayList<>();
    }

    public synchronized void add(T detail) throws InterruptedException {
        while (true) {
            if (details.size() < capacity) {
                details.add(detail);
                notify();
                setChanged();
                notifyObservers(null);
                return;
            }
            wait();
        }
    }

    public synchronized Detail get() throws InterruptedException {
        while (true) {
            if (details.size() > 0) {
                T detail = details.get(0);
                details.remove(0);
                notify();
                setChanged();
                notifyObservers(this);
                return detail;
            }
            wait();
        }
    }

    public int getDetailsNum() {
        return details.size();
    }

    public int getCapacity() {
        return capacity;
    }

}
