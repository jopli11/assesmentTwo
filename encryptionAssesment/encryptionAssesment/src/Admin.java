import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Joel Parfitt - 23020948
 */

// Start of Program -----------------------------
public class Admin {

    // set external location for file
    final static String extFilePath = "C:\\Users\\joelj\\Desktop\\USW Details\\hasheddetails.txt";

    static String firstName;
    static String lastName;
    static String password;

    static int userType;
    static int uswID;
    static String pinNum;
    static int age;

    // start oif setters and getters

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static int getUserType() {
        return userType;
    }

    public static void setUserType(int userType) {
        User.userType = userType;
    }

    public static int getUswID() {
        return User.uswID;
    }

    public static void setUswID(int uswID) {
        User.uswID = uswID;
    }

    public String getPinNum() {
        return User.pinNum;
    }

    public static void setPinNum(String finalPin) {
        User.pinNum = finalPin;
    }

    public static int getAge() {
        return User.age;
    }

    public static void setAge(int age) {
        User.age = age;
    }

    // end of setters and getters

    public static void systemAnnouncement() {
        final JPanel panel = new JPanel();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter annoucment to broadcast to all users:");
            String announcement = scanner.nextLine();

            JOptionPane.showMessageDialog(panel, announcement, "Warning",
                    JOptionPane.WARNING_MESSAGE);
        } catch (HeadlessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // function to store the vowels and return as a string - not as index value
    public static boolean vowelFunc(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    public static void addUser() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print(" Enter First Name : ");
            String firstName = scanner.nextLine();
            setFirstName(firstName);

            System.out.print(" Enter Last Name : ");
            String lastName = scanner.nextLine();
            setLastName(lastName);

            System.out.print(" Enter Password : ");
            String password = scanner.nextLine();
            setPassword(password);

            // declare counts and mimimum password length
            int passwordLength = 14, upperChar = 0, lowerChar = 0;
            int sym = 0, num = 0;
            char charAt;

            int passLength = password.length(); // get password length and store in passLength

            // if mimumum length is not met print user directions.
            if (passLength <= passwordLength) {

                System.out.println("\nPassword is invalid, it has to be atleast 14 characters long.");
                System.out.println("Please retry until password strength is Strong.");
                return;

                // else count the characters in the word
            } else {
                for (int i = 0; i < passLength; i++) {

                    charAt = password.charAt(i);
                    if (Character.isUpperCase(charAt))
                        upperChar++;
                    else if (Character.isLowerCase(charAt))
                        lowerChar++;
                    else if (Character.isDigit(charAt))
                        num++;
                    else {
                        sym++;
                    }

                }
            }

            Validation.passCheck(passLength, upperChar, num, sym);

            System.out.print(" Enter USW ID : ");
            int uswID = scanner.nextInt();
            setUswID(uswID);

            System.out.print(" Enter age : ");
            int age = scanner.nextInt();
            setAge(age);

            System.out.print("\n Account types - :");
            System.out.print("\n Account Type 1 = Admin \n Account Type 2 = Lecturer \n Account Type 3 = Student");
            System.out.print("\n Enter your associated account type:");
            int userType = scanner.nextInt();
            setUserType(userType);
        }

        final String secretKey = "ssshhhhhhhhhhh!!!!";

        // pre-encrypt varaibles
        String first = getFirstName();
        String last = getLastName();
        String pass = getPassword();

        // instantiate string varaibles of the encrypted data
        String firstEncrypt = AES.encrypt(first, secretKey);
        String lastEncrypt = AES.encrypt(last, secretKey);
        String passEncrypt = AES.encrypt(pass, secretKey);

        HashMap<Integer, ArrayList<Object>> userData = new HashMap<>(); // instantiate HashMap for userData
        File userDataFile = new File(extFilePath); // instantiate file variable and save location

        BufferedWriter bf = null; // instantiate bf

        // get the user type - encrypt it as a string value
        int userType = getUserType();
        String userEncrypt = AES.encrypt(String.valueOf(userType), secretKey);

        // get the user type - encrypt it as a string value
        int age = getAge();
        String ageEncrypt = AES.encrypt(String.valueOf(age), secretKey);

        // secret pin generation ----

        int count = 0; // instantiate count variable
        int ageReversed = 0;

        // loop through the fullname string and + count for each vowel
        // found in the vowelFunc function created.
        for (int i = 0; i < firstName.length(); i++) {

            if (vowelFunc(firstName.charAt(i))) // Returns the char value at the specified index.
                count++;
        }

        // while loop to reverse the age
        while (age != 0) {

            int rev = age % 10;
            ageReversed = ageReversed * 10 + rev;
            age /= 10;

        }

        int pinNumber = count * ageReversed; // calculate pin number
        String finalPin = String.format("%04d", pinNumber); // format the value into a 4 digit format

        // end of secret pin generation ----

        // we have to create an array list to then have this as the value in the
        ArrayList<Object> toBeStored = new ArrayList<>();
        toBeStored.add(firstEncrypt);
        toBeStored.add(lastEncrypt);
        toBeStored.add(passEncrypt);
        toBeStored.add(ageEncrypt);
        toBeStored.add(userEncrypt);
        userData.put(getUswID(), toBeStored);

        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(userDataFile, true));

            // iterate map entries
            for (Entry<Integer, ArrayList<Object>> entry : userData.entrySet()) {

                // put key and value separated by a :
                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                // always close the writer
                bf.close();
            } catch (Exception e) {
            }
        }

        // user direction
        System.out.print("\n" + " User Created : ");
        System.out.println(
                "\n" + first + ":" + last + ":" + pass + ":" + String.valueOf(uswID) + ":" + String.valueOf(userType));

    }

}
