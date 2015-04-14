package fr.enslyon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by quentin on 24/03/15.
 */
public class Simplex {
    private LinearCombination objective;
    private DictionaryEntry[] dictionary;
    private Set<Integer> initialVariables;

    Simplex(int n, int m, LinearCombination objective, DictionaryEntry[] dictionary) {
        this.objective = objective;
        this.dictionary = dictionary;
        this.buildInitialVariables();
    }

    //If the simplex is already solved and lead to an optimal solution, return it.
    public SimplexOutput getOptimalSolution() throws DictionaryEntryException {
        if (this.isOptimalSolution()) {

            List<ResultVariable> solution = new LinkedList<ResultVariable>();
            for (int i = 0; i < this.dictionary.length; i++) {
                if (initialVariables.contains(dictionary[i].getVariable())) {
                    solution.add(new ResultVariable<Double>(dictionary[i].getVariable(), dictionary[i].getConstant()));
                }
            }
            return new OptimalSolution(this.objective.getConstant(), solution);
        } else {
            throw new DictionaryEntryException("The l.p. is not in a state corresponding to an optimal solution");
        }
    }

    //If the simplex is already solved and lead to an unbounded solution, return it.
    public SimplexOutput getUnboundedSolution() throws DictionaryEntryException {
        int entering_variable = this.getVariableWithPositiveConstant();
        if (entering_variable >= 0 && this.isUnboundedSolution(entering_variable)) {
            List<ResultVariable<List<Double>>> solution = new LinkedList<ResultVariable<List<Double>>>();
            for (int i = 0; i < this.dictionary.length; i++) {
                int v = dictionary[i].getVariable();
                double c = dictionary[i].getConstantsLinearCombination()
                            [dictionary[i].getIndexVariable(entering_variable)];
                List<Double> values = new LinkedList<Double>();
                values.add(dictionary[i].getConstant());
                values.add(c);
                solution.add(new ResultVariable<List<Double>>(v, values));
            }
            return new UnboundedSolution(entering_variable, solution);
        } else {
            throw new DictionaryEntryException("The l.p. is not in a state corresponding to an unbounded solution");
        }
    }

    //Solve the l.p.
    public SimplexOutput solve() throws DictionaryEntryException, LinearCombinationException {
        if (!this.checkConstantsPositivity()) {
            //We do a copy of the objective before modifying it
            LinearCombination previousObjective = new LinearCombination(this.objective);

            int v = this.addNewVariablesSetToOne();
            this.newObjective();

            System.out.println("New objective, new dictionary:");
            for(int j = 0; j < this.dictionary.length; j++) {
                dictionary[j].print();
            }
            objective.print();

            System.out.println("First illegal pivot:");
            this.fistIllegalPivot(v);

            SimplexOutput solution = this.solve();

            if(solution instanceof OptimalSolution) {
                if(((OptimalSolution) solution).getValue() > 0.0)
                    return new EmptyDomain();
                else {
                    solution.print();
                    this.dictionaryProjection(v, previousObjective);
                }
            }
            else {
                return new EmptyDomain();
            }

        }
        Boolean solved = false;
        while (!solved) {
            solved = step();
            System.out.println("---End of the step---");
        }
        if (this.isOptimalSolution())
            return this.getOptimalSolution();
        else
            return this.getUnboundedSolution();

    }

    //Check if the simplex, in this current state corresponds to an optimal solution
    private Boolean isOptimalSolution() {
        double[] z_constants = objective.getConstantsLinearCombination();
        for(int i = 0; i < z_constants.length; i++) {
            if(z_constants[i] > 0) {
                return false;
            }
        }
        return true;
    }

    //Check if the simplex, in this current state corresponds to an unbounded solution
    private Boolean isUnboundedSolution(int entering_variable) {
        for(int i = 0; i < this.dictionary.length; i++) {
            int index_entering_variable = dictionary[i].getIndexVariable(entering_variable);
            if(dictionary[i].getConstantsLinearCombination()[index_entering_variable] < 0) {
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
        System.out.println("Entering variable: x_" + enteringVariable);

        if (this.isUnboundedSolution(enteringVariable)) {
            return true;
        }

        int i_dict_leaving = this.getIndexDictionaryLeavingVariable(enteringVariable);
        System.out.println("Dictionary leaving: " + i_dict_leaving);
        System.out.println("Leaving variable: x_" + dictionary[i_dict_leaving].getVariable());
        this.substituteEnteringVariable(enteringVariable, i_dict_leaving);

        return false;
    }

    //Build the set of initial variables (the variables we want the values - e.g. not the slash variables).
    private void buildInitialVariables() {
        this.initialVariables = new HashSet<Integer>();
        Set<Integer> slashVariables = new HashSet<Integer>();
        for(int j = 0; j < this.dictionary.length; j++) {
            slashVariables.add(this.dictionary[j].getVariable());
        }
        for(int v = 0; v < this.objective.getNumberOfTerms() + this.dictionary.length; v++) {
            if(!slashVariables.contains(v)) {
                this.initialVariables.add(v);
            }
        }
    }


    private void printObjective() {
        System.out.print("z = ");
        this.objective.print();
    }

    // So the operation entering variable <-> leaving variable in the dictionary
    // and in the objective
    private void substituteEnteringVariable(int entering_variable, int i_dict_leaving)
            throws LinearCombinationException {
        //We permute the leading variable and the entering variable:
        System.out.printf("Permuting x_%d and x_%d in %d\n", entering_variable, this.dictionary[i_dict_leaving].getVariable(), i_dict_leaving);
        this.dictionary[i_dict_leaving].swap_variable(entering_variable);
        this.dictionary[i_dict_leaving].print();

        for(int i = 0; i < this.dictionary.length; i++) {
            if(i != i_dict_leaving) {
                this.dictionary[i].substitute(this.dictionary[i_dict_leaving]);
                this.dictionary[i].print();
            }
        }
        this.objective.substitute(this.dictionary[i_dict_leaving]);
        this.printObjective();
    }

    //Check that all the constants are positives in the dictionary
    private Boolean checkConstantsPositivity() {
        for(int i = 0; i < this.dictionary.length; i++) {
            if(dictionary[i].getConstant() < 0) {
                return false;
            }
        }
        return true;
    }

    //Find a variable for which the coefficient associated in the objective function is positive
    private int getVariableWithPositiveConstant() {
        double[] z_constants = objective.getConstantsLinearCombination();
        for(int i = 0; i < z_constants.length; i++) {
            if (z_constants[i] > 0) {
                return objective.getVariablesLinearCombination()[i];
            }
        }
        return -1;
    }

    //Find a leaving variable, e.g. among all the coefficients a corresponding to the variable enteringVariable
    //Find one for which -the constant / a is minimum

    private int getIndexDictionaryLeavingVariable(int enteringVariable) {
        int index = -1; double ratio = 0;
        for(int i = 0; i < this.dictionary.length; i++) {
            int index_entering_variable = dictionary[i].getIndexVariable(enteringVariable);
            double c = dictionary[i].getConstantsLinearCombination()[index_entering_variable];
            if (c < 0) {
                double new_ratio = dictionary[i].getConstant() / (-c);
                if (index == -1 || new_ratio < ratio) {
                    index = i;
                    ratio = new_ratio;
                }
            }
        }
        return index;
    }

    private int getIndexDictionary(int variable) {
        for(int j = 0; j < this.dictionary.length; j++) {
            if(this.dictionary[j].getVariable() == variable)
                return j;
        }
        return -1;
    }

    //Add a variable in each dictionary set to 1 (during the first phase of the simplex,
    // when we try to find a point in the domain)

    private int addNewVariablesSetToOne() throws DictionaryEntryException {
        int v = this.objective.addVariable(-1);
        System.out.println("Adding variable x_" + v);
        for(int j = 0; j < this.dictionary.length; j++) {
            int vj = this.dictionary[j].addVariable(1);
            if(vj != v) {
                throw new DictionaryEntryException("Dimension problem when trying to add a variable: "
                        + "the dictionary and the objective do not have the same dimension");
            }
        }
        return v;
    }

    // Set the new objective the z = -x_{n-1}
    private void newObjective() throws LinearCombinationException {
        double[] newObjectiveConstants = new double[this.objective.getNumberOfTerms()];
        newObjectiveConstants[newObjectiveConstants.length-1] = -1;
        objective.setConstants(newObjectiveConstants);
    }

    private void fistIllegalPivot(int v) throws LinearCombinationException {
        int i = 0;
        double cst = dictionary[i].getConstant();

        for(int j = 1; j < this.dictionary.length; j++) {
            if(dictionary[j].getConstant() < cst) {
                i = j;
                cst = dictionary[j].getConstant();
            }
        }

        this.substituteEnteringVariable(v, i);

    }

    private void dictionaryProjection(int v, LinearCombination previousObjective) throws LinearCombinationException {
        //We remove the variable added.
        for(int j = 0; j < this.dictionary.length; j++) {
            this.dictionary[j].removeVariable(v);
            this.dictionary[j].print();
        }
        objective.removeVariable(v);

        objective.setConstant(previousObjective.getConstant());

        for(int i = 0; i < this.objective.getNumberOfTerms(); i++) {
            int variable = previousObjective.getVariablesLinearCombination()[i];
            double scalar = previousObjective.getConstantsLinearCombination()[i];
            LinearCombination l = new LinearCombination(this.dictionary[this.getIndexDictionary(variable)]);
            l.scalarMultiplication(scalar);
            objective.add(l);

        }
        System.out.println("New objective:");
        this.printObjective();
    }
}
