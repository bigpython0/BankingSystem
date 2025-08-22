public class Bank{
    private String BankName; 
    private Customer[] customers; 
    private int maxCustomers;
    private int curCustomers;
    @SuppressWarnings("unused")
    private CustomerID idManager; //????
    //array an customern

    public Bank(String BankName, int maxCustomers) {//, array
        this.BankName = BankName;
        this.customers = new Customer[maxCustomers]; //besser als arraylist?
        this.maxCustomers = maxCustomers;
        this.curCustomers = 0;
        idManager = new CustomerID(maxCustomers);
    }

    public Customer[] getCustomerArray(){
        return customers;
    }

    public void printBankName() {
        System.out.print(this.BankName);
    }

    public void printCustomers() {
        System.out.println("List of all Customers:");
        for (int i = 0; i< curCustomers;i++){
            Customer c = customers[i];
            if (c != null) {
                System.out.println(c.getName());
            }
        }
    }

    public Customer getCustomerByName(String name) {
        if (name == null) {
            System.out.println("name is null");
            return null;
        }

        for (int i = 0; i<curCustomers;i++) {
            Customer c = customers[i];
            if (c == null) continue; 
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public BankAccount getCustomerAccountByName(String name) {
        Customer c = getCustomerByName(name);
        return (c != null) ? c.getAccount() : null;
    }

    

    public Customer getCustomerByID(int ID) {
        for (int i = 0; i<curCustomers;i++) {
            if (customers[i].getcustIDunformatted()==ID) {
                return customers[i];
            }
        }
        return null;
    }

    public int getMaxCustomers(int maxCustomers) {
        return this.maxCustomers;
    }

    //public int AssignCustomerID(Customer customers){}

    public void addCustomer(Customer customer) {//eig noch phoneNum und address
        if (this.curCustomers==maxCustomers) {//muss arr nich reinpassen, ist gleiche klasse oder?
            //System.out.println("Bank is already at full capacity.");
            return;
        }else {
            //CustomerID.assignCustomerID(customers[curCustomers]);
            this.customers[curCustomers] = customer; //Customer in Liste
            curCustomers++;
        }
    }

    public boolean removeCustomer(String name) {
        for ( int i = 0; i < curCustomers; i++) {
            Customer c = customers[i];
            if(c!= null && c.getName().equalsIgnoreCase(name)) {
                customers[i] = null;
                return true;
            }
        }
        return false;
    }
}
    
