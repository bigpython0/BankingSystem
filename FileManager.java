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
        try {BufferedWriter writer = new BufferedWriter(new FileWriter("Customers.txt"));
            for (Customer customer : customers) {
                if (customer == null) {continue;}
                writer.write("Customer #"+customer.getcustID());
                writer.write("\n"+customer.getName());
                writer.write("\n"+customer.getPin());
                writer.write("\n" + Double.toString(customer.getAccount().getBalance()) + "\n\n");
            }
            writer.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void loadCustomersFromFile(Bank bank) {
    try (BufferedReader reader = new BufferedReader(new FileReader("Customers.txt"))) {
        String line;
        int loaded = 0;

        while ((line = reader.readLine()) != null) {
            if (!line.startsWith("Customer #")) continue;

            String idStr = line.substring("Customer #".length()).trim(); // "000" -> "000"
            int custID = Integer.parseInt(idStr); // becomes 0 (that’s fine)

            String name = reader.readLine();
            String pin  = reader.readLine();
            String balLine = reader.readLine();
            reader.readLine(); // consume separator blank line (safe even at EOF)

            if (name == null || pin == null || balLine == null) continue;
            name = name.trim();
            pin  = pin.trim();
            if (name.isEmpty() || pin.isEmpty()) continue;

            double loadedBalance = Double.parseDouble(balLine.trim());

            Customer customer = new Customer(name, pin);
            customer.setCustID(custID);
            bank.addCustomer(customer);

            // Set exact balance on the account we just created
            BankAccount acc = customer.getAccount();
            double delta = loadedBalance - acc.getBalance(); // acc likely starts at 0
            if (delta != 0.0) acc.changeBalance(delta);

            loaded++;
        }

        // TEMP debug: prove you loaded something
        System.out.println("Loaded customers: " + loaded);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}