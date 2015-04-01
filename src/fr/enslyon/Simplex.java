package fr.enslyon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by quentin on 24/03/15.
 */
public class Simplex {
    private int n;
    private int m;
    private LinearCombination objective;
    private DictionaryEntry[] dictionary;
    private Set<Integer> initialVariables;

    Simplex(int n, int m, LinearCombination objective, DictionaryEntry[] dictionary) {
        this.n = n;
        this.m = m;
        this.objective = objective;
        this.dictionary = dictionary;
        this.buildInitialVariables();
    }

    public SimplexOutput getOptimalSolution() throws DictionaryEntryException {
        if (this.is_optimal_solution()) {

            List<ResultVariable> solution = new LinkedList<ResultVariable>();
            for (int i = 0; i < m; i++) {
                if (initialVariables.contains(dictionary[i].getVariable())) {
                    solution.add(new ResultVariable<Double>(dictionary[i].getVariable(), dictionary[i].getConstant()));
                }
            }
            return new OptimalSolution(this.objective.getConstant(), solution);
        } else {
            throw new DictionaryEntryException("The l.p. is not in a state corresponding to an optimal solution");
        }
    }

    public SimplexOutput getUnboundedSolution() throws DictionaryEntryException {
        int entering_variable = this.get_variable_with_negative_constant();
        if (entering_variable >= 0 && this.is_unbounded_solution(entering_variable)) {
            List<ResultVariable<List<Double>>> solution = new LinkedList<ResultVariable<List<Double>>>();
            for (int i = 0; i < m; i++) {
                int v = dictionary[i].getVariable();
                if (initialVariables.contains(v)) {
                    double c = dictionary[i].getConstantsLinearCombination()
                            [dictionary[i].getIndexVariable(entering_variable)];
                    List<Double> values = new LinkedList<Double>();
                    values.add(dictionary[i].getConstant());
                    values.add(c);
                    solution.add(new ResultVariable<List<Double>>(v, values));
                }
            }
            return new UnboundedSolution(entering_variable, solution);
        } else {
            throw new DictionaryEntryException("The l.p. is not in a state corresponding to an unbounded solution");
        }
    }

    public SimplexOutput solve() throws DictionaryEntryException, LinearCombinationException {
        Boolean solved = step();
        while(!solved) {
            solved = step();
        }
        if(this.is_optimal_solution())
            return this.getOptimalSolution();
        else
            return this.getUnboundedSolution();
    }

    private Boolean is_optimal_solution() {
        double[] z_constants = objective.getConstantsLinearCombination();
        for(int i = 0; i < n; i++) {
            if(z_constants[i] > 0) {
                return false;
            }
        }
        return true;
    }

    private Boolean is_unbounded_solution(int entering_variable) {
        for(int i = 0; i < m; i++) {
            int index_entering_variable = dictionary[i].getIndexVariable(entering_variable);
            if(dictionary[i].getConstantsLinearCombination()[index_entering_variable] < 0) {
                return false;
            }
        }
        return true;
    }

    private Boolean step() throws DictionaryEntryException, LinearCombinationException {
        if (!this.check_constants_positivity()) {
            throw new DictionaryEntryException("The simplex is not implemented on this entry");
        }
        if (this.is_optimal_solution()) {
            return true;
        }

        int entering_variable = this.get_variable_with_negative_constant();

        if (this.is_unbounded_solution(entering_variable)) {
            return true;
        }

        int i_dict_leaving = this.get_index_dictionary_leaving_variable(entering_variable);
        this.substitute_entering_variable(entering_variable, i_dict_leaving);

        return false;
    }

    private void buildInitialVariables() {
        this.initialVariables = new HashSet<Integer>();
        Set<Integer> slashVariables = new HashSet<Integer>();
        for(int j = 0; j < m; j++) {
            slashVariables.add(this.dictionary[j].getVariable());
        }
        for(int v = 0; v < n+m; v++) {
            if(!slashVariables.contains(v)) {
                this.initialVariables.add(v);
            }
        }
    }

    private void printObjective() {
        System.out.print("z = ");
        this.objective.print();
    }

    private void substitute_entering_variable(int entering_variable, int i_dict_leaving)
            throws LinearCombinationException {
        //We permute the leading variable and the entering variable:
        this.dictionary[i_dict_leaving].swap_variable(entering_variable);

        for(int i = 0; i < m; i++) {
            if(i != i_dict_leaving) {
                this.dictionary[i].substitute(this.dictionary[i_dict_leaving]);
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

    private int get_variable_with_negative_constant() {
        double[] z_constants = objective.getConstantsLinearCombination();
        for(int i = 0; i < n; i++) {
            if (z_constants[i] > 0) {
                return objective.getVariablesLinearCombination()[i];
            }
        }
        return -1;
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
