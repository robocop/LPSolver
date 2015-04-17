package fr.enslyon;

import java.util.HashMap;

/**
 * Created by quentin on 16/04/15.
 */
public class Variables {
    int generatorNameVariable;
    private HashMap<String, Boolean> variables;
    Variables() {
        generatorNameVariable = 0;
        this.variables = new HashMap<String, Boolean>();
    }
    public void add(String s, Boolean bounded) {
        variables.put(s, bounded);
    }
    public String add(Boolean bounded) {
        String var = "nx_" +Integer.toString(this.generatorNameVariable);
        if(!variables.containsKey(var)) {
            this.add(var, bounded);
            return var;
        }
        else {
            this.generatorNameVariable++;
            return add(bounded);
        }
    }
    public void setBounded(String v) {
        variables.put(v, true);
    }
    public Boolean isBounded(String v) {
        return variables.get(v);
    }

    public String toString() {
        String output = "";
        for(String var: variables.keySet()) {
            output += var + "\n";
        }
        return output;
    }
}
