package lab1;

/**
 *
 * @author bratizgut
 */

class Converter {
    public static String[] convert(String str){
        if(str != null){
            String newStr = str.toLowerCase();
            String buffer = newStr.replaceAll("[^a-z0-9]", " ");
            String buf[] = buffer.split(" ");
            return buf;
        } else {
            return null;
        }
    }
}