package fr.enslyon.DivisionRing;

/**
 * Created by quentin on 14/04/15.
 */
public class RationalNumber {
<<<<<<< HEAD
    long a; long b;
    public RationalNumber(long a, long b) throws ArithmeticException{
        this.a = this.sign(b) * a;
        this.b = this.sign(b) * b;
        if(this.b == 0) {
            throw new ArithmeticException();
        }
        long gcd = this.gcd(this.sign(this.a) * this.a, this.b);
        this.a = this.a / gcd;
        this.b = this.b / gcd;
    }

    public RationalNumber(long x) {
        this.a = x;
        this.b = 1;
    }

=======
    final private long a;
    final private long b;
    public RationalNumber(long a, long b) throws ArithmeticException{
        a = this.sign(b) * a;
        b = this.sign(b) * b;
        if(b == 0) {
            throw new ArithmeticException();
        }
        long gcd = this.gcd(this.sign(a) * a, b);
        this.a = a / gcd;
        this.b = b / gcd;
    }

    public RationalNumber(double x) {
        this(Math.round(100000 * x), 100000);
    }


>>>>>>> review2
    public long getNominator() {
        return this.a;
    }
    public long getDenominator() {
        return this.b;
    }

<<<<<<< HEAD
=======
    @Override
>>>>>>> review2
    public String toString() {
        if(this.b > 1)
            return String.format("%d/%d", this.a, this.b);
        else
<<<<<<< HEAD
            return String.format("%d", this.a);
    }

    public boolean equals(Object x) {
        if(x instanceof RationalNumber) {
=======
            return Long.toString(this.a);
    }

    @Override
    public boolean equals(Object x) {
        if(x !=  null && x instanceof RationalNumber) {
>>>>>>> review2
            RationalNumber rx = (RationalNumber) x;
            return (a == rx.getNominator()) && (b == rx.getDenominator());
        }
        else {
            return false;
        }
    }

    private long sign(long x) {
        if(x >= 0)
            return 1;
        else {
            return -1;
        }
    }

    private long gcd(long a, long b) {
        if(b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }

<<<<<<< HEAD
    public RationalNumber(double x) {
        this(Math.round(100000 * x), 100000);
    }
=======
>>>>>>> review2
 }
