package fr.enslyon.DivisionRing;

import java.util.Comparator;

/**
 * Created by quentin on 20/04/15.
 */
public class DoubleDivisionRing implements DivisionRing<Double>, Comparator<Double> {
    @Override
    public Double add(Double x, Double y) {
        return x+y;
    }

    @Override
    public Double prod(Double x, Double y) {
        return x*y;
    }

    @Override
    public Double inverse(Double x) {
        return 1/x;
    }

    @Override
    public Double opposite(Double x) {
        return -x;
    }

    @Override
    public Double fromString(String n) {
        return Double.valueOf(n);
    }

    @Override
    public int compare(Double x, Double y) {
        final double err = 1.0E-10;
        double delta = x-y;
        if(Math.abs(delta) < err) {
            return 0;
        }
        else if (x < y) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
