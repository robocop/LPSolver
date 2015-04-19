package fr.enslyon;

import fr.enslyon.DivisionRing.DivisionRing;
import fr.enslyon.LinearCombination.DictionaryEntry;
import fr.enslyon.LinearCombination.LinearCombination;
import fr.enslyon.LinearCombination.LinearCombinationException;
import fr.enslyon.SimplexAlgorithm.Dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by quentin on 17/04/15.
 */
public class LinearProgramToDictionary<T> {
    LinearProgram<T> linearProgram;
    private DivisionRing<T> ring;
    LinearProgramToDictionary(LinearProgram<T> linearProgram, DivisionRing<T> ring) {
        this.linearProgram = linearProgram;
        this.ring = ring;
    }

    public LinearProgram<T> getLinearProgram() {
        return linearProgram;
    }
    public void makeUniform() {
        this.makeUniformObjective();
        this.makeUniformInequalities();
        this.makeUniformBounds();
        this.setLowerBoundToZero();
    }

    private void makeUniformObjective() {
        linearProgram.getObjective().setToMaximize();
    }
    private void makeUniformInequalities() {
        for(int i =0; i < linearProgram.getInequalities().size(); i++) {
            Inequality<T> inequality = linearProgram.getInequalities().get(i);
            if(inequality.isGreaterInequality()) {
                inequality.scalarMultiplication(ring.fromInteger(-1));
                inequality.setLessInequality();
            }
            inequality.makeUniformConstant();

        }
    }
    private void makeUniformBounds() {
        Bounds<T> bounds = linearProgram.getBounds();
        Bounds<T> newBounds = new Bounds<T>();

        HashMap<String, T> lowerBounds = new HashMap<String, T>();
        HashMap<String, T> upperBounds = new HashMap<String, T>();

        for(int i =0; i < bounds.size(); i++) {
            String var = bounds.get(i).getVariable();
            if(bounds.get(i).hasLowerBound()) {
                if (!lowerBounds.containsKey(var) ||
                        ring.compare(lowerBounds.get(var), bounds.get(i).getLowerBound()) <= 0) {
                    lowerBounds.put(var, bounds.get(i).getLowerBound());
                }
            }
            if(bounds.get(i).hasUpperBound()) {
                if (!upperBounds.containsKey(var) ||
                        ring.compare(upperBounds.get(var), bounds.get(i).getUpperBound()) >= 0) {
                    upperBounds.put(var, bounds.get(i).getUpperBound());
                }
            }
        }

        for(String var: lowerBounds.keySet()) {
            Bound<T> b = new Bound<T>(var);
            b.setLowerBound(lowerBounds.get(var));
            newBounds.add(b);
        }

        this.setUpperBoundsAsInequalities(upperBounds);
        linearProgram.setBounds(newBounds);
    }

    private void setUpperBoundsAsInequalities(HashMap<String, T> upperBounds) {
        for(String var: upperBounds.keySet()) {
            SyntacticLinearCombination<T> l = new SyntacticLinearCombination<T>(ring);
            l.setVariable(var, ring.fromInteger(1));
            Inequality<T> inequality = new Inequality<T>(ring, l, false, upperBounds.get(var));
            linearProgram.getInequalities().add(inequality);
        }
    }

    private void setLowerBoundToZero() {
        HashSet<String> variablesWithLowerBound = new HashSet<String>();
        for(int i = 0; i < linearProgram.getBounds().size(); i++) {
            Bound<T> b = linearProgram.getBounds().get(i);
            T a = b.getLowerBound();
            linearProgram.getObjective().getObjective().translateVariable(b.getVariable(), a);
            for(int j = 0; j < linearProgram.getInequalities().size(); j++) {
                linearProgram.getInequalities().get(j).translateVariable(b.getVariable(), a);
            }
            variablesWithLowerBound.add(b.getVariable());
            b.setLowerBound(ring.fromInteger(0));
        }

        rewriteUnboundedVariables(variablesWithLowerBound);


    }

    private void rewriteUnboundedVariables(HashSet<String> variablesWithLowerBound) {
        HashSet<String> variables = new HashSet<String>(linearProgram.getVariables().getVariables());
        for(String v: variables) {
            if(!variablesWithLowerBound.contains(v)) { //If the variable does not have lower bound
                //We rewrite v has v-nv with v>=0 and nv>=0
                String nv = linearProgram.getVariables().add();

                this.rewriteCombination(linearProgram.getObjective().getObjective(), v, nv);
                for(int i = 0; i < linearProgram.getInequalities().size(); i++) {
                    SyntacticLinearCombination<T> l = linearProgram.getInequalities().get(i).getCombination();
                    this.rewriteCombination(l, v, nv);
                }

                Bound<T> b0 = new Bound<T>(v);
                b0.setLowerBound(ring.fromInteger(0));

                Bound<T> b1 = new Bound<T>(nv);
                b1.setLowerBound(ring.fromInteger(0));

                linearProgram.getBounds().add(b0); linearProgram.getBounds().add(b1);
            }
        }
    }

    //In combination, we replace v by v-nv
    private void rewriteCombination(SyntacticLinearCombination<T> combination, String v, String nv) {
        if(combination.containsVariable(v)) {
            combination.setVariable(nv, ring.opposite(combination.getVariable(v)));
        }
    }

    public HashMap<String, Integer> associateIndexToVariables() {
        HashMap<String, Integer> associatedIndex = new HashMap<String, Integer>();
        int index = 0;
        for(String var: linearProgram.getVariables().getVariables()) {
            associatedIndex.put(var, index);
            index++;
        }
        return associatedIndex;
    }

    public Dictionary<T> convertToDictionary(HashMap<String, Integer> associateIndex)
            throws LinearCombinationException {
        int n = associateIndex.size();
        int m = linearProgram.getInequalities().size();

        LinearCombination<T> objective = convertToLinearCombination(associateIndex,
                linearProgram.getObjective().getObjective(), n, m);

        ArrayList<DictionaryEntry<T>> eqs = new ArrayList<DictionaryEntry<T>>(m);

        for(int i = 0; i < m; i++) {

            Inequality<T> inequality = linearProgram.getInequalities().get(i);

            DictionaryEntry<T> dictEntry = this.convertInequality(associateIndex, inequality, n, m, n+i);

            eqs.add(i, dictEntry);
        }


        return new Dictionary<T>(objective, eqs, false);


    }

    private LinearCombination<T> convertToLinearCombination(HashMap<String, Integer> associateIndex,
                                                            SyntacticLinearCombination<T> combination, int n, int m)
        throws LinearCombinationException
    {
        LinearCombination<T> linearCombination = new LinearCombination<T>(n, n+m, ring);
        linearCombination.setConstant(combination.getConstant());

        int[] variables = new int[n];
        T[] constants = (T[]) new Object[n];


        for(String var: associateIndex.keySet()) {
            T cst = ring.fromInteger(0);
            if(combination.containsVariable(var)) {
                cst = combination.getVariable(var);
            }
            constants[associateIndex.get(var)] = cst;
            variables[associateIndex.get(var)] = associateIndex.get(var);

        }

        linearCombination.setConstants(constants);
        linearCombination.setVariables(variables);

        return linearCombination;
    }

    private DictionaryEntry<T> convertInequality(HashMap<String, Integer> associatedIndex,
                                                 Inequality<T> inequality,
                                                 int n, int m, int indexDictionary)
            throws LinearCombinationException

    {

        LinearCombination<T> l = this.convertToLinearCombination(associatedIndex, inequality.getCombination(), n, m);
        l.scalarMultiplication(ring.fromInteger(-1));
        l.setConstant(inequality.getConstant());
        return new DictionaryEntry<T>(l, indexDictionary);
    }


}
