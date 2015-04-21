package fr.enslyon;

import fr.enslyon.DivisionRing.DivisionRing;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.Parser.LinearProgram;
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
    private LinearProgram<T> linearProgram;

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

    public void setLinearProgram(LinearProgram<T> lp) {
        this.linearProgram = lp;
    }

    public void solve() throws LinearCombinationException, DictionaryEntryException {
        if(debug) {
            System.out.println(linearProgram.toString() + "\n");
        }
        if(debug) {
            System.out.println("Variable assignations:");
            System.out.println(variablesIndex.toString() + "\n");
        }
        Simplex<T> simplex = new Simplex<T>(this.dictionary, ring);
        SimplexOutput<T> solution = simplex.solve();

        if(debug) {
            solution.print();
            System.out.println();
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

    private void printUnboundedSolution(UnboundedSolution<T> unboundedSolution) {
        System.out.println("Unbounded solution");
        for(String var: variables) {
            T constantCoefficient = unboundedSolution.getConstant(variablesIndex.get(var));
            T unboundedCoefficient = unboundedSolution.getUnboundedCoefficient(variablesIndex.get(var));

            if (composedVariables.containsKey(var)) {
                String var2 = composedVariables.get(var);
                T constantCoefficient2 = unboundedSolution.getConstant(variablesIndex.get(var2));
                T unboundedCoefficient2 = unboundedSolution.getUnboundedCoefficient(variablesIndex.get(var2));

                constantCoefficient = ring.add(constantCoefficient, ring.opposite(constantCoefficient2));
                unboundedCoefficient = ring.add(unboundedCoefficient, ring.opposite(unboundedCoefficient2));
            }

            System.out.printf("%s = %s + %s t\n", var, constantCoefficient.toString(), unboundedCoefficient.toString());
        }
        System.out.println("t >= 0");
    }

    private void printSolution(SimplexOutput<T> solution) {
        if(solution instanceof OptimalSolution) {
            this.printOptimalSolution((OptimalSolution<T>) solution);
        }
        else if(solution instanceof EmptyDomain) {
            solution.print();
        }
        else if(solution instanceof UnboundedSolution) {
            this.printUnboundedSolution((UnboundedSolution<T>) solution);
        }
    }
}
