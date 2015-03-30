package fr.enslyon;

/**
 * Created by quentin on 24/03/15.
 */
public class Simplex {
    private int n;
    private int m;
    private LinearCombination objective;
    private DictionaryEntry[] dictionary;

    Simplex(int n, int m, LinearCombination objective, DictionaryEntry[] dictionary) {
        this.n = n;
        this.m = m;
        this.objective = objective;
        this.dictionary = dictionary;
    }

    public void step() throws DictionaryEntryException{
        if(!this.check_constants_positivity()) {
            System.out.println("The simplex is not implemented on this entry");
        }
        else { //All the objective constants are positive.
            if(this.is_optimal_solution()) {
                for(int i = 0; i < m; i++) {
                    dictionary[i].print();
                }
                System.out.println("The solution is optimal, z = " + objective.getConstant());
                this.printObjective();
            }
            else {
                int entering_variable = this.get_variable_with_negative_constant();
                if(this.is_unbounded_solution(entering_variable)) {
                    System.out.println("Unbounded solution, certificate:");
                    System.out.printf("x_%d = t, t >=0\n", entering_variable);
                    for(int i = 0; i < m; i++) {
                        int v = dictionary[i].getVariable();
                        double c = dictionary[i].getConstantsLinearCombination()
                                [dictionary[i].getIndexVariable(entering_variable)];
                        System.out.printf("x_%d = %.01f + %.01f t", v, dictionary[i].getConstant(), c);

                    }
                }
                else {
                    int i_dict_leaving = this.get_index_dictionary_leaving_variable(entering_variable);
                    System.out.printf("Entering variable: x_%d, leaving variable: x_%d\n",
                            entering_variable, this.dictionary[i_dict_leaving].getVariable());

                    this.substitute_entering_variable(entering_variable, i_dict_leaving);

                    this.printObjective();

                }

            }
            System.out.println("End of the step");
        }

    }

    private void printObjective() {
        System.out.print("z = ");
        this.objective.print();
    }

    private void substitute_entering_variable(int entering_variable, int i_dict_leaving)
            throws DictionaryEntryException {

        //We permute the leading variable and the entering variable:
        this.dictionary[i_dict_leaving].change_variable(entering_variable);
        this.dictionary[i_dict_leaving].print();


        for(int i = 0; i < m; i++) {
            if(i != i_dict_leaving) {
                this.dictionary[i].substitute(this.dictionary[i_dict_leaving]);
                this.dictionary[i].print();
            }
        }
        this.objective.substitute(this.dictionary[i_dict_leaving]);

    }

    private Boolean check_constants_positivity() {
        for(int i = 0; i < m; i++) {
            if(dictionary[i].getConstant() < 0) {
                return false;
            }
        }
        return true;
    }

    public Boolean is_optimal_solution() {
        double[] z_constants = objective.getConstantsLinearCombination();
        for(int i = 0; i < n; i++) {
            if(z_constants[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private int get_variable_with_negative_constant() {
        double[] z_constants = objective.getConstantsLinearCombination();
        for(int i = 0; i < n; i++) {
            if (z_constants[i] > 0) {
                return objective.getVariablesLinearCombination()[i];
            }
        }
        return -1;
    }

    public Boolean is_unbounded_solution(int entering_variable) {
        for(int i = 0; i < m; i++) {
            int index_entering_variable = dictionary[i].getIndexVariable(entering_variable);
            if(dictionary[i].getConstantsLinearCombination()[index_entering_variable] < 0) {
                return false;
            }
        }
        return true;
    }

    private int get_index_dictionary_leaving_variable(int entering_variable) {
        int index = -1; double ratio = 0;
        for(int i = 0; i < m; i++) {
            int index_entering_variable = dictionary[i].getIndexVariable(entering_variable);
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


}
