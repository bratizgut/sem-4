
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author bratizgut
 */
public class Main {

    public static void main(String[] args) {
        
        try {
            InputStream stream = Main.class.getResourceAsStream("log.config");
            if (stream == null) {
                throw new FileNotFoundException();
            }
            LogManager.getLogManager().readConfiguration(stream);
        } catch (IOException | SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, "Coul nor open log.config");
        }
        
        Properties properties = new Properties();
        try {
            InputStream stream = Main.class.getResourceAsStream("settings.properties");
            if (stream == null) {
                throw new FileNotFoundException();
            }
            properties.load(stream);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Factory factory = new Factory(Integer.parseInt(properties.getProperty("engineStorageCapacity")), Integer.parseInt(properties.getProperty("engineSupplierWaitTime")),
                                      Integer.parseInt(properties.getProperty("bodyStorageCapacity")), Integer.parseInt(properties.getProperty("bodySupplierWaitTime")),
                                      Integer.parseInt(properties.getProperty("accessorySupplierAmount")), Integer.parseInt(properties.getProperty("accessoryStorageCapacity")), Integer.parseInt(properties.getProperty("accessorySupplierWaitTime")),
                                      Integer.parseInt(properties.getProperty("workerAmount")), Integer.parseInt(properties.getProperty("carStorageCapacity")),
                                      Integer.parseInt(properties.getProperty("dealerAmount")), Integer.parseInt(properties.getProperty("dealerWaitTime")));
        factory.factoryStart();

        Scanner in = new Scanner(System.in);

        in.nextLine();

        factory.factoryStop();
    }
}
