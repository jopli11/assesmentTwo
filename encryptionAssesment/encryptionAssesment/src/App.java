import java.util.Scanner;

/**
 *
 * @author Joel Parfitt - 23020948
 */

// Start of Program -----------------------------

public class App {
    public static void main(String[] args) throws Exception {

        // data declerations

        System.out.println("Welcome to the USW Degree Apprenticeship App\n"); // user directions
        System.out.println("Please enter 1 if you want too login or 2 to register below:"); // user directions

        try (Scanner userInput = new Scanner(System.in)) {
            int i = userInput.nextInt(); // declare and store age variable

            // if input is "1" call login class else call registration class
            // else throw exception
            if (i == 1) {
                User.login(); // 11 call user class & login method

                int userType = User.getUserType(); // get set usertype from login method

                if (userType == 1) { // if decrypted user type = Admin
                    System.out.println("Welcome to the ADMIN USW Portal\n"); // user directions
                    Admin.systemAnnouncement(); // allow annoucment
                    Admin.addUser(); // allow admin to add user

                } else if (userType == 2) { // if decrypted user type = Lecturer
                    System.out.println("Welcome to the Lecturer USW Portal\n"); // user directions
                    Lecturer.moduleAnnouncement();

                } else if (userType == 3){
                    System.out.println("Welcome to the Student USW Portal\n"); // user directions
                    // allow student to view decrypted info
                }

            } else if (i == 2) {
                User.register(); // call user class & register method

            } else {
                System.out.println("1 or 2 has not been entered, please try again."); // user directions
            }
        }
    }
}
