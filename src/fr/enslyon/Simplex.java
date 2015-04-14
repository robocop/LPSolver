package fr.enslyon;

/**
 * Created by quentin on 14/04/15.
 * The general simplex algorithm.
 * Here is implemented the auxiliary l.p., the resolution and the projection of the dictionary.
 */
public class Simplex extends SimplexBase {
    Simplex(LinearCombination objective, DictionaryEntry[] dictionary) {
        super(objective, dictionary);
    }

    public SimplexOutput solve() throws DictionaryEntryException, LinearCombinationException {
        if (!this.checkConstantsPositivity()) {
            //We do a copy of the objective before modifying it
            LinearCombination previousObjective = new LinearCombination(this.objective);
            int variableAdded = this.addNewVariablesSetToOne();
            SimplexOutput auxiliarySolution = this.solveAuxiliaryLP(variableAdded);

            if(auxiliarySolution instanceof OptimalSolution) {
                if(((OptimalSolution) auxiliarySolution).getValue() > 0.0001)
                    return new EmptyDomain();
                else {
                    auxiliarySolution.print();
                    this.dictionaryProjection(variableAdded, previousObjective);
                }
            }
            else {
                return new EmptyDomain();
            }

        }
        return super.solve();
    }

    private SimplexOutput solveAuxiliaryLP(int variableAdded)
            throws DictionaryEntryException, LinearCombinationException {

        this.newObjective();

        System.out.println("New objective, new dictionary:");
        for(int j = 0; j < this.dictionary.length; j++) {
            dictionary[j].print();
        }
        objective.print();

        System.out.println("First illegal pivot:");
        this.fistIllegalPivot(variableAdded);

        return this.solve();
    }

    public SimplexOutput computeAVertex() throws DictionaryEntryException, LinearCombinationException {
        int variableAdded = this.addNewVariablesSetToOne();
        return this.solveAuxiliaryLP(variableAdded);
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

        for(int j = 0; j < this.dictionary.length; j++) {
            this.dictionary[j].print();
        }
    }

    private int getIndexDictionary(int variable) {
        for(int j = 0; j < this.dictionary.length; j++) {
            if(this.dictionary[j].getVariable() == variable)
                return j;
        }
        return -1;
    }
}