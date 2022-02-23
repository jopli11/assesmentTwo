import java.io.*;
import java.security.*;
import java.util.*;
import java.util.Map.Entry;
import javax.crypto.*;

/**
 *
 * @author Joel Parfitt - 23020948
 */

// Start of Program -----------------------------

public class User {

    // set external location for file
    final static String extFilePath = "C:\\Users\\joelj\\Desktop\\USW Details\\hasheddetails.txt";


    // global variable declaration
    static String firstName;
    static String lastName;
    static String password;

    static int userType;
    static int uswID;
    static String pinNum;
    static int age;
    static int firstNameIndex = 1;
    static int lastNameIndex = 2;
    static int passwordIndex = 3;
    static int ageIndex = 4;
    static int userTypeIndex = 5;

    static String encryptedUsername;
    static String encryptedPassword;
    static String encryptedName;
    static String encryptedUserType;
    static String encryptedFirstName;
    static String encryptedLastName;
    static int encryptedUswID;
    static String encryptedAge;

    /////

    // setters and getters

    public static String getEncryptedPassword() {
        return encryptedPassword;
    }

    public static void setEncryptedPassword(String encryptedPassword) {
        User.encryptedPassword = password;
    }

    public static void setEncryptedFirstName(String encryptedFirstName) {
        User.encryptedFirstName = firstName;
    }

    public static String getEncryptedFirstName() {
        return encryptedFirstName;
    }

    public static String getEncryptedLastName() {
        return encryptedLastName;
    }

    public static void setEncryptedLastName(String encryptedlastName) {
        User.encryptedLastName = lastName;
    }

    public static String getEncryptedUserType() {
        return encryptedUserType;
    }

    public static void setEncryptedUserType(String string) {
        User.encryptedUserType = string;
    }

    public static int getEncryptedUswID() {
        return User.encryptedUswID;
    }

    public static void setEncryptedUswID(int encryptedUswID) {
        User.encryptedUswID = encryptedUswID;
    }
    public static String getEncryptedAge() {
        return encryptedAge;
    }

    public static void setEncryptedAge(String string) {
        User.encryptedAge = string;
    }

    // end of encrypted setters n getters
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

    ///// end of setters & getters

    /*
     * login method ----
     * 
     * 
     */
    public static void login() throws IOException {

        HashMap<Integer, ArrayList<Object>> userData = new HashMap<>(); // instantiate HashMap for userData
        File userDataFile = new File(extFilePath);

        BufferedReader loginReader;
        loginReader = new BufferedReader(new FileReader(userDataFile));
        String line;

        try {
            while ((line = loginReader.readLine()) != null) {
                String[] parts = line.split(":");
                setUswID(Integer.parseInt(parts[0].trim()));
                String toBeSplit = parts[1];

                ArrayList<Object> forHM = null;
                for (int i = 0; i < 5; i++) {
                    forHM = new ArrayList<Object>();

                    if (i == firstNameIndex) {
                        setEncryptedFirstName(
                                ((toBeSplit.split(","))[firstNameIndex])
                                        .substring(1));
                        continue;      
                    }
                    if (i == lastNameIndex) {
                        setEncryptedLastName(toBeSplit.split(",")[lastNameIndex]);
                        continue;
                    }
                    if (i == passwordIndex) {
                        setEncryptedPassword(toBeSplit.split(",")[passwordIndex]);

                    }
                    if (i == ageIndex) {
                        setEncryptedAge((toBeSplit.split(","))[userTypeIndex]);
                        continue;
                    }
                    if (i == userTypeIndex) {
                        setEncryptedUserType((toBeSplit.split(", ")[passwordIndex].substring(0,((toBeSplit.split(",")[3]).length() - 2))));
                        continue;
                    }
                    forHM.add(getEncryptedFirstName());
                    forHM.add(getEncryptedLastName());
                    forHM.add(getEncryptedPassword());
                    forHM.add(getEncryptedAge());
                    forHM.add(getEncryptedUserType());

                }

                userData.put(getUswID(), forHM);
            }
        } catch (Exception e) {
        }
        System.out.println(userData);
    }
    /*
     * HashMap<Integer, ArrayList<String>> decryptedUserData = new HashMap<>();
     * Set<Integer> keySet = decryptedUserData.keySet(); // (Saeed, 2020)
     * 
     * ArrayList<Integer> userID = new ArrayList<>(keySet); // (GeeksforGeeks, 2021)
     * 
     * 
     * String encryptedUserName =
     * encryptedData.get(currentUserID).get(User.getUserNameIndex()).toString();
     * String encryptedUserType =
     * encryptedData.get(currentUserID).get(User.getUserTypeIndex()).toString();
     * String encryptedName =
     * encryptedData.get(currentUserID).get(User.getUserNameIndex()).toString();
     * String encryptedPassword =
     * encryptedData.get(currentUserID).get(User.getPasswordIndex()).toString();
     * }
     * 
     * System.out.println(decryptedUserData);
     */

    // function to store the vowels and return as a string - not as index value
    public static boolean vowelFunc(char c) {
        return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
    }

    /*
     * register method ----
     * 
     * loop through user inputs and store variaables
     * set each variable in golbal setters
     * generate secret key / iVParameter and define AES algorithm
     * get each set varaible
     * encrypt everything except UswID
     * instantiate hashmap with an integer and arraylist<object>
     * set file path for generated file
     * fill arraylist with encrypted data
     * loop through data and print into file
     * 
     */

    public static void register() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {

        // user input        
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

            // password validation method
            Validation.passCheck(passLength, upperChar, num, sym);

            // user input
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

        final String secretKey = "ssshhhhhhhhhhh!!!!"; // declare secret key

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

        // start of calculations to generate pin

        System.out.println("\n" + "Generating secret pin number..."); // user directions

        System.out.println("Please make note of your secret pin number : " + finalPin); // user directions
        System.out.println("This will be used to retrieve your personal data." + "\n"); // user directions
        setPinNum(finalPin);
    }
}

/**
 * References
 * 
 * https://docs.oracle.com/javase/tutorial/java/data/manipstrings.html // vowel
 * function
 * https://www.baeldung.com/java-aes-encryption-decryption // AES algorithm
 * 
 */
