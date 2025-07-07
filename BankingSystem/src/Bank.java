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
        for (int i = 0; i<customers.length;i++){
            System.out.println(this.customers[i].name); //kann noch zusatzinfos printen
        }
    }

    public Customer getCustomer(String name){
        for (int i = 0; i<maxCustomers;i++) {
            if (customers[i].getname().equalsIgnoreCase(name)) {
                return customers[i];
            }
        }
        System.out.println("No customer with given name.");
        return null;
    }

    public BankAccount getCustomerAccountByName(String name) {
        for (Customer customer : customers) {
        if (customer != null && customer.getname().equals(name)) {
            return customer.getAccount();
        }
        }
        return null; // or throw an exception if not found
    }

    public Customer getCustomerByName(String name) {
        for (int i = 0; i<this.customers.length;i++) {
            if (customers[i]==null) {
                continue;
            } 
            if (customers[i].name.equalsIgnoreCase(name)) {
                return customers[i];
            }
        }
        System.out.println("Couldnt find a customer named "+name+".");
        return null;
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
            System.out.println("Bank is already at full capacity.");
            return;
        }else {
            //CustomerID.assignCustomerID(customers[curCustomers]);
            this.customers[curCustomers] = customer; //Customer in Liste
            curCustomers++;
        }
    }

    public void removeCustomer(Customer customer) {
        if (this.customers[customer.getcustIDunformatted()] != null) {
            this.customers[customer.getcustIDunformatted()] = null;
        }else {
            System.out.println("Specified User can't be found");
        }
    }
}
    
