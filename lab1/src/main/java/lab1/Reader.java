package lab1;

/**
 *
 * @author bratizgut
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Reader {
    static public String[] readLine(BufferedReader reader){
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Converter.convert(str);
    }
}
