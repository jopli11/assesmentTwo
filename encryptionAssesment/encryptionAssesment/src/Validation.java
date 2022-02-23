/**
 *
 * @author Joel Parfitt - 23020948
 */

// Start of Program -----------------------------

public class Validation {
    public static void passCheck(int passLength, int upperChar, int num, int sym) {

        // validation required from cover sheet (weak, medium & strong)
        if (passLength >= 18 && upperChar >= 4 && num >= 3 && sym >= 3) {
            System.out.println("\nPassword has saved and is of Strong strength.");
        } else if (passLength >= 16 && upperChar >= 2 && num >= 2 && sym >= 2) {
            System.out.println("\nPassword has has saved and is of Medium strength.");
        } else if (passLength >= 14 && upperChar >= 1 && num >= 1 && sym >= 1) {
            System.out.println("\nPassword has has saved and is of Weak strength.");
        }

    }
}
