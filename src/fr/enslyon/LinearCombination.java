package fr.enslyon;

/**
 * Created by quentin on 30/03/15.
 */
public class LinearCombination {
    protected int n; //number of free variables in the equation
    protected int m; //the total number of variables is n+m

    protected double constant = 0;

    protected int[] variablesLinearCombination;
    protected int[] reverseVariables;
    protected double[] constantsLinearCombination;

    LinearCombination(int n, int m) throws DictionaryEntryException {
        if(n <= 0 || m <= 0) {
            throw new DictionaryEntryException("the parameter n & m should be positive");
        }
        else {
            this.n = n;
            this.m = m;
        }
    }

    public void setConstant(double constant) {
        this.constant = constant;
    }
    public void setVariables(int[] variables) throws DictionaryEntryException {
        if(variables.length != n) {
            throw new DictionaryEntryException("the size of the array of the variables should be equal to n");
        }
        else {
            this.variablesLinearCombination = variables;
            this.buildReverseVariables();
        }
    }
    public void setConstants(double[] constants) throws DictionaryEntryException {
        if(constants.length != n) {
            throw new DictionaryEntryException("the size of the array of the constants should be equal to n");
        }
        else {
            this.constantsLinearCombination = constants;
        }
    }

    public double getConstant() {
        return this.constant;
    }
    public double[] getConstantsLinearCombination() {
        return this.constantsLinearCombination;
    }
    public int[] getVariablesLinearCombination() {
        return this.variablesLinearCombination;
    }

    public int getIndexVariable(int v) {
        return this.reverseVariables[v];
    }

    public void print() {
        System.out.printf("%.01f ", this.constant);
        for(int i = 0; i < n; i++) {
            System.out.printf(" + %.01f * x_%d ",
                    this.constantsLinearCombination[i], this.variablesLinearCombination[i]);
        }
        System.out.println();
    }

    private void buildReverseVariables() {
        //build the array this.reverseVariables such that:
        //this.reverseVariables[v] = i with this.variablesLinearCombination[i] = v if i >= 0
        //this.reverseVariables[v] = -1 otherwise

        this.reverseVariables = new int[this.n+this.m];
        for(int v = 0; v < this.n + this.m; v++) {
            this.reverseVariables[v] = -1;
        }

        for(int i = 0; i < this.n; i++) {
            this.reverseVariables[this.variablesLinearCombination[i]] = i;
        }
    }

    public void substitute(DictionaryEntry toSubstitute) throws DictionaryEntryException {
        //We substitute all the occurrences of the variable of toSubstitute by the corresponding value in
        // this current object.
        //The corresponding value is:
        // entry.get_constant() +
        // scalar_product(toSubstitute.get_constants_linear_combination(),
        //                toSubstitute.getVariablesLinearCombination())

        int currentIndexToSubstitute = this.getIndexVariable(toSubstitute.getVariable());

        if(currentIndexToSubstitute == -1) {
            throw new DictionaryEntryException("The variable " + toSubstitute.getVariable() +
                    "does not appears in the system. Impossible to substitute it");
        }
        else {
            double cstSubstitution = this.constantsLinearCombination[currentIndexToSubstitute];
            this.constant += cstSubstitution * toSubstitute.getConstant();

            for (int i = 0; i < this.n; i++) {
                //i is an index of toSubstitute

                int v = toSubstitute.getVariablesLinearCombination()[i];
                int current_index_v= this.getIndexVariable(v);

                if(current_index_v != -1) {
                    this.constantsLinearCombination[current_index_v] +=
                            cstSubstitution * toSubstitute.getConstantsLinearCombination()[i];
                }
                else {
                    //the variable v does not appears in the current equation
                    //we replace in the current entry the variable toSubsistute.get_variable by v

                    this.variablesLinearCombination[currentIndexToSubstitute] = v;
                    this.constantsLinearCombination[currentIndexToSubstitute] =
                            cstSubstitution * toSubstitute.getConstantsLinearCombination()[i];

                    this.reverseVariables[this.getVariablesLinearCombination()[currentIndexToSubstitute]] = -1;
                    this.reverseVariables[v] = currentIndexToSubstitute;

                }
            }
        }

    }

}
