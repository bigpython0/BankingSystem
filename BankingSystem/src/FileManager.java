import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("unused")

public class FileManager {
    public static void saveCustomersToFile(Customer[] customers, Bank bank) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt"));

            for (Customer customer : customers) {
                if (customer == null) {continue;}
                writer.write("Customer #"+customer.getcustID());
                writer.write("\n"+customer.getname());
                writer.write("\n"+customer.getPin());
                writer.write("\n"+Double.toString(bank.getCustomerAccountByName(customer.name).getBalance())+"\n\n");
            }
            writer.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadCustomersFromFile( Bank bank) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Customers.txt"));
            String line;
            while((line = reader.readLine()) != null)
            if (line.startsWith("Customer #")) {
                String idStr = line.substring("Customer #".length());
                int custID = Integer.parseInt(idStr);

                String name = reader.readLine();
                String pin = reader.readLine();

                String balanceLine = reader.readLine();
                if (balanceLine == null || balanceLine.trim().isEmpty()) {
                    System.err.println("Error: Balance line is empty for customer " + name);
                    continue; // skip this customer
                }
                
                double balance = Double.parseDouble(balanceLine.trim());
              
                Customer customer = new Customer(name, pin);
                bank.addCustomer(customer); 
                customer.setCustID(custID);
                bank.getCustomerAccountByName(name).changeBalance(balance);

                reader.readLine(); //skip empty
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
