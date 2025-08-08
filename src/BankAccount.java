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

    public void deposit() {
        System.out.print("Enter amount to deposit: ");
        double moneyDeposit = scanner.nextDouble();
        scanner.nextLine(); //catch \n
        changeBalance(moneyDeposit);
    }

    public void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double moneyWithdrawal = scanner.nextDouble();
        scanner.nextLine(); //catch \n
        changeBalance(-moneyWithdrawal);
    }

    public void sendMoney(Bank bank) {//receiver name
        System.out.print("Enter amount to transfer: ");
        double moneyToTransfer;

        //insufficient balance?
        if((moneyToTransfer = scanner.nextDouble()) > this.balance) {
            System.out.println("thats a little much, dont you think... :)");
        }
        scanner.nextLine();

        System.out.println("Enter recipient name: ");
        String recipientName = scanner.nextLine();
        if((bank.getCustomerAccountByName(recipientName))!= null) {
            bank.getCustomerAccountByName(recipientName).changeBalance(moneyToTransfer);
            this.changeBalance(-moneyToTransfer); //set balance
            System.out.println("Successfully sent "+moneyToTransfer+ " Euros.");
        } else {
            System.out.println("you sure you wanna send this to "+ recipientName+"??");
            if (scanner.nextLine() != "no") {
                Main.printLoading();
                System.out.println("youre dumb\n");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}