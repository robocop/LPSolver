package fr.enslyon.SimplexAlgorithm;

import fr.enslyon.LinearCombination.DictionaryEntry;
import fr.enslyon.LinearCombination.LinearCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quentin on 14/04/15.
 */
public class Dictionary<T> {
    private ArrayList<DictionaryEntry<T>> dictionaryEntries;
    private LinearCombination<T> objective;
    private Set<Integer> initialVariables;
    Boolean debug = false;

    public Dictionary(LinearCombination<T> objective, ArrayList<DictionaryEntry<T>> dictionaryEntries, Boolean debug) {
        this.objective = objective;
        this.dictionaryEntries = dictionaryEntries;
        this.buildInitialVariables();
        this.debug = debug;
    }

    public DictionaryEntry<T> get(int j) {
        return this.dictionaryEntries.get(j);
    }
    public LinearCombination<T> getObjective() {
        return this.objective;
    }
    public int size() {
        return this.dictionaryEntries.size();
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
        for(int j = 0; j < this.dictionaryEntries.size(); j++) {
            this.printDictionaryEntry(j);
        }
        this.print("----------------------\n");
        this.printObjective();
    }


    //Build the set of initial variables (the variables we want the values - e.g. not the slash variables).
    private void buildInitialVariables() {
        this.initialVariables = new HashSet<Integer>();
        Set<Integer> slashVariables = new HashSet<Integer>();
        for(int j = 0; j < this.dictionaryEntries.size(); j++) {
            slashVariables.add(this.dictionaryEntries.get(j).getVariable());
        }
        for(int v = 0; v < this.objective.getNumberOfTerms() + this.dictionaryEntries.size(); v++) {
            if(!slashVariables.contains(v)) {
                this.initialVariables.add(v);
            }
        }
    }
}