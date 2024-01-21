import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SIPCalculatorGUI1 extends JFrame {

    private static final double REGISTRATION_FEE = 150.0;
    private static final double ANNUAL_INCREMENT = 50.0;

    private JTextField principalField, annualRateField, periodField, resultField;

    public SIPCalculatorGUI1() {
        setTitle("SIP Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel principalLabel = new JLabel("Principal Amount:");
        JLabel annualRateLabel = new JLabel("Annual Interest Rate (%):");
        JLabel periodLabel = new JLabel("Investment Period (Years):");
        JLabel resultLabel = new JLabel("Final Amount:");

        principalField = new JTextField(10);
        annualRateField = new JTextField(10);
        periodField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateSIP();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(principalLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(principalField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(annualRateLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(annualRateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(periodLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(periodField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(calculateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(resultField, gbc);

        add(panel);
        setVisible(true);
    }

    private void calculateSIP() {
        try {
            double principal = Double.parseDouble(principalField.getText());
            double annualRate = Double.parseDouble(annualRateField.getText());
            int period = Integer.parseInt(periodField.getText());

            double totalReturn = 0;

            principal -= REGISTRATION_FEE;

            for (int year = 1; year <= period; year++) {
                double returnsForYear = principal * (1 + annualRate / 100);

                totalReturn += returnsForYear;

                principal += ANNUAL_INCREMENT;
            }

            resultField.setText(String.format("%.2f", totalReturn));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SIPCalculatorGUI1();
            }
        });
    }
}
