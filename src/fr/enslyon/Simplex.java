package fr.enslyon;

/**
 * Created by quentin on 14/04/15.
 * The general simplex algorithm.
 * Here is implemented the auxiliary l.p., the resolution and the projection of the dictionary.
 */
public class Simplex extends SimplexBase {
    Simplex(LinearCombination objective, DictionaryEntry[] dictionaryEntries) {
        super(objective, dictionaryEntries);
    }
    Simplex(LinearCombination objective, DictionaryEntry[] dictionaryEntries, Boolean debug) {
        super(objective, dictionaryEntries, debug);
    }


    public SimplexOutput solve() throws DictionaryEntryException, LinearCombinationException {
        if (!this.checkConstantsPositivity()) {
            //We do a copy of the objective before modifying it
            LinearCombination previousObjective = new LinearCombination(this.dictionary.getObjective());
            int variableAdded = this.addNewVariablesSetToOne();
            SimplexOutput auxiliarySolution = this.solveAuxiliaryLP(variableAdded);

            if(auxiliarySolution instanceof OptimalSolution) {
                if(((OptimalSolution) auxiliarySolution).getValue() > 0.0001)
                    return new EmptyDomain();
                else {
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

        this.dictionary.print("New objective, new dictionary:\n");
        this.dictionary.printDictionary();

        this.dictionary.print("First illegal pivot:\n");
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
        int v = this.dictionary.getObjective().addVariable(-1);
        this.dictionary.print("Adding variable x_" + v + "\n");
        for(int j = 0; j < this.dictionary.length(); j++) {
            int vj = this.dictionary.get(j).addVariable(1);
            if(vj != v) {
                throw new DictionaryEntryException("Dimension problem when trying to add a variable: "
                        + "the dictionary and the objective do not have the same dimension");
            }
        }
        return v;
    }

    // Set the new objective the z = -x_{n-1}
    private void newObjective() throws LinearCombinationException {
        double[] newObjectiveConstants = new double[this.dictionary.getObjective().getNumberOfTerms()];
        newObjectiveConstants[newObjectiveConstants.length-1] = -1;
        this.dictionary.getObjective().setConstants(newObjectiveConstants);
    }

    private void fistIllegalPivot(int v) throws LinearCombinationException {
        int i = 0;
        double cst = this.dictionary.get(i).getConstant();

        for(int j = 1; j < this.dictionary.length(); j++) {
            if(dictionary.get(j).getConstant() < cst) {
                i = j;
                cst = dictionary.get(j).getConstant();
            }
        }
        this.pivot(v, i);
    }

    private void dictionaryProjection(int v, LinearCombination previousObjective) throws LinearCombinationException {
        //We remove the variable added for each entry.
        for(int j = 0; j < this.dictionary.length(); j++) {
            this.dictionary.get(j).removeVariable(v);
        }
        this.dictionary.getObjective().removeVariable(v);
        this.dictionary.getObjective().setConstant(previousObjective.getConstant());

        for(int i = 0; i < this.dictionary.getObjective().getNumberOfTerms(); i++) {
            int variable = previousObjective.getVariablesLinearCombination()[i];
            double scalar = previousObjective.getConstantsLinearCombination()[i];
            LinearCombination l = new LinearCombination(this.dictionary.get(this.getIndexDictionary(variable)));
            l.scalarMultiplication(scalar);
            this.dictionary.getObjective().add(l);
        }

        this.dictionary.print("New objective:\n");
        this.dictionary.printDictionary();
    }

    private int getIndexDictionary(int variable) {
        for(int j = 0; j < this.dictionary.length(); j++) {
            if(this.dictionary.get(j).getVariable() == variable)
                return j;
        }
        return -1;
    }
}