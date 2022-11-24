package Actions;

import GUI.InvoiceDialog;
import GUI.InvoiceGenerator;
import GUI.ItemDialog;
import Model.Invoice;
import Model.InvoicesTable;
import Model.Item;
import Model.ItemsTable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Actions implements ActionListener, ListSelectionListener {

    private final InvoiceGenerator frame ;
    private InvoiceDialog invoiceDialog;
    private ItemDialog itemDialog ;
    public Actions (InvoiceGenerator frame){
     this.frame=frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionDone=e.getActionCommand();

        switch (actionDone){
            case "Load File": loadFile() ;break;

            case "Save File": saveFile() ;break;

            case "Create New Invoice": createNewInvoice();break;

            case "Delete Invoice": deleteInvoice();break;

            case "Create New Item": createNewItem();break;

            case "Delete Item": deleteItem();break;

            case "New Invoice Ok" : newInvoiceOk();break;

            case "Invoice Cancel" : invoiceCancel();break;

            case "New Item Ok": newItemOk();break;

            case "Item Cancel": cancelNewItem();break;
        }


    }

    private void loadFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(frame);
        try {
            if (result == JFileChooser.APPROVE_OPTION) {

                File invoiceFile = fc.getSelectedFile();
                Path invoicesPath = Paths.get(invoiceFile.getAbsolutePath());
                List<String> invoiceLines = Files.readAllLines(invoicesPath);
                ArrayList<Invoice> invoices = new ArrayList<>();

                for (String invoice : invoiceLines) {
                    String[] invoiceParts = invoice.split(",");
                    int invoiceNum = Integer.parseInt(invoiceParts[0]);
                    String date = invoiceParts[1];
                    String customer = invoiceParts[2];

                    Invoice invoiceLine = new Invoice(invoiceNum,date,customer);
                    invoices.add(invoiceLine);
                }

                result=fc.showOpenDialog(frame);

                if (result==JFileChooser.APPROVE_OPTION){
                    File itemFile = fc.getSelectedFile();
                    Path itemsPath = Paths.get(itemFile.getAbsolutePath());
                    List<String> itemLines = Files.readAllLines(itemsPath);

                    for (String items : itemLines){
                        String [] itemParts = items.split(",");
                        int invoiceNum = Integer.parseInt(itemParts[0]);
                        String itemName = itemParts[1];
                        double price = Double.parseDouble(itemParts[2]);
                        int amount = Integer.parseInt(itemParts[3]);

                        Invoice inv=null;
                        for (Invoice invoice : invoices) {
                            if (invoice.getInvoiceNum()==invoiceNum){
                                inv=invoice;
                                break;
                            }
                        }
                        Item item = new Item(itemName,price,amount,inv);
                        inv.getItems().add(item);


                    }
                }
                frame.setInvoices(invoices);
                InvoicesTable invoicesTableModel=new InvoicesTable(invoices);
                frame.setInvoicesTableModel(invoicesTableModel);
                frame.getInvoicesTable().setModel(invoicesTableModel);
                frame.getInvoicesTableModel().fireTableDataChanged();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void saveFile() {
        ArrayList<Invoice> invoices = frame.getInvoices();
        String headers="";
        String lines="";
        for ( Invoice invoice : invoices) {
            String invCSV = invoice.saveCSV();
            headers += invCSV;
            headers += "\n";

            for (Item item : invoice.getItems()) {
                String itemCSV = item.saveCSV();
                lines += itemCSV;
                lines += "\n";
            }
        }
            try {
            JFileChooser fc = new JFileChooser();
            int result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION){
                File headerFile = fc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = fc.showSaveDialog(frame);
                if ( result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();

                }
            }

            } catch (IOException e){e.printStackTrace();}
    }



    private void createNewInvoice() {
        invoiceDialog = new InvoiceDialog(frame);
        invoiceDialog.setVisible(true);


    }
    private void invoiceCancel(){
        invoiceDialog.setVisible(false);
        invoiceDialog.dispose();
        invoiceDialog=null;

    }

    private void deleteInvoice() {
       int selectedRow = frame.getInvoicesTable().getSelectedRow();
       if ( selectedRow !=-1){
           frame.getInvoices().remove(selectedRow);
           frame.getInvoicesTableModel().fireTableDataChanged();
           frame.getInvoicesTableModel().fireTableDataChanged();

       }
    }

    private void createNewItem() {
        itemDialog = new ItemDialog(frame);
        itemDialog.setVisible(true);

    }

    private void cancelNewItem(){
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog=null;

    }

    private void deleteItem() {
        int selectedInv = frame.getInvoicesTable().getSelectedRow();
        int selectedRowItem = frame.getInvoiceItems().getSelectedRow();
        if(selectedRowItem!=-1 && selectedInv!=-1) {
           Invoice invoice = frame.getInvoices().get(selectedInv);
           invoice.getItems().remove(selectedRowItem);
            ItemsTable itemsTable= new ItemsTable(invoice.getItems());
           frame.getInvoiceItems().setModel(itemsTable);
           itemsTable.fireTableDataChanged();
           frame.getInvoicesTableModel().fireTableDataChanged();

        }
    }
    private void newItemOk() {
        String itemName = itemDialog.getItemNameField().getText();
        int amount = Integer.parseInt(itemDialog.getItemAmountField().getText());
        double price = Double.parseDouble(itemDialog.getItemPriceField().getText());
        int selectedInvoice = frame.getInvoicesTable().getSelectedRow();

        if (selectedInvoice!= -1){
            Invoice invoice = frame.getInvoices().get(selectedInvoice);
            Item item = new Item(itemName,price,amount,invoice);
            invoice.getItems().add(item);
            ItemsTable itemsTable= (ItemsTable) frame.getInvoiceItems().getModel();
            itemsTable.fireTableDataChanged();
            frame.getInvoicesTableModel().fireTableDataChanged();
        }

        itemDialog.dispose();

    }

    private void newInvoiceOk() {
        String date = invoiceDialog.getDateField().getText();
        String customer = invoiceDialog.getCustNameField().getText();
        int num = frame.getNextInvoiceNum();

        Invoice invoice = new Invoice(num , date , customer);
        frame.getInvoices().add(invoice);
        frame.getInvoicesTableModel().fireTableDataChanged();
        invoiceDialog.dispose();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
