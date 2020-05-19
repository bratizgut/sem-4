
import details.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bratizgut
 */
public class Factory {

    private final Storage<Engine> engineStorage;
    private final Storage<Body> bodyStorage;
    private final Storage<Accessory> accessoryStorage;
    private final Storage<Car> carStorage;

    private final Supplier<Engine> engineSupplier;
    private final Supplier<Body> bodySupplier;
    private final ArrayList<Supplier<Accessory>> accessorySuppliers;

    private final Controller controller;

    private final ArrayList<Dealer> dealers;

    public Factory(int eCapacity, int eWait, int bCapacity, int bWait, int aNum, int aCapacity, int aWait, int wNum, int cCapacity, int dNum, int dWait) {
        engineStorage = new Storage<>(eCapacity);
        engineSupplier = new Supplier<>(engineStorage, Engine.class, eWait);

        bodyStorage = new Storage<>(bCapacity);
        bodySupplier = new Supplier<>(bodyStorage, Body.class, bWait);

        accessoryStorage = new Storage<>(eCapacity);
        accessorySuppliers = new ArrayList<>();
        for (int i = 0; i < aNum; i++) {
            accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class, aWait));
        }

        carStorage = new Storage<>(cCapacity);
        controller = new Controller(engineStorage, bodyStorage, accessoryStorage, carStorage, wNum);

        carStorage.addObserver(controller);

        dealers = new ArrayList<>();
        for (int i = 0; i < dNum; i++) {
            dealers.add(new Dealer(carStorage, dWait * (i + 1), i));
        }
    }

    public void factoryStart() {
        engineSupplier.start();
        bodySupplier.start();
        for (Supplier<Accessory> i : accessorySuppliers) {
            i.start();
        }
        for (Dealer i : dealers) {
            i.start();
        }
        
        controller.start();
    }

    public void factoryStop() {

        engineSupplier.interrupt();
        bodySupplier.interrupt();
        for (Supplier<Accessory> i : accessorySuppliers) {
            i.interrupt();
        }
        for (Dealer i : dealers) {
            i.interrupt();
        }
        controller.stop();

        try {
            engineSupplier.join();
            bodySupplier.join();
            for (Supplier<Accessory> i : accessorySuppliers) {
                i.join();
            }
            for (Dealer i : dealers) {
                i.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Factory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Factory succesfully ended work.");

    }
}