package fr.enslyon;

public class Main {

    public static void main(String[] args) {


        try {
            // Max 2x_0 + x_1
            LinearCombination objective = new LinearCombination(2, 5);
            objective.setConstant(0);
            objective.setConstants(new double[]{2, 1});
            objective.setVariables(new int[]{0, 1});

            //st -2x_0 + x_1 <= -2
            // x_2 = -2 + 2x_0 - x_1
            DictionaryEntry equ1 = new DictionaryEntry(2, 5, 2);
            equ1.setConstant(-2);
            equ1.setConstants(new double[]{2, -1});
            equ1.setVariables(new int[]{0, 1});

            //st x_0 - 2 x_1 <= -2
            // x_3 = -2 - x_0 + 2x_1
            DictionaryEntry equ2 = new DictionaryEntry(2, 5, 3);
            equ2.setConstant(-2);
            equ2.setConstants(new double[]{-1, 2});
            equ2.setVariables(new int[]{0, 1});

            //st x_0 + x_1 <= 7
            // x_3 = 7 - x_0 - x_1
            DictionaryEntry equ3 = new DictionaryEntry(2, 5, 4);
            equ3.setConstant(7);
            equ3.setConstants(new double[]{-1, -1});
            equ3.setVariables(new int[]{0, 1});



            objective.print();
            equ1.print();
            equ2.print();
            equ3.print();

            DictionaryEntry[] eqs = new DictionaryEntry[] {equ1, equ2, equ3};

            Simplex s = new Simplex(objective, eqs);
            SimplexOutput solution = s.solve();

            solution.print();


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
