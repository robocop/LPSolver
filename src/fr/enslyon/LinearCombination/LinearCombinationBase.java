package fr.enslyon.LinearCombination;

/**
 * Created by quentin on 14/04/15.
 */

public class LinearCombinationBase<T> {
    protected int numberOfTerms; //number of different terms in the equation
    protected int maximumIndexVariables; //the total number of variables is maximumIndexVariables
    protected int[] variablesLinearCombination;
    protected int[] reverseVariables;
    protected T[] constantsLinearCombination;


    LinearCombinationBase(int numberOfTerms, int maximumIndexVariables) throws LinearCombinationException {
        if(numberOfTerms <= 0) {
            throw new LinearCombinationException("the parameter numberOfTerms should be positive");
        }
        else if (numberOfTerms > maximumIndexVariables) {
            throw new LinearCombinationException("the parameter maximumIndexVariables should be greater " +
                    "than numberOfTerms");
        }
        else {
            this.numberOfTerms = numberOfTerms;
            this.maximumIndexVariables = maximumIndexVariables;
        }
    }
    LinearCombinationBase(LinearCombinationBase<T> l) throws LinearCombinationException {
        this(l.getNumberOfTerms(), l.getMaximumIndexVariables());

        int[] variables = new int[l.getNumberOfTerms()];
        System.arraycopy(l.getVariablesLinearCombination(), 0, variables, 0, l.getNumberOfTerms());

        @SuppressWarnings("unchecked")
        T[] constants = ( T[]) new Object[l.getNumberOfTerms()];
        System.arraycopy(l.getConstantsLinearCombination(), 0, constants, 0, l.getNumberOfTerms());

        this.setVariables(variables);
        this.setConstants(constants);

    }

    public void setVariables(int[] variables) throws LinearCombinationException {
        if(variables.length != this.numberOfTerms) {
            throw new LinearCombinationException("the size of the array of the variables should be equal to " +
                    "numberOfTerms");
        }
        else {
            this.variablesLinearCombination = variables;
            this.buildReverseVariables();
        }
    }
    public void setConstants(T[] constants) throws LinearCombinationException {
        if(constants.length != this.numberOfTerms) {
            throw new LinearCombinationException("the size of the array of the constants should be equal to " +
                    "numberOfTerms");
        }
        else {
            this.constantsLinearCombination = constants;
        }
    }

    public void setVariable(int variable, T value) {
        int indexVariable = this.getIndexVariable(variable);
        this.constantsLinearCombination[indexVariable] = value;
    }

    public T getVariable(int variable) {
        int indexVariable = this.getIndexVariable(variable);
        return this.constantsLinearCombination[indexVariable];
    }

    public int getMaximumIndexVariables() {
        return this.maximumIndexVariables;
    }
    public int getNumberOfTerms() {
        return this.numberOfTerms;
    }

    public T[] getConstantsLinearCombination() {
        return this.constantsLinearCombination;
    }
    public int[] getVariablesLinearCombination() {
        return this.variablesLinearCombination;
    }





    public int getIndexVariable(int v) {
        return this.reverseVariables[v];
    }

    public String toString() {
        String output = "";
        for(int i = 0; i < numberOfTerms; i++) {
            if(i > 0)
                output += " + ";
            output += String.format("%s * x_%d",
                    this.constantsLinearCombination[i].toString(), this.variablesLinearCombination[i]);
        }
        return output;
    }


    public int addVariable(T constantAssociated) {
        int[] variables = new int[this.numberOfTerms+1];
        @SuppressWarnings("unchecked")
        T[] constants = (T[]) new Object[this.numberOfTerms+1];

        System.arraycopy(this.variablesLinearCombination, 0, variables, 0, this.numberOfTerms);
        System.arraycopy(this.constantsLinearCombination, 0, constants, 0, this.numberOfTerms);

        variables[this.numberOfTerms] = this.maximumIndexVariables;
        constants[this.numberOfTerms] = constantAssociated;

        this.numberOfTerms += 1;
        this.maximumIndexVariables += 1;

        this.variablesLinearCombination = variables;
        this.constantsLinearCombination = constants;

        this.buildReverseVariables();

        return this.maximumIndexVariables-1;

    }

    public void removeVariable(int variable) {
        int[] variables = new int[this.numberOfTerms-1];
        @SuppressWarnings("unchecked")
        T[] constants = (T[]) new Object[this.numberOfTerms-1];
        int j = 0;
        for(int i = 0; i < this.numberOfTerms; i++) {
            if(this.variablesLinearCombination[i] != variable) {
                variables[j] = this.variablesLinearCombination[i];
                constants[j] = this.constantsLinearCombination[i];
                j++;
            }
        }
        this.numberOfTerms--;
        this.variablesLinearCombination = variables;
        this.constantsLinearCombination = constants;

        this.buildReverseVariables();
    }

    private void buildReverseVariables() {
        //build the array this.reverseVariables such that:
        //this.reverseVariables[v] = i with this.variablesLinearCombination[i] = v if i >= 0
        //this.reverseVariables[v] = -1 otherwise

        this.reverseVariables = new int[this.maximumIndexVariables];
        for(int v = 0; v < this.maximumIndexVariables; v++) {
            this.reverseVariables[v] = -1;
        }

        for(int i = 0; i < this.numberOfTerms; i++) {
            this.reverseVariables[this.variablesLinearCombination[i]] = i;
        }
    }

}
