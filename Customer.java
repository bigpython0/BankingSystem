public class Customer {
        private int custID;
        String name;
        /*String address;
        int phoneNum;*/
        private String pin;
        private BankAccount account;
        //jeder customer hat ein BankAccount, das hier hinzufügen?

    public Customer(String name, String pin) {
        this.custID = CustomerID.findNextFreeID();
        this.name = name;
        //this.address = address;
        //this.phoneNum = phoneNum;
        this.account = new BankAccount(0);
        this.pin = pin;
    }
    //___________________________print variables_________________________
    public int getcustIDunformatted() {
            return this.custID; 
        }    

    public String getPin() {
        return this.pin;
    }


    public String getcustID() {
        return String.format("%03d",this.custID); 
    }

    public void setCustID(int id) {
        this.custID = id;
    }

    public String getName() {
        return this.name;
    }

    /*public String getAddress() {
        return this.address;
    }

    public int getNum() {
        return this.phoneNum;
    }*/

    public BankAccount getAccount(){
        return this.account;
    }

}

