package fr.enslyon;

/**
 * Created by quentin on 22/03/15.
 */
public class DictionaryEntry extends LinearCombination {
    private int variable; // in [|0, maximumIndexVariables-1|], variable involved in the equation

    DictionaryEntry(int numberOfTerms, int maximumIndexVariables, int variable)
            throws DictionaryEntryException, LinearCombinationException
    {
        super(numberOfTerms, maximumIndexVariables);

        if (variable < 0 || variable >= maximumIndexVariables) {
            throw new DictionaryEntryException("the variable associated should be in the range " +
                    "[0, maximumIndexVariables-1]");
        }
        else {
            this.variable = variable;
        }
    }

    public int getVariable() {
        return variable;
    }

    public void print() {
        System.out.printf("x_%d = ", this.variable);
        super.print();
    }

    public void swap_variable(int variable) {
        int index_variable = super.getIndexVariable(variable);
        double c_variable = super.constantsLinearCombination[index_variable];
        this.setConstant(this.constant / -c_variable);

        this.variablesLinearCombination[index_variable] = this.variable;
        this.constantsLinearCombination[index_variable] = -1.;
        this.reverseVariables[variable] = -1;
        this.reverseVariables[this.variable] = index_variable;

        this.variable = variable;

        for(int i = 0; i < numberOfTerms; i++) {
            this.constantsLinearCombination[i] /= -c_variable;
        }
    }



}
