import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM {
    static int balance = 10000;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ATM");
        frame.getContentPane().setBackground(Color.CYAN);
        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setBackground(Color.yellow);
        checkBalanceButton.setForeground(Color.black);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(Color.BLUE);
        withdrawButton.setForeground(Color.black);
        JButton depositButton = new JButton("Deposit");
        depositButton.setBackground(Color.RED);
        depositButton.setForeground(Color.black);

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Your balance is Rs" + balance);
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String wAmountStr = JOptionPane.showInputDialog(frame, "Enter the amount you want to withdraw");
                int wAmount = Integer.parseInt(wAmountStr);
                if(wAmount>balance){
                    JOptionPane.showMessageDialog(frame, "insufficient balance");
                }
                else {
                    balance -= wAmount;
                    JOptionPane.showMessageDialog(frame, "Successfully withdrawn Rs" + wAmount);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dAmountStr = JOptionPane.showInputDialog(frame, "Enter the amount you want to deposit");
                int dAmount = Integer.parseInt(dAmountStr);
                balance += dAmount;
                JOptionPane.showMessageDialog(frame, "Successfully deposited Rs" + dAmount);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(checkBalanceButton);
        frame.add(withdrawButton);
        frame.add(depositButton);
        frame.setVisible(true);
    }
}
