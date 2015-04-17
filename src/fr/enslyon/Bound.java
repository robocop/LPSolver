package fr.enslyon;

/**
 * Created by quentin on 17/04/15.
 */
public class Bound<T> {
    String variable;
    T lower; T upper;

    Bound(String variable) {
        this.variable = variable;
    }
    public void setLowerBound(T bound) {
        this.lower = bound;
    }
    public void setUpperBound(T bound) {
        this.upper = bound;
    }

    public boolean isUpperBound() {
        return (upper != null);
    }
    public T getUpperBound() {
        return upper;
    }
    public boolean isLowerBound() {
        return (lower != null);
    }
    public T getLowerBound() {
        return lower;
    }
    public String toString() {
        String r = "";
        if(lower != null) {
            r = lower.toString() + " <= ";
        }
        r += variable;
        if(upper != null) {
            r += " <= " + upper.toString();
        }
        return r;
    }

}
