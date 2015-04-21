package fr.enslyon;

import fr.enslyon.DivisionRing.DivisionRing;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.SimplexAlgorithm.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by quentin on 21/04/15.
 */
public class SimplexEncapsulation<T> {
    private boolean isObjectiveMaximize = true;
    public boolean debug = false;

    private HashSet<String> variables;
    private HashMap<String, Integer> variablesIndex = new HashMap<String, Integer>();
    private HashMap<String, String> composedVariables = new HashMap<String, String>();
    private Dictionary<T> dictionary;

    private DivisionRing<T> ring;

    public SimplexEncapsulation(DivisionRing<T> ring) {
        this.ring = ring;
    }
    public void setDebug(boolean debugValue) {
        this.debug = debugValue;
        if(this.dictionary!=null) {
            this.dictionary.setDebug(debugValue);
        }
    }
    public void setMinimizeObjective() {
        this.isObjectiveMaximize = false;
    }

    public void setVariables(HashSet<String> variables) {
        this.variables = variables;
    }
    public void addVariableAssociation(String var, Integer index) {
        variablesIndex.put(var, index);
    }
    public HashMap<String, Integer> getVariablesIndex() {
        return this.variablesIndex;
    }

    public void setDictionary(Dictionary<T> dictionary) {
        this.dictionary = dictionary;
    }

    public void solve() throws LinearCombinationException, DictionaryEntryException {
        if(debug) {
            System.out.println("Variable assignations:");
            System.out.println(variablesIndex);
        }
        Simplex<T> simplex = new Simplex<T>(this.dictionary, ring);
        SimplexOutput<T> solution = simplex.solve();

        if(debug) {
            solution.print();
        }

        this.printSolution(solution);
    }

    /*
        The unbounded variable a is set to be equal to a-b
        with a >=0 and b >= 0
     */
    public void addUnboundedVariable(String a, String b) {
        this.composedVariables.put(a, b);
    }


    private void printOptimalSolution(OptimalSolution<T> optimalSolution) {
        T optimalValue = optimalSolution.getValue();
        if(!isObjectiveMaximize) {
            optimalValue = ring.opposite(optimalValue);
        }
        System.out.printf("Optimal solution: %s\n", optimalValue.toString());

        for(String var: variables) {
            T value = optimalSolution.getVariableValue(variablesIndex.get(var));
            if(composedVariables.containsKey(var)) {
                String var2 = composedVariables.get(var);
                int index2 = variablesIndex.get(var2);
                T value2 = optimalSolution.getVariableValue(index2);
                value = ring.add(value, ring.opposite(value2));
            }
            System.out.printf("%s = %s\n", var, value);
        }
    }

    private void printSolution(SimplexOutput<T> solution) {
        if(solution instanceof OptimalSolution) {
            this.printOptimalSolution((OptimalSolution<T>) solution);
        }
        else if(solution instanceof EmptyDomain) {
            solution.print();
        }
    }
}
