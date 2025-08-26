import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class GUI implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cards;
    private JFrame frame;

    private static final String CARD_START = "START";
    private static final String CARD_SECOND = "SECOND";
    private static final String CARD_THIRD = "THIRD";

    private String bankName = "Targobank"; //only centered for ts
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel submitErrorLabel = new JLabel();

    JLabel balanceLabel = new JLabel("Balance: ");

    private BankService bankService;
    private BankAccount currentAccount;

    public GUI(BankService bankservice) {
        this.bankService = bankservice;
        this.frame = new JFrame("Banking System");

        buildUI();
    }

    private void buildUI(){

               //JFrame frame = new JFrame("Banking System"); //replaces setTitle
        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

             //---start Panel---
        JPanel startPanel = new JPanel();
        startPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        startPanel.setLayout(null);
        
        JButton button = new JButton("START");
        button.addActionListener(this);
        button.setActionCommand("SECOND");
        button.setBounds(140, 130, 280, 40);
        startPanel.add(button);

        JLabel welcomelabel1 = new JLabel("Welcome to");
        welcomelabel1.setForeground(new Color(0, 83, 184));
        welcomelabel1.setFont(welcomelabel1.getFont().deriveFont(30f));
        welcomelabel1.setBounds(200, 10, 300 ,30);
        startPanel.add(welcomelabel1);

        JLabel welcomelabel2 = new JLabel(bankName);
        welcomelabel2.setForeground(new Color(0, 22, 48));
        welcomelabel2.setFont(welcomelabel2.getFont().deriveFont(50f));
        welcomelabel2.setBounds(150, 35, 300 ,70);
        startPanel.add(welcomelabel2);
        
        cards.add(startPanel, CARD_START);

               //--- login Panel---
        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        loginPanel.setLayout(null);

        JLabel secondLabel = new JLabel("TARGOBANK");
        secondLabel.setBounds(10, 10, 200, 30);
        secondLabel.setFont(welcomelabel1.getFont().deriveFont(30f));
        loginPanel.add(secondLabel);

        JLabel logInLabel = new JLabel("Log in");
        logInLabel.setBounds(260, 50, 100, 30);
        logInLabel.setFont(welcomelabel1.getFont().deriveFont(25f));
        loginPanel.add(logInLabel);

        submitErrorLabel = new JLabel("");
        submitErrorLabel.setForeground(Color.RED);
        submitErrorLabel.setBounds(230, 170, 200, 30);
        //will be changed later to text when any errors occur trying to login
        loginPanel.add(submitErrorLabel);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(150, 85, 100, 30);
        usernameLabel.setFont(welcomelabel1.getFont().deriveFont(15f));
        loginPanel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(150, 135, 100, 30);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(15f));
        loginPanel.add(passwordLabel);

        //usernameField = new JTextField();
        usernameField.setBounds(250,90,100, 30);
        loginPanel.add(usernameField);

             //passwordField = new JPasswordField();
        passwordField.setBounds(250,140,100, 30);
        loginPanel.add(passwordField);

        JButton finishInput = new JButton("Submit");
        finishInput.setBounds(260, 210, 80, 20);
        finishInput.setActionCommand("SUBMIT_LOGIN_INPUT");
        finishInput.addActionListener(this);
        loginPanel.add(finishInput);

        //enter makes it submit
        usernameField.addActionListener(_ -> finishInput.doClick());
        passwordField.addActionListener(_ -> finishInput.doClick());

        JButton back = new JButton("Back");
        back.setBounds(500, 210, 70, 20);
        back.setActionCommand("BACK_START");
        back.addActionListener(this);
        loginPanel.add(back);
        cards.add(loginPanel, CARD_SECOND);

                //---3rd panel---
        JPanel manageAccountPanel = new JPanel();
        manageAccountPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        manageAccountPanel.setLayout(null);

        balanceLabel = new JLabel("Balance: ");
        balanceLabel.setBounds(350, 10, 200, 30);
        balanceLabel.setFont(balanceLabel.getFont().deriveFont(20f));
        manageAccountPanel.add(balanceLabel);

        JLabel bankLabel = new JLabel("TARGOBANK");
        bankLabel.setBounds(10, 10, 200, 30);
        bankLabel.setFont(bankLabel.getFont().deriveFont(30f));
        manageAccountPanel.add(bankLabel);

                     //------ BUTTONS -----------
        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(this);
        transferButton.setActionCommand("TRANSFER");
        transferButton.setBounds(20,90,170,60);
        manageAccountPanel.add(transferButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);
        depositButton.setActionCommand("DEPOSIT");
        depositButton.setBounds(210,90,170,60);
        manageAccountPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        withdrawButton.setActionCommand("WITHDRAW");
        withdrawButton.setBounds(400,90,170,60);
        manageAccountPanel.add(withdrawButton);

        JButton logOutButton = new JButton("Log out");
        logOutButton.setBounds(470, 210, 100, 30);
        logOutButton.setActionCommand("LOGOUT");
        logOutButton.addActionListener(this);
        manageAccountPanel.add(logOutButton);



        cards.add(manageAccountPanel, CARD_THIRD);

                //--- add everything to frame---
        frame.add(cards, BorderLayout.CENTER);
        cardLayout.show(cards, CARD_START);

        frame.setResizable(false);
        frame.setMaximizedBounds(frame.getBounds());
        frame.setExtendedState(JFrame.NORMAL);

        frame.setVisible(true); // in focus
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //i think also save customers here
        frame.setSize(600, 300); //size
        frame.setLocationRelativeTo(null); // placed at center

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch(cmd) {
            case "SECOND" -> {
                cardLayout.show(cards, CARD_SECOND);
            }

            case "BACK_START" -> {
                cardLayout.show(cards, CARD_START);
                usernameField.setText("");
                passwordField.setText("");
            }

            case "SUBMIT_LOGIN_INPUT" -> {
                String username = usernameField.getText().trim();
                String pin = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || pin.isEmpty()) {
                    submitErrorLabel.setText("Can't leave empty text fields.");
                }

                BankAccount loginAccount = null;
                try {
                    loginAccount = bankService.tryLogin(username, pin);
                } catch (WrongPinException exc) {
                    submitErrorLabel.setText(exc.getMessage());
                }
                

                if(loginAccount != null) {
                    usernameField.setText("");
                    passwordField.setText("");
                    cardLayout.show(cards, CARD_THIRD);
                    balanceLabel.setText("Balance: "+ loginAccount.getBalance() + "€");
                    currentAccount = loginAccount;
                } else {
                    submitErrorLabel.setText("Wrong password or username.");;
                }
            }

            case "TRANSFER" -> {
                String input = JOptionPane.showInputDialog(frame, "Customer name: ");

                if (input != null) {
                    BankAccount ba = bankService.getBankAccountbyName(input);

                    if (ba == null) {
                        JOptionPane.showMessageDialog(frame, "Couldnt find that Person.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // sending to own acc?
                        if (ba.equals(currentAccount)) {
                            JOptionPane.showMessageDialog(frame, "Can't send money to yourself.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        BigDecimal amount = new BigDecimal(JOptionPane.showInputDialog(frame, "Transfer amount: "));

                        //right format? (decimals)
                        if (amount.scale() > 2) {
                            JOptionPane.showMessageDialog(frame, "More than two decimal numbers", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        //amount <0 ?
                        if (amount.compareTo(BigDecimal.ZERO) < 0) {
                            JOptionPane.showMessageDialog(frame,
                            "Can't enter a negative number.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        //
                        if (amount.compareTo(new BigDecimal("0.01")) < 0) {
                            JOptionPane.showMessageDialog(frame,
                            "Input too small, select bigger amount"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        } else {
                            //amount is okay
                            currentAccount.changeBalance(amount.negate());
                            balanceLabel.setText("Balance: "+String.format("%.2f",currentAccount.getBalance())+"€");
                            ba.changeBalance(amount);
                        }
                    }
                }
            }

            case "DEPOSIT" -> {
                String input = JOptionPane.showInputDialog(frame, "Deposit amount:");
                
                if (input != null) {
                    try {
                        //validate decimal
                        BigDecimal bd = new BigDecimal(input);
                        
                        if (bd.scale() > 2) {
                            JOptionPane.showMessageDialog(frame, "More than two decimal numbers", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        BigDecimal amount = new BigDecimal(input);

                        if (amount.compareTo(new BigDecimal(0.10)) < 0) {
                            JOptionPane.showMessageDialog(frame, "Input too small", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        currentAccount.changeBalance(amount);
                        balanceLabel.setText("Balance: "+String.format("%.2f",currentAccount.getBalance())+"€");
                        amount = null;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            case "WITHDRAW" -> {
                String input = JOptionPane.showInputDialog(frame, "Withdraw amount:");
                if (input != null) {
                    try {
                        //validate decimal
                        BigDecimal bd = new BigDecimal(input);
                        if (bd.scale() > 2) {
                            JOptionPane.showMessageDialog(frame, "More than two decimal numbers", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        BigDecimal amount = new BigDecimal(input);

                        if (amount.compareTo(currentAccount.getBalance()) > 0) {
                            JOptionPane.showMessageDialog(frame, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        currentAccount.changeBalance(amount.negate());
                        balanceLabel.setText("Balance: "+String.format("%.2f",currentAccount.getBalance())+"€");
                        amount = null;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            case "LOGOUT" -> {
                bankService.saveCustomers();
                cardLayout.show(cards, CARD_SECOND);
                currentAccount = null;
            }
        }
    }
}
