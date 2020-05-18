
import details.*;

/**
 *
 * @author bratizgut
 */
public class Worker implements Runnable {

    private final Storage<Body> bodyStorage;
    private final Storage<Engine> engineStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    public Worker(Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage,
            Storage<Car> carStorage) {
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
    }

    @Override
    public void run() {
        Body body;
        Engine engine;
        Accessory accessory1;
        Accessory accessory2;

        try {
            body = (Body) bodyStorage.get();
            engine = (Engine) engineStorage.get();
            accessory1 = (Accessory) accessoryStorage.get();
            accessory2 = (Accessory) accessoryStorage.get();
        } catch (InterruptedException e) {
            return;
        }
        try {
            Car car = new Car(body, engine, accessory1, accessory2);
            carStorage.add(car);
        } catch (InterruptedException e) {
            return;
        }
    }

}
