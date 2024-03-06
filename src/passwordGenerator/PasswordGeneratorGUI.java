package passwordGenerator;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PasswordGeneratorGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel lengthLabel = new JLabel("Password Length:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(lengthLabel, constraints);

        SpinnerModel lengthModel = new SpinnerNumberModel(12, 1, 100, 1);
        JSpinner lengthSpinner = new JSpinner(lengthModel);
        constraints.gridx = 1;
        panel.add(lengthSpinner, constraints);
        
        JLabel numPasswordsLabel = new JLabel("Number of Passwords:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(numPasswordsLabel, constraints);
        
        SpinnerModel numPasswordsModel = new SpinnerNumberModel(10, 1, 100, 1);
        JSpinner numPasswordsSpinner = new JSpinner(numPasswordsModel);
        constraints.gridx = 1;
        panel.add(numPasswordsSpinner, constraints);
        
        JButton generateButton = new JButton("Generate Passwords");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(generateButton, constraints);

        JList<String> passwordList = new JList<>();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(new JScrollPane(passwordList), constraints);

        JButton copyButton = new JButton("Copy Passwords");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(copyButton, constraints);

        List<String> passwords = new ArrayList<>();

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int length = (int) lengthSpinner.getValue();
                int numPasswords = (int) numPasswordsSpinner.getValue();
                passwords.clear();
                for (int i = 0; i < numPasswords; i++) {
                    passwords.add(PasswordGenerator.generatePassword(length));
                }
                passwordList.setListData(passwords.toArray(new String[0]));
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (String password : passwords) {
                    sb.append(password).append("\n");
                }
                String passwordString = sb.toString();
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(passwordString), null);
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}