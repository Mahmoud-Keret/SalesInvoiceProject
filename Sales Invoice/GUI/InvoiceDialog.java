package GUI;

import javax.swing.*;
import java.awt.*;

public class InvoiceDialog extends JDialog {
    private final JTextField custNameField;
    private final JTextField dateField ;

    public InvoiceDialog(InvoiceGenerator frame) {
        setTitle ("Create New Invoice");
        JLabel custNameLbl = new JLabel("Customer Name");
        custNameField = new JTextField(20);

        JLabel dateLbl = new JLabel("Date");
        dateField = new JTextField(20);

        JButton okBtn = new JButton("Ok");
        okBtn.setActionCommand("New Invoice Ok");
        okBtn.addActionListener(frame.getActions());

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("Invoice Cancel");
        cancelBtn.addActionListener(frame.getActions());

        add(dateLbl);
        add(dateField);
        add(custNameLbl);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);

        setLayout(new GridLayout (3,2));

        pack();
    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getDateField() {
        return dateField;
    }
}
