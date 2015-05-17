package fr.enslyon.DivisionRing;

/**
 * Created by quentin on 14/04/15.
 */
public interface DivisionRing<T> {
    public T add(T x, T y);
    public T prod(T x, T y);
    public T inverse(T x);
    public T opposite(T x);
    public T fromString(String n);

    public int compare(T a, T b);
}