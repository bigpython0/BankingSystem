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
        button.setBounds(280, 130, 280, 40);
        startPanel.add(button);

        JLabel welcomelabel1 = new JLabel("Welcome to");
        welcomelabel1.setFont(welcomelabel1.getFont().deriveFont(30f));
        welcomelabel1.setBounds(330, 10, 300 ,30);
        startPanel.add(welcomelabel1);

        JLabel welcomelabel2 = new JLabel(bankName);
        welcomelabel2.setFont(welcomelabel2.getFont().deriveFont(50f));
        welcomelabel2.setBounds(280, 40, 300 ,70);
        startPanel.add(welcomelabel2);

        //frame.add(panel, BorderLayout.CENTER);
        
        cards.add(startPanel, CARD_START);

        JPanel secondPanel = new JPanel();
        secondPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        secondPanel.setLayout(null);

        JLabel secondLabel = new JLabel("TARGOBANK");
        secondLabel.setBounds(20, 20, 200, 30);
        secondLabel.setFont(welcomelabel1.getFont().deriveFont(30f));
        secondPanel.add(secondLabel);

        //MAIN OUTPUT temp
        JTextField mainField = new JTextField();
        mainField.setBounds(300,10,400, 240);
        secondPanel.add(mainField);
        
        JTextField usernameField = new JTextField();
        usernameField.setBounds(30,80,100, 30);
        secondPanel.add(usernameField);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(30,150,100, 30);
        secondPanel.add(passwordField);

        JButton back = new JButton("Back");
        back.setBounds(710, 230, 70, 20);
        back.setActionCommand("BACK_START");
        back.addActionListener(this);
        secondPanel.add(back);

        cards.add(secondPanel, CARD_SECOND);
        frame.add(cards, BorderLayout.CENTER);
        cardLayout.show(cards, CARD_START);

        frame.setResizable(false);
        frame.setMaximizedBounds(frame.getBounds());
        frame.setExtendedState(JFrame.NORMAL);

        frame.setVisible(true); // in focus
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // choose action on hitting close
        frame.setSize(800, 300); //size
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
