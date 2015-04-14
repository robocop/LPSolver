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



            objective.toString();
            equ1.toString();
            equ2.toString();
            equ3.toString();

            DictionaryEntry[] eqs = new DictionaryEntry[] {equ1, equ2, equ3};

            Simplex s = new Simplex(objective, eqs, true);
            SimplexOutput solution = s.solve();

            solution.print();

        }
        catch (DictionaryEntryException e) {

        }
        catch (LinearCombinationException e) {

        }



    }
}
