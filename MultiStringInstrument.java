xcimport java.util.*;
/**
 * MultiStringInstrument generates a keyboard of strings to be played by the user.
 * 
 * @author Zach Souser 
 * @version Sept 6 2012
 */
public class MultiStringInstrument implements Instrument {

    // The keyboard of characters
    private final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    // array of keys
    private char[] keys;
    // array of corresponding strings
    private SteelString[] strings;
    
    /**
     * Constructor for objects of class MultiStringInstrument
     * 
     * Iterates through the keyboard string populating the keys and strings arrays
     */
    public MultiStringInstrument() {
       keys = new char[37];
       strings = new SteelString[37];
       for (int i = 0; i < KEYBOARD.length(); i++) {
            keys[i] = (char)KEYBOARD.charAt(i);
            strings[i] = new SteelString(440 * Math.pow(2, (i-24)/12.0));
       }
    }
    
    /**
     * hasString iterates through the keys array searching for the specified string
     */

    public boolean hasString(char string) {
       for (int i = 0; i < keys.length; i++) {
           if (keys[i] == string) return true;
       }
       return false;
    }

    /**
     * Plucks the string associated with the parameter.
     * @param string a character corresponding to a string to be plucked
     */
    public void pluck(char string) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == string) {
                strings[i].pluck();
            }
        }
       
    }

    /** Plays the current sound (sum of all strings). */
    public void play() {
        double sample = 0.0;
        for (int i = 0; i < keys.length; i++) {
            sample += strings[i].sample();
        }
        
        StdAudio.play(sample);
    }
            

    /** Advances the simulation by invoking each string's tic method. */
    public void tic() {
        for (int i = 0; i < keys.length; i++) {
            strings[i].tic();
        }
    }
}
