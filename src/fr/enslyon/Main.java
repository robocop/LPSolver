package fr.enslyon;

public class Main {

    public static void main(String[] args) {


        try {
            // Max 5x_0 + 4x_1 + 3x_2
            LinearCombination objective = new LinearCombination(3, 6);
            objective.setConstant(0);
            objective.setConstants(new double[]{5, 4, 3});
            objective.setVariables(new int[]{0, 1, 2});

            //st 2x_0 + 3_x_1 + x_2 <= 5
            // x_3 = 5 - 2x_0 - 3x_1 -x_2
            DictionaryEntry equ1 = new DictionaryEntry(3, 6, 3);
            equ1.setConstant(5);
            equ1.setConstants(new double[]{-2, -3, -1});
            equ1.setVariables(new int[]{0, 1, 2});

            //st 4x_0+x_1+2x_2 <= 11
            // x_4 = 11-4x_0-x_1 - 2x_2
            DictionaryEntry equ2 = new DictionaryEntry(3, 6, 4);
            equ2.setConstant(11);
            equ2.setConstants(new double[]{-4, -1, -2});
            equ2.setVariables(new int[]{0, 1, 2});

            //st 3x_0+4x_1+2x_2 <= 8
            // x_5 = 8-3x_0-4x_1 - 2x_2
            DictionaryEntry equ3 = new DictionaryEntry(3, 6, 5);
            equ3.setConstant(8);
            equ3.setConstants(new double[]{-3, -4, -2});
            equ3.setVariables(new int[]{0, 1, 2});

            objective.print();
            equ1.print();
            equ2.print();
            equ3.print();

            DictionaryEntry[] eqs = new DictionaryEntry[] {equ1, equ2, equ3};

            Simplex s = new Simplex(3, 3, objective, eqs);
            SimplexOutput solution = s.solve();

            solution.print();
            s.getUnboundedSolution().print();
        }
        catch (DictionaryEntryException e) {

        }
        catch (LinearCombinationException e) {

        }

        //v_4 = 11.0  + -4.0 * v_0  + -1.0 * v_1  + -2.0 * v_2

        //v_0 = 2.5  + -0.5 * v_3  + -1.5 * v_1  + -0.5 * v_2

        // -> v_4 = 1.0  + 2.0 * v_3  + 5.0 * v_1  + 0.0 * v_2


    }
}
