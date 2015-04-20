package fr.enslyon;

import fr.enslyon.DivisionRing.RationalNumber;

/**
 * Created by quentin on 16/04/15.
 */
public class Value {
    final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public Item<RationalNumber> asItem() {
        return (Item<RationalNumber>) value;
    }

    public SyntacticLinearCombination<RationalNumber> asSyntacticLinearCombination() {
        return (SyntacticLinearCombination<RationalNumber>)value;
    }


    @Override
    public int hashCode() {

        if(value == null) {
            return 0;
        }

        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if(value == o) {
            return true;
        }

        if(value == null || o == null || o.getClass() != value.getClass()) {
            return false;
        }

        Value that = (Value)o;

        return this.value.equals(that.value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}