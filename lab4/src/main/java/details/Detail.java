package details;

import java.util.UUID;

/**
 *
 * @author bratizgut
 */
public class Detail {
    private final String ID;

    public Detail() {
        ID = UUID.randomUUID().toString();
    }

    public String getID() {
        return ID;
    }
    
}
