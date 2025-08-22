public class CustomerID {
    private static boolean[] customerIDs;

    public CustomerID(int maxCustomers) {
        customerIDs = new boolean[maxCustomers]; //boolean array with all false
    }

    public static synchronized int findNextFreeID() {
        for (int i = 0; i<customerIDs.length; i++) { // search for first free ID
            if (customerIDs[i]==false) {
                return i;
            }
        }
        System.out.println("No free Customer IDs.");
        return -1;
    }

    public synchronized void assignCustomerID(Customer customer) {
        int FreeID = findNextFreeID();        
        customerIDs[FreeID] = true;
        customer.setCustID(FreeID); // index is customer ID
        return;
    }
}