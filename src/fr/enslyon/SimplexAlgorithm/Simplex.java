package fr.enslyon.SimplexAlgorithm;

import fr.enslyon.DivisionRing.DivisionRing;
import fr.enslyon.LinearCombination.DictionaryEntry;
import fr.enslyon.LinearCombination.DictionaryEntryException;
import fr.enslyon.LinearCombination.LinearCombination;
import fr.enslyon.LinearCombination.LinearCombinationException;

import java.util.ArrayList;

/**
 * Created by quentin on 14/04/15.
 * The general simplex algorithm.
 * Here is implemented the auxiliary l.p., the resolution and the projection of the dictionary.
 */
public class Simplex<T> extends SimplexBase<T> {
    Simplex(LinearCombination<T> objective, ArrayList<DictionaryEntry<T>> dictionaryEntries, DivisionRing<T> ring) {
        super(objective, dictionaryEntries, ring);
    }
    public Simplex(LinearCombination<T> objective, ArrayList<DictionaryEntry<T>> dictionaryEntries, DivisionRing<T> ring,
                   Boolean debug) {
        super(objective, dictionaryEntries, ring, debug);
    }

    public SimplexOutput<T> solve() throws DictionaryEntryException, LinearCombinationException {
        if (!this.checkConstantsPositivity()) {
            //We do a copy of the objective before modifying it
            LinearCombination<T> previousObjective = new LinearCombination<T>(this.dictionary.getObjective());
            int variableAdded = this.addNewVariablesSetToOne();
            SimplexOutput<T> auxiliarySolution = this.solveAuxiliaryLP(variableAdded);

            if(auxiliarySolution instanceof OptimalSolution) {
                if(ring.compare(((OptimalSolution<T>) auxiliarySolution).getValue(), ring.fromInteger(0)) > 0)
                    return new EmptyDomain<T>();
                else {
                    this.dictionaryProjection(variableAdded, previousObjective);
                }
            }
            else {
                return new EmptyDomain<T>();
            }

        }
        return super.solve();
    }

    private SimplexOutput<T> solveAuxiliaryLP(int variableAdded)
            throws DictionaryEntryException, LinearCombinationException {

        this.newObjective();

        this.dictionary.print("New objective, new dictionary:\n");
        this.dictionary.printDictionary();

        this.dictionary.print("First illegal pivot:\n");
        this.fistIllegalPivot(variableAdded);

        return this.solve();
    }

    public SimplexOutput<T> computeAVertex() throws DictionaryEntryException, LinearCombinationException {
        int variableAdded = this.addNewVariablesSetToOne();
        return this.solveAuxiliaryLP(variableAdded);
    }


    //Add a variable in each dictionary set to 1 (during the first phase of the simplex,
    // when we try to find a point in the domain)
    private int addNewVariablesSetToOne() throws DictionaryEntryException {
        int v = this.dictionary.getObjective().addVariable(ring.fromInteger(-1));
        this.dictionary.print("Adding variable x_" + v + "\n");
        for(int j = 0; j < this.dictionary.size(); j++) {
            int vj = this.dictionary.get(j).addVariable(ring.fromInteger(1));
            if(vj != v) {
                throw new DictionaryEntryException("Dimension problem when trying to add a variable: "
                        + "the dictionary and the objective do not have the same dimension");
            }
        }
        return v;
    }

    // Set the new objective the z = -x_{n-1}
    private void newObjective() throws LinearCombinationException {
        @SuppressWarnings("unchecked")
        T[] newObjectiveConstants = (T[]) new Object[this.dictionary.getObjective().getNumberOfTerms()];
        for(int i = 0; i < newObjectiveConstants.length-1; i++) {
            newObjectiveConstants[i] = ring.fromInteger(0);
        }
        newObjectiveConstants[newObjectiveConstants.length-1] = ring.fromInteger(-1);
        this.dictionary.getObjective().setConstants(newObjectiveConstants);
    }

    private void fistIllegalPivot(int v) throws LinearCombinationException {
        int i = 0;
        T cst = this.dictionary.get(i).getConstant();

        for(int j = 1; j < this.dictionary.size(); j++) {
            if(ring.compare(dictionary.get(j).getConstant(), cst) < 0) {
                i = j;
                cst = dictionary.get(j).getConstant();
            }
        }
        this.pivot(v, i);
    }

    private void dictionaryProjection(int v, LinearCombination<T> previousObjective)
            throws LinearCombinationException {
        //We remove the variable added for each entry.
        for(int j = 0; j < this.dictionary.size(); j++) {
            this.dictionary.get(j).removeVariable(v);
        }
        this.dictionary.getObjective().removeVariable(v);
        this.dictionary.getObjective().setConstant(previousObjective.getConstant());

        for(int i = 0; i < this.dictionary.getObjective().getNumberOfTerms(); i++) {
            int variable = previousObjective.getVariablesLinearCombination()[i];
            T scalar = previousObjective.getConstantsLinearCombination()[i];
            LinearCombination<T> l = new LinearCombination<T>(this.dictionary.get(this.getIndexDictionary(variable)));
            l.scalarMultiplication(scalar);
            this.dictionary.getObjective().add(l);
        }

        this.dictionary.print("New objective:\n");
        this.dictionary.printDictionary();
    }

    private int getIndexDictionary(int variable) {
        for(int j = 0; j < this.dictionary.size(); j++) {
            if(this.dictionary.get(j).getVariable() == variable)
                return j;
        }
        return -1;
    }
}