import java.lang.Math.*;
import java.util.*;
/**
 * Write a description of class SteelString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SteelString implements InstrumentString {
    
    private double frequency;
    static double ENERGY_DECAY_FACTOR = 0.996;
    private List<Double> buffer;
    private int bufferLength;
    private int time;
    
    /**
     * Constructor for objects of class SineString
     */
    public SteelString(double frequency) throws IllegalArgumentException {
        if (frequency <= 0) {
            throw new IllegalArgumentException("Invalid frequency");
        }
        
        this.bufferLength = Math.round(StdAudio.SAMPLE_RATE/(long)frequency);
        
        if (this.bufferLength < 2) {
            throw new IllegalArgumentException();
        }
        
        this.frequency = frequency;
        this.buffer = new LinkedList<Double>();
        
        for (int i = 0; i < this.bufferLength; i++) {
            this.buffer.add(new Double(0));
        }
    }
    
    public SteelString(double[] init) {
        for (int i = 0; i < init.length; i++) {
            this.buffer.add(init[i]);
        }
    }

    public void pluck() {
        for (int i = 0; i < this.bufferLength; i++) {
            this.buffer.set(i,Math.random()-0.5);
        }
    }
    
    public void tic() {
        double sample = this.sample();
        this.buffer.remove(0);
        this.buffer.add((sample + this.sample()) * 0.5 * ENERGY_DECAY_FACTOR);
        this.time++;
    }
    
    public double sample() {
        return (double)this.buffer.get(0);
    }
    
    public int time() {
        return this.time;
    }
}
