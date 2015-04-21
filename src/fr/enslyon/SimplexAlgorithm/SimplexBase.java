package fr.enslyon.SimplexAlgorithm;

import fr.enslyon.DivisionRing.DivisionRing;
import fr.enslyon.LinearCombination.DictionaryEntry;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombination;
import fr.enslyon.LinearCombination.LinearCombinationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by quentin on 24/03/15.
 * This class corresponds to the simplex algorithm where the initial dictionary corresponds to an existing
 * vertex in the polyhedron.
 * If it is not the case, then it would return an exception.
 * For the general case with the auxiliary l.p. see the Simplex class
 */

public class SimplexBase<T> {
    protected Dictionary<T> dictionary;
    protected DivisionRing<T> ring;

    SimplexBase(Dictionary<T> dictionary, DivisionRing<T> ring) {
        this.dictionary = dictionary;
        this.ring = ring;
    }
    SimplexBase(LinearCombination<T> objective, ArrayList<DictionaryEntry<T>> dictionaryEntries,
                DivisionRing<T> ring) {
        this.ring = ring;
        this.dictionary = new Dictionary<T>(objective, dictionaryEntries);
    }

    //If the simplex is already solved and lead to an optimal solution, return it.
    public SimplexOutput<T> getOptimalSolution() throws DictionaryEntryException {
        if (this.isOptimalSolution()) {
            HashMap<Integer, T> solution = new HashMap<Integer, T>();

            for (int j = 0; j < this.dictionary.size(); j++) {
                if (this.dictionary.getInitialVariables().contains(dictionary.get(j).getVariable())) {
                    solution.put(dictionary.get(j).getVariable(), dictionary.get(j).getConstant());
                }
            }
            return new OptimalSolution<T>(ring, this.dictionary.getObjective().getConstant(), solution);
        } else {
            throw new DictionaryEntryException("The l.p. is not in a state corresponding to an optimal solution");
        }
    }

    //If the simplex is already solved and lead to an unbounded solution, return it.
    public SimplexOutput<T> getUnboundedSolution() throws DictionaryEntryException {
        int entering_variable = this.getVariableWithPositiveConstant();
        if (entering_variable >= 0 && this.isUnboundedSolution(entering_variable)) {
            List<ResultVariable<List<T>>> solution = new LinkedList<ResultVariable<List<T>>>();
            for (int i = 0; i < this.dictionary.size(); i++) {
                int v = dictionary.get(i).getVariable();
                T c = dictionary.get(i).getConstantsLinearCombination()
                            [dictionary.get(i).getIndexVariable(entering_variable)];
                List<T> values = new LinkedList<T>();
                values.add(dictionary.get(i).getConstant());
                values.add(c);
                solution.add(new ResultVariable<List<T>>(v, values));
            }
            return new UnboundedSolution<T>(entering_variable, solution);
        } else {
            throw new DictionaryEntryException("The l.p. is not in a state corresponding to an unbounded solution");
        }
    }

    //Solve the l.p.
    public SimplexOutput<T> solve() throws DictionaryEntryException, LinearCombinationException {
        if (!this.checkConstantsPositivity()) {
            throw new DictionaryEntryException("The simplex is not implemented on this entry");
        }
        Boolean solved = false;
        while (!solved) {
            solved = step();
            if(!solved)
                this.dictionary.print("---End of the step---\n");
        }
        if (this.isOptimalSolution())
            return this.getOptimalSolution();
        else
            return this.getUnboundedSolution();

    }

    //Check if the simplex, in this current state corresponds to an optimal solution
    private Boolean isOptimalSolution() {
        T[] zConstants = this.dictionary.getObjective().getConstantsLinearCombination();
        for (T zConstant : zConstants) {
            if (this.ring.compare(zConstant, ring.fromInteger(0)) > 0) {
                return false;
            }
        }
        return true;
    }

    //Check if the simplex, in this current state corresponds to an unbounded solution
    private Boolean isUnboundedSolution(int entering_variable) {
        for(int i = 0; i < this.dictionary.size(); i++) {
            int index_entering_variable = dictionary.get(i).getIndexVariable(entering_variable);
            if(this.ring.compare(dictionary.get(i).getConstantsLinearCombination()[index_entering_variable],
                    ring.fromInteger(0)) < 0) {
                return false;
            }
        }
        return true;
    }

    //Move forward to one step in the simplex algorithm
    private Boolean step() throws DictionaryEntryException, LinearCombinationException {
        if (this.isOptimalSolution()) {
            return true;
        }

        int enteringVariable = this.getVariableWithPositiveConstant();
        this.dictionary.print("Entering variable: x_" + enteringVariable + "\n");

        if (this.isUnboundedSolution(enteringVariable)) {
            return true;
        }

        int i_dict_leaving = this.getIndexDictionaryLeavingVariable(enteringVariable);

        this.dictionary.print("Dictionary leaving: " + i_dict_leaving + "\n");
        this.dictionary.print("Leaving variable: x_" + dictionary.get(i_dict_leaving).getVariable() + "\n");
        this.pivot(enteringVariable, i_dict_leaving);

        return false;
    }


    // Do the operation entering variable <-> leaving variable in the dictionary
    // and in the objective
    protected void pivot(int enteringVariable, int indexDictionaryLeaving) throws LinearCombinationException {
        //We permute the leading variable and the entering variable:
        this.dictionary.print(
                String.format("Permuting x_%d and x_%d in %d\n", enteringVariable,
                    this.dictionary.get(indexDictionaryLeaving).getVariable(), indexDictionaryLeaving)
        );

        this.dictionary.get(indexDictionaryLeaving).swap_variable(enteringVariable);
        this.dictionary.printDictionaryEntry(indexDictionaryLeaving);


        for(int i = 0; i < this.dictionary.size(); i++) {
            if(i != indexDictionaryLeaving) {
                this.dictionary.get(i).substitute(this.dictionary.get(indexDictionaryLeaving));
                this.dictionary.printDictionaryEntry(i);
            }
        }
        this.dictionary.getObjective().substitute(this.dictionary.get(indexDictionaryLeaving));
        this.dictionary.printObjective();
    }

    //Check that all the constants are positives in the dictionary
    protected Boolean checkConstantsPositivity() {
        for(int i = 0; i < this.dictionary.size(); i++) {
            if(ring.compare(dictionary.get(i).getConstant(), ring.fromInteger(0)) < 0) {
                return false;
            }
        }
        return true;
    }

    //Find a variable for which the coefficient associated in the objective function is positive
    private int getVariableWithPositiveConstant() {
        T[] z_constants = this.dictionary.getObjective().getConstantsLinearCombination();
        for(int i = 0; i < z_constants.length; i++) {
            if (ring.compare(z_constants[i], ring.fromInteger(0)) > 0) {
                return this.dictionary.getObjective().getVariablesLinearCombination()[i];
            }
        }
        return -1;
    }

    //Find a leaving variable, e.g. among all the coefficients a corresponding to the variable enteringVariable
    //Find one for which -the constant / a is minimum

    private int getIndexDictionaryLeavingVariable(int enteringVariable) {
        int index = -1; T ratio = ring.fromInteger(0);
        for(int i = 0; i < this.dictionary.size(); i++) {
            int index_entering_variable = dictionary.get(i).getIndexVariable(enteringVariable);
            T c = dictionary.get(i).getConstantsLinearCombination()[index_entering_variable];
            if (ring.compare(c, ring.fromInteger(0)) < 0) {
                T new_ratio = ring.prod(dictionary.get(i).getConstant(), ring.inverse(ring.opposite(c)));
                if (index == -1 || ring.compare(new_ratio,ratio) < 0) {
                    index = i;
                    ratio = new_ratio;
                }
            }
        }
        return index;
    }

}
