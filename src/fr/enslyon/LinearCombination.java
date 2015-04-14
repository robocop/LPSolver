package fr.enslyon;

/**
 * Created by quentin on 30/03/15.
 */
public class LinearCombination {
    protected int numberOfTerms; //number of free variables in the equation
    protected int maximumIndexVariables; //the total number of variables is maximumIndexVariables

    protected double constant = 0;

    protected int[] variablesLinearCombination;
    protected int[] reverseVariables;
    protected double[] constantsLinearCombination;

    LinearCombination(int numberOfTerms, int maximumIndexVariables) throws LinearCombinationException {
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

    LinearCombination(LinearCombination l) throws LinearCombinationException {
        this(l.getVariablesLinearCombination().length, l.getMaximumIndexVariables());
        int[] variables = l.getVariablesLinearCombination();
        double[] constants = l.getConstantsLinearCombination();
        double constant = l.getConstant();
        this.setConstant(constant);
        this.setVariables(variables);
        this.setConstants(constants);
    }



    public void setConstant(double constant) {
        this.constant = constant;
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
    public void setConstants(double[] constants) throws LinearCombinationException {
        if(constants.length != this.numberOfTerms) {
            throw new LinearCombinationException("the size of the array of the constants should be equal to " +
                    "numberOfTerms");
        }
        else {
            this.constantsLinearCombination = constants;
        }
    }

    public int getMaximumIndexVariables() {
        return this.maximumIndexVariables;
    }
    public int getNumberOfTerms() {
        return this.numberOfTerms;
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

    public int addVariable(double constantAssociated) {
        int[] variables = new int[this.numberOfTerms+1];
        double[] constants = new double[this.numberOfTerms+1];

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
        double[] constants = new double[this.numberOfTerms-1];
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

    public int getIndexVariable(int v) {
        return this.reverseVariables[v];
    }

    public void scalarMultiplication(double scalar) {
        this.constant *= scalar;
        for(int i = 0; i < this.numberOfTerms; i++) {
            this.constantsLinearCombination[i] *= scalar;
        }
    }

    public void add(LinearCombination toAdd) {
        double[] constantsToAdd = toAdd.getConstantsLinearCombination();
        this.constant += toAdd.getConstant();
        for(int i = 0; i < this.numberOfTerms; i++) {
            int indexToAdd = toAdd.getIndexVariable(this.variablesLinearCombination[i]);
            this.constantsLinearCombination[i] += constantsToAdd[indexToAdd];
        }
    }



    public void print() {
        System.out.printf("%.01f ", this.constant);
        for(int i = 0; i < numberOfTerms; i++) {
            System.out.printf("+ %.2f * x_%d ",
                    this.constantsLinearCombination[i], this.variablesLinearCombination[i]);
        }
        System.out.println();
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

    public void substitute(DictionaryEntry toSubstitute) throws LinearCombinationException {
        //We substitute all the occurrences of the variable of toSubstitute by the corresponding value in
        // this current object.
        //The corresponding value is:
        // entry.get_constant() +
        // scalar_product(toSubstitute.get_constants_linear_combination(),
        //                toSubstitute.getVariablesLinearCombination())

        int currentIndexToSubstitute = this.getIndexVariable(toSubstitute.getVariable());

        if(currentIndexToSubstitute == -1) {
            throw new LinearCombinationException("The variable " + toSubstitute.getVariable() +
                    "does not appears in the linear combination. Impossible to substitute it");
        }
        else {
            double cstSubstitution = this.constantsLinearCombination[currentIndexToSubstitute];
            this.constant += cstSubstitution * toSubstitute.getConstant();

            for (int i = 0; i < this.numberOfTerms; i++) {
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

                    //int old_v = this.variablesLinearCombination[currentIndexToSubstitute];
                    int old_v = toSubstitute.getVariable();
                    this.variablesLinearCombination[currentIndexToSubstitute] = v;

                    this.reverseVariables[old_v] = -1;
                    this.reverseVariables[v] = currentIndexToSubstitute;


                    this.constantsLinearCombination[currentIndexToSubstitute] =
                            cstSubstitution * toSubstitute.getConstantsLinearCombination()[i];



                }
            }
        }

    }

}
