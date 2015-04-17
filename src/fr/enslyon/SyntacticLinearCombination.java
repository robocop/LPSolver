package fr.enslyon;

import fr.enslyon.DivisionRing.DivisionRing;

import java.util.HashMap;

/**
 * Created by quentin on 16/04/15.
 */
public class SyntacticLinearCombination<T> {
    private HashMap<String, T> combination;
    private DivisionRing<T> ring;

    SyntacticLinearCombination(DivisionRing<T> ring) {
        combination = new HashMap<String, T>();
        this.ring = ring;
    }

    public void setVariable(String var, T cstAssociated) {
        if(combination.containsKey(var)) {
            combination.put(var, ring.add(cstAssociated, this.getVariable(var)));
        }
        else {
            combination.put(var, cstAssociated);
        }
    }

    public T getConstant() {
        if(combination.containsKey("")) {
            return combination.get("");
        }
        else {
            return ring.fromInteger(0);
        }
    }
    public void setConstant(T c) {
        setVariable("", c);
    }
    public T getVariable(String var) {
        return combination.get(var);
    }

    public void scalarMultiplication(T scalar) {
        for(String key: combination.keySet()) {
            T newValue = ring.prod(combination.get(key), scalar);
            combination.put(key, newValue);
        }
    }

    public String toString() {
        boolean first = true;
        String output = "";
        for(String var: combination.keySet()) {
            if(!first) {
                output += " + ";
            }
            else {
                first = false;
            }
            output += String.format("%s %s", combination.get(var).toString(), var);
        }
        return output;
    }
}
