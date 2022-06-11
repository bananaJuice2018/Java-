package Ray;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        System.out.println("Enter password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        User user = new User(password);
        System.out.println(user); // Before the Hash.
        try {
            NSALoginController.hashUserPassword(user);  // Static function, use Class dot function.
            System.out.format("\tPassword After Hash: %s\n", user.getPassword());
            System.out.format("\tHashed Password: %s\n", user.getHashedPassword());
            System.out.format("\tSalt: %s\n", user.getSalt());
        } catch (WeakPasswordException ex) {
            System.out.println("Password rejected: " + ex.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Something is wrong!");
        }
        System.out.println(user);

        // Verify the password.
        System.out.print("Enter your password: ");
        password = scanner.nextLine();
        user.setPassword(password);

        try {
            if (NSALoginController.verifyPassword(user))
                System.out.println("Login Successful!");
            else
                System.out.println("Login Failed!");
        } catch (Exception e) {
            System.out.println("Soemthing went wrong");
        }
    }
}




