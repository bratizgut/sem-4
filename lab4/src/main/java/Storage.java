
import details.Detail;
import java.util.ArrayList;

/**
 *
 * @author bratizgut
 * @param <T>
 */
public class Storage<T extends Detail> {

    private final ArrayList<T> details;
    private final int capacity;
    
    private Controller controller;

    public Storage(int capacity) {
        this.capacity = capacity;
        this.controller = null;
        details = new ArrayList<>();
    }
    
    public void addController(Controller controller) {
        this.controller = controller;
    }

    public synchronized void add(T detail) throws InterruptedException {
        while (true) {
            if (details.size() < capacity) {
                details.add(detail);
                notify();
                return;
            }
            wait();
        }
    }

    public synchronized Detail get() throws InterruptedException {
        if(controller != null){
            controller.notifyController();
        }
        while (true) {
            if (details.size() > 0) {
                T detail = details.get(0);
                details.remove(0);
                notify();
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
