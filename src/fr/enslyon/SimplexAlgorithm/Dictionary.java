package fr.enslyon.SimplexAlgorithm;

import fr.enslyon.LinearCombination.DictionaryEntry;
import fr.enslyon.LinearCombination.LinearCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by quentin on 14/04/15.
 */
public class Dictionary<T> {
    private ArrayList<DictionaryEntry<T>> dictionaryEntries;
    private LinearCombination<T> objective;
    private Set<Integer> initialVariables;
    Boolean debug = false;
    Boolean latex = false;

    public Dictionary(LinearCombination<T> objective, ArrayList<DictionaryEntry<T>> dictionaryEntries) {
        this.objective = objective;
        this.dictionaryEntries = dictionaryEntries;
        this.buildInitialVariables();
    }

    public void setDebug(boolean debugValue) {
        this.debug = debugValue;
    }

    public void setLatex(boolean latexValue) {
        this.latex = latexValue;
    }

    public DictionaryEntry<T> get(int j) {
        return this.dictionaryEntries.get(j);
    }
    public LinearCombination<T> getObjective() {
        return this.objective;
    }
    public int size() {
        return this.dictionaryEntries.size();
    }

    public Set<Integer> getInitialVariables() {
        return this.initialVariables;
    }

    private void print(String message) {
        if(debug || latex) {
            System.out.print(message);
        }
    }

    public void println(String message) {
        if(debug) {
            print(message + "\n");
        }
        else if(latex) {
            print(message + "\\newline\n");
        }
    }

    public void printObjective() {
        if(debug) {
            this.print("z = ");
            this.print(this.objective.toString() + "\n");
        }
        else if(latex) {
            this.print(String.format("$ z $  & = %s \n", this.latexFormatLinearCombination(this.objective)));
        }
    }

    private String latexFormatLinearCombination(LinearCombination<T> lc) {
        String r = String.format("& $%s$ ", lc.getConstant().toString());
        for(int i = 0; i < lc.getNumberOfTerms(); i++) {
            r += String.format("& + & $%s x_{%d}$ ", lc.getConstantsLinearCombination()[i],
                    lc.getVariablesLinearCombination()[i]);
        }
        return r;
    }
    public String printVariable(int variable) {
        if(latex)
            return "$x_{" + variable + "}$";
        else
            return "x_" + variable;
    }



    public void printDictionaryEntry(int j) {
        if(debug) {
            this.print(this.get(j).toString() + "\n");
        }
        else if(latex) {
            this.print(String.format("$ x_{%d} $ & = %s\n", this.get(j).getVariable(),
                    this.latexFormatLinearCombination(this.get(j))));
        }
    }

    public void printDictionary() {
        if(debug) {
            for (int j = 0; j < this.dictionaryEntries.size(); j++) {
                this.printDictionaryEntry(j);
            }
            this.print("----------------------\n");
            this.printObjective();
        }
        else if(latex) {
            String w = "";
            for(int i = 0; i < 2*(this.objective.getNumberOfTerms()+1)+1; i++) {
                w += "l ";
            }
            this.print("\\begin{tabular}{" + w + "}");
            for (int j = 0; j < this.dictionaryEntries.size(); j++) {
                this.printDictionaryEntry(j);
                this.print("\\\\ \n");
            }
            this.print("\\hline\n");
            this.printObjective();
            this.println("\\end{tabular}");
        }
    }


    //Build the set of initial variables (the variables we want the values - e.g. not the slash variables).
    private void buildInitialVariables() {
        this.initialVariables = new HashSet<Integer>();
        Set<Integer> slashVariables = new HashSet<Integer>();
        for(int j = 0; j < this.dictionaryEntries.size(); j++) {
            slashVariables.add(this.dictionaryEntries.get(j).getVariable());
        }
        for(int v = 0; v < this.objective.getNumberOfTerms() + this.dictionaryEntries.size(); v++) {
            if(!slashVariables.contains(v)) {
                this.initialVariables.add(v);
            }
        }
    }
}