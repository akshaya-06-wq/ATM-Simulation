import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame implements ActionListener {
    private double balance;
    private JTextField amountField;
    private JTextArea displayArea;

    public ATM() {
        balance = 0.0;
        createGUI();
    }

    private void createGUI() {
        setTitle("ATM Simulation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); // You may want to consider using a layout manager instead

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(20, 20, 100, 30);
        add(amountLabel);

        amountField = new JTextField();
        amountField.setBounds(120, 20, 150, 30);
        add(amountField);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBounds(20, 70, 150, 30);
        checkBalanceButton.addActionListener(this);
        add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(180, 70, 150, 30);
        depositButton.addActionListener(this);
        add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(20, 110, 150, 30);
        withdrawButton.addActionListener(this);
        add(withdrawButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(180, 110, 150, 30);
        exitButton.addActionListener(this);
        add(exitButton);

        displayArea = new JTextArea();
        displayArea.setBounds(20, 150, 350, 100);
        displayArea.setEditable(false);
        add(displayArea);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Check Balance":
                checkBalance();
                break;
            case "Deposit":
                depositMoney();
                break;
            case "Withdraw":
                withdrawMoney();
                break;
            case "Exit":
                exitATM();
                break;
        }
    }

    private void checkBalance() {
        displayArea.setText("Your current balance is: $" + balance);
        amountField.setText(""); // Clear the text field
    }

    private void depositMoney() {
        try {
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty()) {
                displayArea.setText("Please enter an amount.");
                return;
            }
            double depositAmount = Double.parseDouble(amountText);
            if (depositAmount > 0) {
                balance += depositAmount;
                displayArea.setText("Successfully deposited: $" + depositAmount + "\nYour current balance is: $" + balance);
            } else {
                displayArea.setText("Invalid amount. Please try again.");
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid input. Please enter a valid amount.");
        }
        amountField.setText(""); // Clear the text field
    }

    private void withdrawMoney() {
        try {
            String amountText = amountField.getText().trim();
            if (amountText.isEmpty()) {
                displayArea.setText("Please enter an amount.");
                return;
            }
            double withdrawAmount = Double.parseDouble(amountText);
            if (withdrawAmount > 0 && withdrawAmount <= balance) {
                balance -= withdrawAmount;
                displayArea.setText("Successfully withdrew: $" + withdrawAmount + "\nYour current balance is: $" + balance);
            } else {
                displayArea.setText("Invalid amount or insufficient balance. Please try again.");
            }
        } catch (NumberFormatException ex) {
            displayArea.setText("Invalid input. Please enter a valid amount.");
        }
        amountField.setText(""); // Clear the text field
    }

    private void exitATM() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
