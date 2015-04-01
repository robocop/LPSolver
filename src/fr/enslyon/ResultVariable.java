package fr.enslyon;

/**
 * Created by quentin on 31/03/15.
 */
public class ResultVariable<X> {
    int variable;
    X value;
    ResultVariable(int variable, X value) {
        this.variable = variable;
        this.value = value;
    }
    int getVariable() {
        return variable;
    }
    X getValue() {
        return value;
    }
}
