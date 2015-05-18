package fr.enslyon.DivisionRing;

<<<<<<< HEAD
/**
 * Created by quentin on 14/04/15.
 */
public class RationalDivisionRing implements DivisionRing<RationalNumber> {
=======
import java.util.Comparator;

/**
 * Created by quentin on 14/04/15.
 */
public class RationalDivisionRing implements DivisionRing<RationalNumber>, Comparator<RationalNumber> {
>>>>>>> review2
    @Override
    public RationalNumber add(RationalNumber x, RationalNumber y) {
        return new RationalNumber(x.getNominator() * y.getDenominator() + y.getNominator() * x.getDenominator(),
                x.getDenominator() * y.getDenominator());
    }

    @Override
    public RationalNumber prod(RationalNumber x, RationalNumber y) {
        return new RationalNumber(x.getNominator() * y.getNominator(), x.getDenominator() * y.getDenominator());
    }

    @Override
    public RationalNumber inverse(RationalNumber x) {
        return new RationalNumber(x.getDenominator(), x.getNominator());
    }

    @Override
    public RationalNumber opposite(RationalNumber x) {
        return new RationalNumber(-x.getNominator(), x.getDenominator());
    }

    @Override
<<<<<<< HEAD
    public RationalNumber fromInteger(int n) {
        return new RationalNumber(n, 1);
    }

    @Override
=======
>>>>>>> review2
    public RationalNumber fromString(String n) {
        return new RationalNumber(Double.valueOf(n));
    }

    @Override
    public int compare(RationalNumber x, RationalNumber y) {
        long a = x.getNominator() * y.getDenominator();
        long b = x.getDenominator() * y.getNominator();

<<<<<<< HEAD
        if(a < b)
            return -1;
        else if(a == b)
            return 0;
        else
            return 1;
=======
        if(a < b) {
            return -1;
        }
        else if(a == b) {
            return 0;
        }
        else {
            return 1;
        }
>>>>>>> review2
    }
}