import java.lang.Math.*;
import java.util.*;
/**
 * Write a description of class SineString here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SineString implements InstrumentString {
    
    private double frequency;
    static double ENERGY_DECAY_FACTOR = 0.990;
    private List<Double> buffer;
    private int bufferLength;
    private int time;
    
    /**
     * Constructor for objects of class SineString
     */
    public SineString(double frequency) throws IllegalArgumentException {
        if (frequency <= 0) {
            throw new IllegalArgumentException();
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
    
    public SineString(double[] init) {
        for (int i = 0; i < init.length; i++) {
            this.buffer.add(init[i]);
        }
    }

    public void pluck() {
        for (int i = 0; i < this.bufferLength; i++) {
            this.buffer.set(i,Math.sin(i)/2);
        }
    }
    
    public void tic() {
        this.buffer.add(this.sample()*ENERGY_DECAY_FACTOR);
        this.buffer.remove(0);
        this.time++;
    }
    
    public double sample() {
        return (double)this.buffer.get(0);
    }
    
    public int time() {
        return this.time;
    }
}
