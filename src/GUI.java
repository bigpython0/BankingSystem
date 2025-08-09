import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    private CardLayout cardLayout;
    private JPanel cards;

    private static final String CARD_START = "START";
    private static final String CARD_SECOND = "SECOND";

    private String bankName = "Targobank"; //only centered for ts

    public GUI() {

        JFrame frame = new JFrame("Banking System"); //replaces setTitle
        
        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel startPanel = new JPanel();
        startPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        startPanel.setLayout(null);
        
        JButton button = new JButton("START");
        button.addActionListener(this);
        button.setActionCommand("SECOND");
        button.setBounds(140, 130, 280, 40);
        startPanel.add(button);

        JLabel welcomelabel1 = new JLabel("Welcome to");
        welcomelabel1.setFont(welcomelabel1.getFont().deriveFont(30f));
        welcomelabel1.setBounds(200, 10, 300 ,30);
        startPanel.add(welcomelabel1);

        JLabel welcomelabel2 = new JLabel(bankName);
        welcomelabel2.setFont(welcomelabel2.getFont().deriveFont(50f));
        welcomelabel2.setBounds(150, 35, 300 ,70);
        startPanel.add(welcomelabel2);

        //frame.add(panel, BorderLayout.CENTER);
        
        cards.add(startPanel, CARD_START);

        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        loginPanel.setLayout(null);

        JLabel secondLabel = new JLabel("TARGOBANK");
        secondLabel.setBounds(10, 10, 200, 30);
        secondLabel.setFont(welcomelabel1.getFont().deriveFont(30f));
        loginPanel.add(secondLabel);

        //MAIN OUTPUT temp

        JLabel logInLabel = new JLabel("Log in");
        logInLabel.setBounds(260, 50, 100, 30);
        logInLabel.setFont(welcomelabel1.getFont().deriveFont(25f));
        loginPanel.add(logInLabel);
        
        JTextField usernameField = new JTextField();
        usernameField.setBounds(250,90,100, 30);
        loginPanel.add(usernameField);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(150, 85, 100, 30);
        usernameLabel.setFont(welcomelabel1.getFont().deriveFont(15f));
        loginPanel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(150, 165, 100, 30);
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(15f));
        loginPanel.add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(250,170,100, 30);
        loginPanel.add(passwordField);

        JButton finishInput = new JButton("Submit");
        finishInput.setBounds(260, 210, 80, 20);
        finishInput.setActionCommand("SUBMIT_INPUT");
        finishInput.addActionListener(this);
        loginPanel.add(finishInput);

        JButton back = new JButton("Back");
        back.setBounds(710, 10, 70, 20);
        back.setActionCommand("BACK_START");
        back.addActionListener(this);
        loginPanel.add(back);

        cards.add(loginPanel, CARD_SECOND);
        frame.add(cards, BorderLayout.CENTER);
        cardLayout.show(cards, CARD_START);

        frame.setResizable(false);
        frame.setMaximizedBounds(frame.getBounds());
        frame.setExtendedState(JFrame.NORMAL);

        frame.setVisible(true); // in focus
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // choose action on hitting close
        frame.setSize(600, 300); //size
        frame.setLocationRelativeTo(null); // placed at center

    }
    
    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if ("SECOND".equals(cmd)) {
            cardLayout.show(cards, CARD_SECOND);
        } else if ("BACK_START".equals(cmd)) {
            cardLayout.show(cards, CARD_START);
        } 
    }
}
