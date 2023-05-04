import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, BankAccount> accountsDatabase = new LinkedHashMap<>();

        String continueSimulation = "yes";

        while (continueSimulation.equals("yes")) {
            System.out.println();
            System.out.println("Hello. What would you like to do?");
            System.out.println();
            System.out.println("A. Log in with your ID");
            System.out.println("B. Create an account");
            String startInput = twoOptionsCheck("a", "b");

            String inputID = "";

            if (startInput.equals("b")) {
                newAccount(accountsDatabase);
                inputID = String.valueOf(accountsDatabase.size());
            } else {
                System.out.println("Please enter your ID");
                inputID = scanner.nextLine();
                while (!accountsDatabase.containsKey(Integer.parseInt(inputID))) {
                    System.out.println("An account with this ID does not exist. What would you like to do?");
                    System.out.println("A. Create a new account");
                    System.out.println("B. Type another ID");
                    String answer = twoOptionsCheck("a", "b");
                    if (answer.equals("a")) {
                        newAccount(accountsDatabase);
                        inputID = String.valueOf(accountsDatabase.size());
                        break;
                    } else {
                        System.out.println("Please enter your ID:");
                        inputID = scanner.nextLine();
                    }
                }
            }


            BankAccount currentUser = accountsDatabase.get(Integer.parseInt(inputID));
            System.out.println();
            System.out.println("Welcome "
                    + currentUser.customersTitle
                    + ". " + currentUser.getCustomersFamilyName());
            System.out.println();

            String input = whatActionToBeDone();

            String answer = "yes";

            while (answer.equals("yes")) {

                switch (input) {
                    case "1":
                        System.out.println("How much would you like to withdraw?");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        currentUser.withdraw(withdrawAmount);
                        System.out.println("Would you like to take another action?");
                        answer = twoOptionsCheck("yes", "no");
                        if (answer.equals("yes")) {
                            input = whatActionToBeDone();
                        }
                        break;
                    case "2":
                        System.out.println("How much would you like to deposit?");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        currentUser.deposit(depositAmount);
                        System.out.println("Would you like to take another action?");
                        answer = twoOptionsCheck("yes", "no");
                        if (answer.equals("yes")) {
                            input = whatActionToBeDone();
                        }
                        break;
                    case "3":
                        System.out.println("Your balance is: " + currentUser.balanceEnquiry());
                        System.out.println("Would you like to take another action?");
                        answer = twoOptionsCheck("yes", "no");
                        if (answer.equals("yes")) {
                            input = whatActionToBeDone();
                        }
                        break;
                    case "4":
                        System.out.println("Write the ID of the account to which you want to transfer funds.");
                        int idToTransferTo = checkID(accountsDatabase);
                        if (idToTransferTo == 123456789) {
                            break;
                        } else if (idToTransferTo == currentUser.customerID) {
                            System.out.println("This is your ID");
                            System.out.println("Would you like to take another action?");
                            answer = twoOptionsCheck("yes", "no");
                            if (answer.equals("yes")) {
                                input = whatActionToBeDone();
                            }
                            break;
                        } else {
                            String nameToTransferTo = accountsDatabase.get(idToTransferTo).customersFirstName;
                            String familyNameToTransferTo = accountsDatabase.get(idToTransferTo).customersFamilyName;
                            System.out.println("How much would you like to transfer to " + nameToTransferTo + " " + familyNameToTransferTo + "?");
                            double toTransfer = Double.parseDouble(scanner.nextLine());
                            currentUser.withdraw(toTransfer);
                            accountsDatabase.get(idToTransferTo).deposit(toTransfer);
                            System.out.println("Transaction completed.");
                            System.out.printf("You transferred %.2f euro to %s %s.%n", toTransfer, nameToTransferTo, familyNameToTransferTo);
                        }
                        System.out.println("Would you like to take another action?");
                        answer = twoOptionsCheck("yes", "no");
                        if (answer.equals("yes")) {
                            input = whatActionToBeDone();
                        }
                        break;
                    case "5":
                        answer = "no";
                        break;
                }
            }
            System.out.println("Thank you! Have a nice day!");
            System.out.println("Thanks for trying my Banking app. Would you like to continue the simulation?");
            continueSimulation = twoOptionsCheck("yes", "no");
        }
        System.out.println("If you have some feedback, please send it to: georgi.iliev9191@gmail.com");
        System.out.println("Have a lovely day.");
    }

    public static void newAccount(Map<Integer, BankAccount> accountsDatabase) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How should we address you? Mr, Mrs, or Mx ?");
        String title = correctTitleInput();
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter your family name: ");
        String familyName = scanner.nextLine();
        BankAccount newUser = new BankAccount(title, firstName, familyName);
        System.out.println("Welcome! Your ID is: " + newUser.getCustomerID());
        accountsDatabase.put(newUser.getCustomerID(), newUser);
    }

    public static String correctTitleInput() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("Mr") && !input.equals("Mrs")
                && !input.equals("Mx")) {
            System.out.println("Incorrect title. Please choose between Mr, Mrs or Mx");
            input = scanner.nextLine();
        }
        return input;
    }

    public static String correctActionInput() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("1")
                && !input.equals("2")
                && !input.equals("3")
                && !input.equals("4")
                && !input.equals("5")) {
            System.out.println("Incorrect number. Please choose the number of the action which you would like to do.");
            input = scanner.nextLine();
        }
        return input;
    }

    public static String twoOptionsCheck(String optionOne, String optionTwo) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().toLowerCase();
        System.out.println();

        while (!input.equals(optionOne) && !input.equals(optionTwo)) {
            System.out.println("Only " + optionOne + " or " + optionTwo + ", please.");
            input = scanner.nextLine();
        }
        return input;
    }

    public static String whatActionToBeDone() {
        System.out.println("What would you like to do? Please choose the number.");
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance Inquiry");
        System.out.println("4. Transfer");
        System.out.println("5. Log out");

        String input = correctActionInput();
        return input;
    }

    public static int checkID(Map<Integer, BankAccount> accountsDatabase) {
        Scanner scanner = new Scanner(System.in);
        String answer = "yes";

        while (answer.equals("yes")) {
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. An ID can consist only of numbers. Try again.");
                scanner.nextLine();
            }

            int input = scanner.nextInt();

            if (accountsDatabase.containsKey(input)) {
                return input;
            } else {
                System.out.println("Such an ID does not exist. Do you want to try again?");
                answer = twoOptionsCheck("yes", "no");
                System.out.println("Type the ID of the account to which you want to transfer funds.");
            }
        }
        return 123456789;
    }
}
