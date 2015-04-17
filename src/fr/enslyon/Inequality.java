package fr.enslyon;

import fr.enslyon.DivisionRing.DivisionRing;

/**
 * Created by quentin on 16/04/15.
 */
public class Inequality<T> {
    SyntacticLinearCombination<T> combination;
    T constant;
    Boolean greater;
    DivisionRing<T> ring;
    Inequality(DivisionRing<T> ring, SyntacticLinearCombination<T> combination, Boolean greater, T constant) {
        this.combination = combination;
        this.greater = greater;
        this.constant = constant;
        this.ring = ring;
    }
    public void scalarMultiplication(T scalar) {
        combination.scalarMultiplication(scalar);
        constant = ring.prod(constant, scalar);
        if(ring.compare(scalar, ring.fromInteger(0)) < 0) {
            greater = !greater;
        }
    }
    public Boolean isGreaterInequality() {
        return greater;
    }
    public String toString() {
        String output = combination.toString();
        if(greater) {
            output += " >= ";
        }
        else {
            output += " <= ";
        }
        output += constant.toString();
        return output;
    }

}
