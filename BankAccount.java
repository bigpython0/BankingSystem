import java.util.Scanner;
@SuppressWarnings("unused")


public class BankAccount {
    private double balance;
    Scanner scanner = new Scanner(System.in);

    public BankAccount(double balance) { //constructor
        this.balance = balance;
    }

    public void printBalance() {
        System.out.println("Your current balance is "+this.balance+".");
    }

    public double getBalance() {
        return this.balance;
    }

    public void changeBalance(double amount) {
        this.balance = this.balance + amount; 
        if (this.balance < 0) {
            System.out.println("You are " +java.lang.Math.abs(this.balance)+"€ in depth.");
            return;
        }
    }

    public double deposit(double amount) {
        changeBalance(amount);
        return this.balance;
    }

    public double withdraw(double amount) {
        changeBalance(-amount);
        return this.balance;
    }

    public boolean sendMoney(Bank bank, double amount, String recipientName) {
        //insufficient balance?
        if(amount > this.balance) {
            return false;
        }

        if((bank.getCustomerAccountByName(recipientName))!= null) {
            bank.getCustomerAccountByName(recipientName).changeBalance(amount);
            this.changeBalance(-amount); //set balance
            //System.out.println("Successfully sent "+amount+ " Euros.");
        }

        return false;
    }
}