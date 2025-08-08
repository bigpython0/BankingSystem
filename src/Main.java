import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(GUI::new); //use EDT thread for GUI

        Scanner scanner = new Scanner(System.in);
        Bank Bank1 = new Bank("TargoBank",5);
        System.out.print("Welcome to ");
        Bank1.printBankName();
        FileManager.loadCustomersFromFile(Bank1);
        
        //new customer method
        customerRegistration(scanner, Bank1);

        //login
        BankAccount currentCustomerAccount = null;
        if ((currentCustomerAccount = customerLogin(scanner, Bank1))!= null) {
            loggedInSession(currentCustomerAccount, Bank1, scanner);
            System.out.println("logged out bye bye");
        } else {
            System.out.println("couldnt log in, try again");
            scanner.close();
        }
    }

    public static void printLoading(){
        try{
            Thread.sleep(700);
            System.out.print(".");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(700);
            System.out.print(".\n");
        } catch (InterruptedException e) {
            System.out.println("Loading interrupted.");
        }
    }

    public static BankAccount customerLogin(Scanner scanner, Bank Bank1) {
        System.out.println("Enter name: ");
        String nameLogin = scanner.nextLine();
        //check if user exists

        System.out.println("Enter your 4-digit pin: ");
        String pinLogin = scanner.nextLine();
        return checkLogin(Bank1, pinLogin, nameLogin);
    }
    
    public static BankAccount checkLogin(Bank Bank1, String pinLogin, String nameLogin) {
        BankAccount currentCustomerAccount = Bank1.getCustomerAccountByName(nameLogin);
        if (Bank1.getCustomerByName(nameLogin).getPin().equals(pinLogin)) {
            System.out.println("Your current balance is: "+ currentCustomerAccount.getBalance());
            return currentCustomerAccount;
        } 
        return null; //failed login
    }

    public static void customerRegistration(Scanner scanner, Bank Bank1) {
        System.out.print(".\n Are you a new customer?\nYes --> y\n No --> n\n");
        String OpenAccount = scanner.nextLine();
        if (OpenAccount.equalsIgnoreCase("y")|| OpenAccount.equalsIgnoreCase("yes")){
            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Choose a 4 digit pin: ");
            String pin = scanner.nextLine();
            //ich gehe erstmal davon aus dass es wirklich richtig ist

            Customer cust = new Customer(name, pin);
            Bank1.addCustomer(cust);
            System.out.println("Your customer ID is: " + cust.getcustID());
        } else {
            System.out.print("Proceeding to login");
            printLoading();
        }
    }

    

    static void loggedInSession(BankAccount currentCustomerAccount, Bank Bank1, Scanner scanner) {
        boolean loggedIn = true;
            while ( loggedIn ) {
                System.out.println("What would you like to do?");
                System.out.println("(1) Deposit \n(2) Withdraw\n(3) Transfer\n(4) Log out\n");
                int action = scanner.nextInt();
                scanner.nextLine();

                switch(action) {
                    case 1:
                    currentCustomerAccount.deposit();
                    System.out.println("Updated balance: "+currentCustomerAccount.getBalance()+"\n");
                    break;

                    case 2:
                    currentCustomerAccount.withdraw();
                    System.out.println("Updated balance: "+currentCustomerAccount.getBalance()+"\n");
                    break;

                    case 3:
                    //idk if im gonna add more banks but for now ts will work
                    currentCustomerAccount.sendMoney(Bank1); //cant transfer to self
                    break;

                    case 4:
                    System.out.println("Logging out...");
                    FileManager.saveCustomersToFile(Bank1.getCustomerArray(), Bank1);
                    System.out.print("Saving customer data"); 
                    printLoading();
                    loggedIn = false;
                    break;

                    default: 
                    System.out.println("Invalid action. Try again blödmann");
                    break;
                }
            }
    }
}
