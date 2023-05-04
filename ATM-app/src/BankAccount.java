import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BankAccount {

    static int currentHighestID = 0;

    double balance;
    String customersTitle;
    String customersFirstName;
    String customersFamilyName;
    int customerID;

    public BankAccount(String customersTitle, String customersFirstName, String customersFamilyName) {
        this.customersTitle = customersTitle;
        this.customersFirstName = customersFirstName;
        this.customersFamilyName = customersFamilyName;
        currentHighestID++;
        customerID = currentHighestID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomersFamilyName() {
        return customersFamilyName;
    }

    void withdraw (double amount){
        if (amount > 0) {
            double newBalance = balance - amount;
            if (newBalance >= 0) {
                balance = newBalance;
            } else {
                System.out.println("Insufficient funds.");
            }
        } else if (amount == 0) {
            System.out.println("0 is not an amount that can be withdrawn");
        }else  {
            System.out.println("Negative amount cannot be withdrawn");
        }
    }

    void deposit (double amount) {
        if (amount > 0) {
            balance += amount;
        } else if (amount == 0) {
            System.out.println("0 is not an amount that can be deposited");
        }else {
            System.out.println("Negative amount cannot be deposited");
        }
    }

    String balanceEnquiry () {
        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.HALF_DOWN);
        String formattedBalance = df.format(balance);
        String output = formattedBalance + " euro";
        return output;
    }
}
