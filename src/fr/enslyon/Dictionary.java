package fr.enslyon;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by quentin on 14/04/15.
 */
public class Dictionary {
    private DictionaryEntry[] dictionaryEntries;
    private LinearCombination objective;
    private Set<Integer> initialVariables;
    Boolean debug = false;

    Dictionary(LinearCombination objective, DictionaryEntry[] dictionaryEntries, Boolean debug) {
        this.objective = objective;
        this.dictionaryEntries = dictionaryEntries;
        this.buildInitialVariables();
        this.debug = debug;
    }

    public DictionaryEntry get(int j) {
        return this.dictionaryEntries[j];
    }
    public LinearCombination getObjective() {
        return this.objective;
    }
    public int length() {
        return this.dictionaryEntries.length;
    }

    public Set<Integer> getInitialVariables() {
        return this.initialVariables;
    }

    public void print(String message) {
        if(debug) {
            System.out.print(message);
        }
    }

    public void printObjective() {
        this.print("z = ");
        this.print(this.objective.toString() + "\n");
    }

    public void printDictionaryEntry(int j) {
        this.print(this.get(j).toString() + "\n");
    }

    public void printDictionary() {
        for(int j = 0; j < this.dictionaryEntries.length; j++) {
            this.printDictionaryEntry(j);
        }
        this.print("----------------------\n");
        this.printObjective();
    }


    //Build the set of initial variables (the variables we want the values - e.g. not the slash variables).
    private void buildInitialVariables() {
        this.initialVariables = new HashSet<Integer>();
        Set<Integer> slashVariables = new HashSet<Integer>();
        for(int j = 0; j < this.dictionaryEntries.length; j++) {
            slashVariables.add(this.dictionaryEntries[j].getVariable());
        }
        for(int v = 0; v < this.objective.getNumberOfTerms() + this.dictionaryEntries.length; v++) {
            if(!slashVariables.contains(v)) {
                this.initialVariables.add(v);
            }
        }
    }




}
