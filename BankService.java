import java.math.BigDecimal;

public class BankService {
    private final Bank bank;
    

    public BankService(Bank bank){
        this.bank = bank;
    }

    public BankAccount tryLogin(String username, String pin) {
     bank.printCustomers(); 
     Customer customer = bank.getCustomerByName(username);
      if (customer == null) {
        //System.out.println("no user with that name");
        return null;
      }

      if(customer.getPin().equals(pin)) {
        return customer.getAccount();
      } else {
        System.out.println("Wrong pin");
        return null;
      }
   }

   public void loadCustomers() {
        FileManager.loadCustomersFromFile(bank);
   }

   public void saveCustomers() {
        FileManager.saveCustomersToFile(bank.getCustomerArray(), bank);
   }

   public Customer registerCustomer(String name, String pin) {
     Customer customerToAdd = new Customer(name, pin);
     bank.addCustomer(customerToAdd);
     return customerToAdd;
   }

   public BigDecimal deposit(BankAccount account, BigDecimal amount) {  
     if (amount.compareTo(new BigDecimal(0)) <= 0) {
          //throw error
          return account.getBalance();
     }
     return account.deposit(amount);
   }

   public BigDecimal withdraw(BankAccount account, BigDecimal amount) {
     if (amount.compareTo(account.getBalance()) > 0) {
               //throw error
     } 
     return account.withdraw(amount); //account.withdraw returns updated balance
   }

   public boolean transfer(BankAccount fromAccount, String toCustomerName, BigDecimal amount) {
        if(fromAccount.sendMoney(bank, amount, toCustomerName)== false) {
            return false;
        }
        return true;
   }
}
