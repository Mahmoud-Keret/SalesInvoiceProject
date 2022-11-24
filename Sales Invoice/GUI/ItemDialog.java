package GUI;

import javax.swing.*;
import java.awt.*;

public class ItemDialog extends JDialog{
    private final JTextField itemNameField;
    private final JTextField itemAmountField;
    private final JTextField itemPriceField;


    public ItemDialog(InvoiceGenerator frame) {
        setTitle("Add New Item");
        itemNameField = new JTextField(20);
        JLabel itemNameLbl = new JLabel("Item Name");

        itemAmountField = new JTextField(20);
        JLabel itemAmountLbl = new JLabel("Item Amount");

        itemPriceField = new JTextField(20);
        JLabel itemPriceLbl = new JLabel("Item Price");

        JButton okBtn = new JButton("Ok");
        okBtn.setActionCommand("New Item Ok");
        okBtn.addActionListener(frame.getActions());

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setActionCommand("Item Cancel");
        cancelBtn.addActionListener(frame.getActions());

        add(itemNameLbl);
        add(itemNameField);
        add(itemAmountLbl);
        add(itemAmountField);
        add(itemPriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);
        setLayout(new GridLayout(4,2));

        pack();


    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemAmountField() {
        return itemAmountField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
}
