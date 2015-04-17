package fr.enslyon.DivisionRing;

/**
 * Created by quentin on 14/04/15.
 */
public class RationalNumber {
    int a; int b;
    public RationalNumber(int a, int b) throws ArithmeticException{
        this.a = this.sign(b) * a;
        this.b = this.sign(b) * b;
        if(this.b == 0) {
            throw new ArithmeticException();
        }
        int gcd = this.gcd(this.sign(this.a) * this.a, this.b);
        this.a = this.a / gcd;
        this.b = this.b / gcd;
    }

    public RationalNumber(int x) {
        this.a = x;
        this.b = 1;
    }

    public int getNominator() {
        return this.a;
    }
    public int getDenominator() {
        return this.b;
    }

    public String toString() {
        if(this.b > 1)
            return String.format("%d/%d", this.a, this.b);
        else
            return String.format("%d", this.a);
    }

    public boolean equals(Object x) {
        if(x instanceof RationalNumber) {
            RationalNumber rx = (RationalNumber) x;
            return (a == rx.getNominator()) && (b == rx.getDenominator());
        }
        else {
            return false;
        }
    }

    private int sign(int x) {
        if(x >= 0)
            return 1;
        else {
            return -1;
        }
    }

    private int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }

    public RationalNumber(double x) {
        this((int) Math.round(10000000 * x), 10000000);
    }
 }
