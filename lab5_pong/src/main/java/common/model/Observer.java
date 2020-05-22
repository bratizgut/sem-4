package server.model;
/**
 *
 * @author bratizgut
 */
public interface Observer {
    void handleEvent(Observable o);
}
