/*
 */
package fileutils;

import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author leonardoho
 */
public class FileUtils {
    public static int EOF_FLAG = 65535;
    
    private static String tmpBuffer = "";
    
    public static char getNextChar(FileInputStream stream) throws IOException{
        if (tmpBuffer.length() == 0)
            return (char) stream.read();
        else{
            char lastChar = tmpBuffer.charAt(tmpBuffer.length() -1);
            tmpBuffer = tmpBuffer.substring(0, tmpBuffer.length() -1);
            return lastChar;
        }
    }
    
    public static void unGetChar(char c){
        tmpBuffer = tmpBuffer.concat(String.valueOf(c));
    }
    
    public static boolean isSpace(char c){
        return (c == ' ' || c == '\n' || c == '\t' || c == '\r');
    }
    
    public static boolean isAlphanumeric(char c){
        return (String.valueOf(c).matches("[A-Za-z0-9]+"));
    }
    
}
